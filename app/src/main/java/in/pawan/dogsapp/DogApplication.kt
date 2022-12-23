package `in`.pawan.dogsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.branch.referral.Branch

@HiltAndroidApp
class DogApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Branch logging for debugging
//        Branch.enableTestMode()

        Branch.enableLogging()
        // Branch object initialization
        Branch.getAutoInstance(this)
    }
}