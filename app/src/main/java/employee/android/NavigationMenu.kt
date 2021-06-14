package employee.android

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.text.HtmlCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.navigation.NavController
import employee.android.data.LoginRepository
import employee.android.data.model.UserDetails
import employee.android.ui.attributes.AttributeFragment
import java.text.SimpleDateFormat

class NavigationMenu : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController : NavController
    private lateinit var navDrawer : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_menu)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            navDrawer = findViewById (R.id.drawer_layout);
            // If the navigation drawer is not open then open it, if its already open then close it.
            if (!navDrawer.isDrawerOpen(GravityCompat.START)) navDrawer.openDrawer(GravityCompat.START);
            else navDrawer.closeDrawer(GravityCompat.END);
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val user: UserDetails = intent.getSerializableExtra("user") as UserDetails;


        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val headerView : View = navView.getHeaderView(0)
        val userNameTextView : TextView = headerView.findViewById(R.id.textViewUserName)
        userNameTextView.text = getString(R.string.username).format(user.username)
        val registerDateTextView : TextView = headerView.findViewById(R.id.textViewRegisterDate)
        registerDateTextView.text = getString(R.string.user_register_date).format(simpleDateFormat.format(user.registerDate))
        val expireDateTextView : TextView = headerView.findViewById(R.id.textViewExpireDate)
        expireDateTextView.text = getString(R.string.user_expiration_date).format(simpleDateFormat.format(user.accountExpire))



        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_employee, R.id.nav_attribute, R.id.nav_map), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //When is kotlin way of switch!!!
        when(item.toString()){
            "Employees" -> navController.navigate(R.id.EmployeesFragment)
            "Attribute" -> navController.navigate(R.id.AttributeFragment)
            "Map"       -> navController.navigate(R.id.MapFragment)
        }
        navDrawer.closeDrawer(GravityCompat.START)
        return true
    }
}