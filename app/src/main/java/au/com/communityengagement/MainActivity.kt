package au.com.communityengagement

import android.Manifest
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import au.com.communityengagement.util.CustomSharedPreferences
import au.com.communityengagement.util.DataGenerator
import au.com.forteis.rhinocrm.db.entities.CommunityEngagementDatabase
import com.google.android.material.navigation.NavigationView
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(),
    MultiplePermissionsListener,
    NavigationView.OnNavigationItemSelectedListener {

    //We are not using it but we potential can simply create list of permissions and show dialog if permissions are needed
    val permissions = listOf(Manifest.permission.INTERNET)

    @Inject lateinit var customSharedPreferences: CustomSharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setDrawer()

        customSharedPreferences.saveUser(DataGenerator.getCurrentUser())
    }

    private fun setDrawer() {
        val drawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
        TODO("Not yet implemented")
    }

    override fun onPermissionRationaleShouldBeShown(
        permissions: MutableList<PermissionRequest>?,
        token: PermissionToken?
    ) {
        TODO("Not yet implemented")
    }
}