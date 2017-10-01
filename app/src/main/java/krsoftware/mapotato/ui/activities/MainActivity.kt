package krsoftware.mapotato.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import krsoftware.mapotato.R
import krsoftware.mapotato.showFragment
import krsoftware.mapotato.ui.fragments.RestaurantsFragment
import krsoftware.mapotato.ui.fragments.SpeechFragment
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Fresco.initialize(this)
        showSpeechFragment()
    }

    private fun showRestaurantsFragment(query: String) {
        showFragment(RestaurantsFragment.newInstance(query), true, "RestaurantsFragment")
    }

    private fun showSpeechFragment() {
        val fragment = SpeechFragment.newInstance()
        fragment.getSpeechListener()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.single())
                .subscribe({
                    try {
                        Log.d(TAG, "Speech returned $it")
                        showRestaurantsFragment(it)
                    } catch (e: IllegalStateException) {
                        // ignore
                    }
                }, this::onSubscriptionError)
        showFragment(fragment)
    }

    private fun onSubscriptionError(e: Throwable) {
        throw RuntimeException(e)
    }
}
