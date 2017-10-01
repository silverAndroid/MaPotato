package krsoftware.mapotato.ui.fragments

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_speech.*
import krsoftware.mapotato.R
import krsoftware.mapotato.SearchRecognitionListener
import krsoftware.mapotato.inflate
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

/**
 * Created by silver_android on 01/10/17.
 */
@RuntimePermissions
class SpeechFragment : Fragment() {
    private val speechObservable: PublishSubject<String> = PublishSubject.create()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_speech, inflater = inflater)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeSpeechEngineWithPermissionCheck()
    }

    @NeedsPermission(Manifest.permission.RECORD_AUDIO)
    fun initializeSpeechEngine() {
        val speechRecognizer: SpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        speechRecognizer.setRecognitionListener(SearchRecognitionListener(speechObservable))

        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en")

        this.speech_button.setOnClickListener {
            speechRecognizer.startListening(speechIntent)
        }
    }

    fun getSpeechListener(): PublishSubject<String> = speechObservable

    companion object {
        fun newInstance(): SpeechFragment = SpeechFragment()
    }
}