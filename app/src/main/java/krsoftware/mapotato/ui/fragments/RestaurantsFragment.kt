package krsoftware.mapotato.ui.fragments


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_restaurants.*
import krsoftware.mapotato.R
import krsoftware.mapotato.ui.adapters.RestaurantsAdapter
import krsoftware.mapotato.StatusEnum
import krsoftware.mapotato.inflate
import krsoftware.mapotato.model.Place
import krsoftware.mapotato.retrofit.MapsAPIService

/**
 * A simple [Fragment] subclass.
 */
class RestaurantsFragment : Fragment() {
    private val mapsAPI: MapsAPIService = MapsAPIService()
    private lateinit var query: String
    private lateinit var adapter: RestaurantsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        query = arguments.getString(ARG_QUERY)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return container?.inflate(R.layout.fragment_restaurants, inflater = inflater)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapsAPI.search(query, 5000, "45.4276550,-75.6824510")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                    when (StatusEnum.valueOf(response.status)) {
                        StatusEnum.OK, StatusEnum.ZERO_RESULTS -> {
                            updateSearchResults(response.results.map { it.toModel() })
                        }
                        StatusEnum.OVER_QUERY_LIMIT, StatusEnum.INVALID_REQUEST, StatusEnum.REQUEST_DENIED -> {
                            Toast.makeText(context, response.error_message ?: "An error occurred", Toast.LENGTH_SHORT).show()
                        }
                    }
                }, this::onNetworkError)
    }

    private fun updateSearchResults(results: List<Place>) {
        showProgress(false)

        adapter = RestaurantsAdapter(results)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context)
    }

    private fun showProgress(show: Boolean) {
        val showView = if (show) loading_indicator else recycler_view
        val hideView = if (show) recycler_view else loading_indicator

        fadeAnimation(showView, hideView)
    }

    private fun onNetworkError(e: Throwable) {
        Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
        Log.e(TAG, "Network error", e)
    }

    private fun fadeAnimation(showView: View, hideView: View) {
        val animTime: Long = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        showView.alpha = 0f
        showView.visibility = View.VISIBLE

        showView.animate()
                .alpha(1f)
                .setDuration(animTime)
                .setListener(null)

        hideView.animate()
                .alpha(0f)
                .setDuration(animTime)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        hideView.visibility = View.GONE
                    }
                })
    }

    companion object {
        private const val TAG = "RestaurantsFragment"
        private const val ARG_QUERY = "query"

        fun newInstance(query: String): RestaurantsFragment {
            val fragment = RestaurantsFragment()
            val args = Bundle()
            args.putString(ARG_QUERY, query)
            fragment.arguments = args
            return fragment
        }
    }
}
