package employee.android.data

import employee.android.data.model.UserDetails
import employee.android.data.model.UserRequestDetails
import employee.android.httpservice.LoginHttpService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Call<UserDetails> {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.99.100:9595")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: LoginHttpService = retrofit.create(LoginHttpService::class.java)

        val u = UserRequestDetails(username, password)
        return service.listRepos(u)
    }

    fun logout() {
        // TODO: revoke authentication
    }
}