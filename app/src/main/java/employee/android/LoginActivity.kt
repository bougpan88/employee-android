package employee.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import employee.android.data.LoginRepository
import employee.android.data.model.UserDetails
import employee.android.ui.login.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginRepository : LoginRepository

    companion object {
        lateinit var loginActivityContext: LoginActivity
        fun getContext(): Context? {
            return loginActivityContext.applicationContext
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActivityContext = this
        loginRepository = LoginRepository()

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)


        loginViewModel = ViewModelProvider(this)
            .get(LoginViewModel::class.java)


        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }


            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                val call = loginRepository.login(username.text.toString(), password.text.toString())

                call.enqueue(object : Callback<UserDetails> {
                    override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                        if (response.code() == 200) {
                            val user: UserDetails = response.body()
                            val intent = Intent(this@LoginActivity, NavigationMenu::class.java)
                            intent.putExtra("user", user)
                            startActivity(intent)
                            finish()
                        } else {
                            loading.visibility = View.INVISIBLE
                            showLoginFailed(R.string.login_failed)
                        }
                    }
                    override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                        loading.visibility = View.INVISIBLE
                        showLoginFailed(R.string.login_failed)
                    }
                })

            }
        }

    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext,
                HtmlCompat.fromHtml("<font color='red' ><b>" + getResources().getString(errorString) + "</b></font>", HtmlCompat.FROM_HTML_MODE_LEGACY),
                Toast.LENGTH_LONG).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}