package `in`.pawan.dogsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.branch.referral.Branch

@HiltAndroidApp
class DogApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Branch.enableLogging()
//        Branch.enableTestMode()


        // Branch object initialization
        Branch.getAutoInstance(this)


//        Branch.getAutoInstance(this).enableFacebookAppLinkCheck();

//        Branch.getInstance().disableTracking(false)
    }
}