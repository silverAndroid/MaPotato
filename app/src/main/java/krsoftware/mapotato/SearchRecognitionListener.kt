package krsoftware.mapotato

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import io.reactivex.subjects.PublishSubject

/**
 * Created by kkung on 2017-09-30.
 */

class SearchRecognitionListener(private val updateResults: PublishSubject<String>) : RecognitionListener {
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

    override fun onError(errorCode: Int) {
        println("Error $errorCode")
    }

    override fun onResults(bundleResults: Bundle?) {
        val results = bundleResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        results?.let {
            updateResults.onNext(results[0])
        }
    }
}