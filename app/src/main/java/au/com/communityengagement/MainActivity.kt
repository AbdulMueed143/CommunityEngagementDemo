package au.com.communityengagement

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import au.com.communityengagement.db.CommunityEngagementDatabase
import au.com.communityengagement.util.CustomSharedPreferences
import au.com.communityengagement.util.DataGenerator
import au.com.communityengagement.util.EspressoIdlingResource
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {


    @Inject lateinit var customSharedPreferences: CustomSharedPreferences
    @Inject lateinit var instance: CommunityEngagementDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setDrawer()

        customSharedPreferences.getUser()?.let {

        } ?: kotlin.run {
            customSharedPreferences.saveUser(DataGenerator.getCurrentUser())
        }
    }

    private fun setDrawer() {
        val drawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}