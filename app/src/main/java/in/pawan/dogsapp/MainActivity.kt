package `in`.pawan.dogsapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import dagger.hilt.android.AndroidEntryPoint
import `in`.pawan.dogsapp.ui.main.MainFragmentDirections
import io.branch.referral.Branch
import io.branch.referral.Branch.BranchReferralInitListener
import io.branch.referral.validators.DeepLinkRoutingValidator
import java.lang.ref.WeakReference


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var analytics: FirebaseAnalytics
    private var activityRef: WeakReference<Activity>? = null

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRef = WeakReference<Activity>(this)

        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        // listener (within Main Activity's onStart)
        analytics = Firebase.analytics

//        analytics.appInstanceId.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                // Get the Firebase App Instance ID
//                val appInstanceId = task.result
//                // Set the Firebase App Instance ID as a request metadata in Branch SDK
//                if (appInstanceId != null) {
//                    Branch.getInstance().setRequestMetadata("\$firebase_app_instance_id", appInstanceId)
//                }
//                Log.d(TAG, "Firebase App Instance ID: $appInstanceId")
//            } else {
//                val exception = task.exception
//                Log.e(TAG, "Error getting Firebase App Instance ID", exception)
//            }
//        }

        AlertDialog.Builder(this)
            .setTitle("Navigate to Details?")
            .setMessage("Accept cookies")
            .setPositiveButton("Yes") { dialog, _ ->
               Branch.getInstance().disableTracking(false)
            }
            .setNegativeButton("No") { dialog, _ ->
                Branch.getInstance().disableTracking(true)
                dialog.dismiss()
            }
            .show()


        Branch.sessionBuilder(this).withCallback { referringParams, error ->
            if (error == null) {
                Log.i("BRANCH SDK logs", referringParams.toString())
                if (referringParams?.has("breed") == true) {
                    val action = MainFragmentDirections.actionMainFragmentToDogsDetailsFragment(
                        breed = if (referringParams?.has("breed") == true) {
                            referringParams?.getString("breed")
                        } else ""
                    )

                    DeepLinkRoutingValidator.validate(activityRef)
                    this.findNavController(R.id.nav_host_fragment).navigate(action)
                }
            } else {
                Log.e("BRANCH SDK", error.message)
            }
        }.withData(this.intent.data).init()

//        GlobalScope.launch {
//            val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(this@MainActivity)
//            Log.d("aaid", adInfo.id ?: "not capturing")
//        }
    }

//    override fun onResume() {
//        super.onResume()
////        intent?.putExtra("branch", "http://pawanpal1.app.link/testlink")
//        this.intent?.putExtra("branch_force_new_session", true)
//        Branch.sessionBuilder(this).withCallback { referringParams, error ->
//            if (error == null) {
//                Log.i("BRANCH SDK logs", referringParams.toString())
//                val action = MainFragmentDirections.actionMainFragmentToDogsDetailsFragment(
//                    breed = referringParams?.getString("breed")
//                )
//
//                this.findNavController(R.id.nav_host_fragment).navigate(action)
//
//            } else {
//                Log.e("BRANCH SDK", error.message)
//            }
//        }.withData(this.intent.data).reInit()
//    }

    private val branchReferralInitListener =
        BranchReferralInitListener { linkProperties, error -> // do stuff with deep link data (nav to page, display content, etc)
            if (error == null) {
                Log.i("Branch Init Config", "Deep Link Data: " + linkProperties.toString())
            } else {
                Log.e("Branch Init Config", "Error while initializing: " + error.message)
            }
        }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        if (intent != null && intent.hasExtra("branch_force_new_session") && intent.getBooleanExtra(
                "branch_force_new_session",
                false
            )
        ) {
            Log.i("onNewIntent", "Branch SDK reinitialized");
            Branch.sessionBuilder(this).withCallback(branchReferralInitListener).reInit();
        } else {
            Log.i("onNewIntent", "Intent is null");
        }

//        this.intent?.putExtra("branch_force_new_session", true)
//        Branch.sessionBuilder(this).reInit()
//        withCallback { referringParams, error ->
//            if (error == null) {
//                Log.i("BRANCH SDK logs", referringParams.toString())
//                val action = MainFragmentDirections.actionMainFragmentToDogsDetailsFragment(
//                    breed = referringParams?.getString("breed")
//                )
//
//                this.findNavController(R.id.nav_host_fragment).navigate(action)
//
//            } else {
//                Log.e("BRANCH SDK", error.message)
//            }
//        }.withData(this.intent.data).reInit()
    }
}