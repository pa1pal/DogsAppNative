package `in`.pawan.dogsapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import io.branch.referral.Branch
import io.branch.referral.BranchLogger

@HiltAndroidApp
class DogApplication: Application() {
    override fun onCreate() {
        super.onCreate()

//        Branch.enableLogging()


//        Branch.expectDelayedSessionInitialization(true)
        Branch.enableLogging(BranchLogger.BranchLogLevel.VERBOSE)

        Branch.getAutoInstance(this)

    }
}