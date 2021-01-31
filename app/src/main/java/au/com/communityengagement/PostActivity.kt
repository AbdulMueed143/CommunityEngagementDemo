package au.com.communityengagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import au.com.communityengagement.di.ViewModelProviderFactory
import au.com.communityengagement.util.CustomSharedPreferences
import javax.inject.Inject

class PostActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    @Inject
    lateinit var customSharedPreferences: CustomSharedPreferences

    //Basically, when user clicks on comment, we open this activity to allow user to comment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
    }

}