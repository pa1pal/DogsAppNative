package `in`.pawan.dogsapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Dogs app", "onStart: Test onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Dogs app", "onResume: Test onresume")
    }
}