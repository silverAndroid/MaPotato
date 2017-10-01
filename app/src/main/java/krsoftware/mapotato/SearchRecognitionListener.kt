package krsoftware.mapotato

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer

/**
 * Created by kkung on 2017-09-30.
 */

class SearchRecognitionListener(val updateResults: (String?) -> Unit) : RecognitionListener{
    var results : ArrayList<String>? = null
    override fun onReadyForSpeech(p0: Bundle?) {
        println("Ready For Speech")
    }

    override fun onRmsChanged(p0: Float) {
        println("Rms Changed")
    }

    override fun onBufferReceived(p0: ByteArray?) {
        println("Buffer Received")
    }

    override fun onPartialResults(p0: Bundle?) {
        println("Partial Results")
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
        println("Event")
    }

    override fun onBeginningOfSpeech() {
        println("Beginning of Speech")
    }

    override fun onEndOfSpeech() {
        println("End of Speech")
    }

    override fun onError(p0: Int) {
        println("Error" + p0)
    }

    override fun onResults(bundleResults: Bundle?) {
        results = bundleResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        updateResults(results?.get(0))

    }

}