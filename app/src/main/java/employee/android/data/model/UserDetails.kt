package employee.android.data.model

import java.util.*

data class UserDetails(val jwtToken: String, val username: String, val accountExpire: Date, val registerDate: Date, val enabled: Boolean): java.io.Serializable