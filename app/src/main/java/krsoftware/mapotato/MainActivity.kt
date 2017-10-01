package krsoftware.mapotato

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.facebook.drawee.backends.pipeline.Fresco
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class MainActivity : AppCompatActivity() {

    private val MY_PERMISSIONS_RECORD_AUDIO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Fresco.initialize(this)
//        showRestaurantsFragment("Google")
        requestSpeechPermissionWithPermissionCheck()
    }

    private fun showRestaurantsFragment(query: String) {
        showFragment(RestaurantsFragment.newInstance(query), true, "RestaurantsFragment")
    }

    @NeedsPermission(Manifest.permission.RECORD_AUDIO)
    fun requestSpeechPermission() {
        val speechRecognizer : SpeechRecognizer  = SpeechRecognizer.createSpeechRecognizer(this.applicationContext)
        speechRecognizer.setRecognitionListener(SearchRecognitionListener(this::updateResults))

        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en")

        this.speech_button.setOnClickListener {
            speechRecognizer.startListening(speechIntent)
        }
    }

    fun updateResults(result: String?) {
        this.text_results.text = result
    }


}
