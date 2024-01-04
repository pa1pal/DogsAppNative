package `in`.pawan.dogsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.branch.referral.Branch

@HiltAndroidApp
class DogApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Branch.enableLogging()


        // Branch object initialization
        Branch.getAutoInstance(this).disableTracking(true)
//        Branch.getAutoInstance(this).enableFacebookAppLinkCheck();

//        Branch.getInstance().disableTracking(false)
    }
}