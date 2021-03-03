package employee.android.httpservice

/*import com.android.volley.AuthFailureError
import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley*/

import employee.android.data.model.UserDetails
import employee.android.data.model.UserRequestDetails
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginHttpService {


    @POST("/authenticate")
    fun listRepos(@Body userRequestDetails: UserRequestDetails): Call<UserDetails>

   //Volley Library !
   /* fun createRequestBody(username: String, password: String): JSONObject{
        val jsonBody = JSONObject()
        jsonBody.put("username", username)
        jsonBody.put("password", password)
        return jsonBody
    }

    fun login(username: String, password: String): Unit {


        try {
            val requestQueue = Volley.newRequestQueue(LoginActivity.getContext())
            val jsonBody = createRequestBody(username, password)
            val requestBody = jsonBody.toString()
            val stringRequest: StringRequest = object : StringRequest(
                Method.POST,
                authenticationPath,
                Response.Listener { response -> response},
                Response.ErrorListener { }) {
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

                @Throws(AuthFailureError::class)
                override fun getBody(): ByteArray? {
                    return try {
                        requestBody?.toByteArray(charset("utf-8"))
                    } catch (uee: UnsupportedEncodingException) {
                        VolleyLog.wtf(
                            "Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody,
                            "utf-8"
                        )
                        return null
                    }
                }

                override fun parseNetworkResponse(response: NetworkResponse): Response<String> {
                    var responseString = ""
                    if (response != null) {
                        responseString = response.statusCode.toString()
                        // can get more details such as response.headers
                    }
                    return Response.success(
                        responseString,
                        HttpHeaderParser.parseCacheHeaders(response)
                    )
                }
            }
            requestQueue.add(stringRequest)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }*/


}