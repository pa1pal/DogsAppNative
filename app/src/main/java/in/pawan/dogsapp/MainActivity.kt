package `in`.pawan.dogsapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import `in`.pawan.dogsapp.data.ApiResponse
import `in`.pawan.dogsapp.ui.main.MainFragmentDirections
import `in`.pawan.dogsapp.ui.main.details.DogsDetailsFragment
import io.branch.referral.validators.DeepLinkRoutingValidator
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var analytics: FirebaseAnalytics
    private var activityRef: WeakReference<Activity>? = null
    private val viewmodel: MainActivityViewModel by viewModels()

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRef = WeakReference<Activity>(this)

        setContentView(R.layout.activity_main)
        setObservers()
    }

    private fun setObservers() {
        viewmodel.readDeepLinkLiveData.observe(this, {
            when (it) {
                is ApiResponse.Success -> {
                    if (it.data?.data?.breed != null && it.data.data.breed.isNotEmpty()) {
                        Log.d(TAG, "Deep Link Success: ${it.data.data.breed}")
                        val navController = findNavController(R.id.nav_host_fragment)
                        if (navController.currentDestination?.label == "DogsDetailsFragment") {
                            navController.popBackStack()
                        }
                        val action = MainFragmentDirections.actionMainFragmentToDogsDetailsFragment(
                            breed = it.data.data.breed
                        )

                        DeepLinkRoutingValidator.validate(activityRef)
                        this.findNavController(R.id.nav_host_fragment).navigate(action)
                    }

//                    if (it?.has("breed") == true) {
//                    val action = MainFragmentDirections.actionMainFragmentToDogsDetailsFragment(
//                        breed = if (referringParams?.has("breed") == true) {
//                            referringParams?.getString("breed")
//                        } else ""
//                    )
//
//                    DeepLinkRoutingValidator.validate(activityRef)
//                    this.findNavController(R.id.nav_host_fragment).navigate(action)
//
//                }
                }
                is ApiResponse.Error -> {
                    Log.d(TAG, "Deep Link Error: ${it.message}")
                }
                is ApiResponse.Loading -> {
                    Log.d(TAG, "Deep Link Loading")
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        if (intent.data?.host == "pawanpal1.app.link") {
            Log.d("MainActivity", intent.data.toString())
            viewmodel.readDeepLink(intent.data.toString())
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        if (intent?.data?.host == "pawanpal1.app.link") {
            Log.d("MainActivity", intent.data.toString())
            viewmodel.readDeepLink(intent.data.toString())
        }
    }
}