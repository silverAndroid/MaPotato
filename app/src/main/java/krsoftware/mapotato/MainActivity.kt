package krsoftware.mapotato

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.facebook.drawee.backends.pipeline.Fresco

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Fresco.initialize(this)
        showRestaurantsFragment("Google")
    }

    private fun showRestaurantsFragment(query: String) {
        showFragment(RestaurantsFragment.newInstance(query), true, "RestaurantsFragment")
    }
}
