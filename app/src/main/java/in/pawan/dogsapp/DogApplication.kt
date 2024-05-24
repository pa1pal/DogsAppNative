package `in`.pawan.dogsapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import io.branch.referral.Branch

@HiltAndroidApp
class DogApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Branch.enableLogging()
//        Branch.enableTestMode()


        // Branch object initialization
        Branch.getAutoInstance(this).setIdentity("1234")
//        Branch.getAutoInstance(this).enableFacebookAppLinkCheck();

//        Branch.getInstance().disableTracking(false)
    }
}