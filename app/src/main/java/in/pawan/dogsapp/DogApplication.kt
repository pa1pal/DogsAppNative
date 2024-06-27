package `in`.pawan.dogsapp

import android.app.Application
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.HiltAndroidApp
import io.branch.referral.Branch
import io.branch.referral.BranchLogger
import io.branch.referral.validators.IntegrationValidator

@HiltAndroidApp
class DogApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Branch.enableLogging()
//        Branch.enableTestMode()



//        Branch.expectDelayedSessionInitialization(true)

        Branch.getAutoInstance(this).disableTracking(true)

//        IntegrationValidator.validate(this)

//        FirebaseAnalytics.getInstance(this).appInstanceId.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                // Get the Firebase App Instance ID
//                val appInstanceId = task.result
//                // Set the Firebase App Instance ID as a request metadata in Branch SDK
//                if (appInstanceId != null) {
//                    Branch.getAutoInstance(this).setRequestMetadata("\$firebase_app_instance_id", appInstanceId)
//                }̄
//
//                Log.d(MainActivity.TAG, "Firebase App Instance ID: $appInstanceId")
//            } else {
//                val exception = task.exception
//                Branch.getAutoInstance(this).setIdentity("1234")
//                Log.e(MainActivity.TAG, "Error getting Firebase App Instance ID", exception)
//            }
//        }

//        Branch.getAutoInstance(this)

        // Branch object initialization

//        Branch.getAutoInstance(this).enableFacebookAppLīnkCheck();

//        Branch.getInstance().disableTracking(false)
    }
}