package employee.android.data

import employee.android.data.model.UserDetails
import employee.android.data.model.UserRequestDetails
import employee.android.httpservice.LoginHttpService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository() {

    fun logout() {
        //handle logout
    }

    fun login(username: String, password: String): Call<UserDetails> {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://172.27.80.1:9595")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: LoginHttpService = retrofit.create(LoginHttpService::class.java)

        val u = UserRequestDetails(username, password)
        return service.listRepos(u)
    }
}