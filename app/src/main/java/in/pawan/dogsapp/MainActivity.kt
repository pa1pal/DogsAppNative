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
import io.branch.referral.validators.IntegrationValidator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
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

//        AlertDialog.Builder(this)
//            .setTitle("Navigate to Details?")
//            .setMessage("Accept cookies")
//            .setPositiveButton("Yes") { dialog, _ ->
//               Branch.getInstance().disableTracking(false)
//            }
//            .setNegativeButton("No") { dialog, _ ->
//                Branch.getInstance().disableTracking(true)
//                dialog.dismiss()
//            }
//            .show()

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
        Log.e("branch params", Branch.getInstance().latestReferringParams.toString())

    }

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
    }
}