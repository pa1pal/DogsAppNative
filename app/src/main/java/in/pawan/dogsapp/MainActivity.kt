package `in`.pawan.dogsapp

import `in`.pawan.dogsapp.ui.main.MainFragmentDirections
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.branch.referral.Branch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        // listener (within Main Activity's onStart)
        Branch.sessionBuilder(this).withCallback { referringParams, error ->
            if (error == null) {
                Log.i("BRANCH SDK", referringParams.toString())

                val action = MainFragmentDirections.actionMainFragmentToDogsDetailsFragment(
                    breed = referringParams?.getString("breed")
                )

                this.findNavController(R.id.nav_host_fragment).navigate(action)

            } else {
                Log.e("BRANCH SDK", error.message)
            }
        }.withData(this.intent.data).init()

        // first
        val installParams = Branch.getInstance().firstReferringParams
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
//        Branch.sessionBuilder(this).withCallback(branchListener).reInit()
        intent?.putExtra("branch_force_new_session", true)
        Branch.sessionBuilder(this).withCallback { referringParams, error ->
            if (error != null) {
                Log.e("BranchSDK_Tester error", error.message)
            } else if (referringParams != null) {
                Log.e("BranchSDK_Tester refer", referringParams.toString())
            }
        }.reInit()
    }


}