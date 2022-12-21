package `in`.pawan.dogsapp

import `in`.pawan.dogsapp.ui.main.MainFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Branch.getAutoInstance(this)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }
    }

    override fun onStart() {
        super.onStart()
//        val branch = Branch.getInstance()
//        // Branch init
//        branch.initSession({ referringParams, error ->
//            if (error == null) {
//                // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
//                // params will be empty if no data found
//                // ... insert custom logic here ...
//                Log.i("BRANCH SDK", referringParams.toString())
//            } else {
//                Log.i("BRANCH SDK", error.message)
//            }
//        }, this.intent.data, this)
    }
}