package employee.android.data.model

data class UserDetails(val jwtToken: String, val username: String, val accountExpire: String, val registerDate: String, val enabled: Boolean): java.io.Serializable