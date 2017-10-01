package krsoftware.mapotato.retrofit

import io.reactivex.Single
import krsoftware.mapotato.BuildConfig
import krsoftware.mapotato.model.SearchResults
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by silver_android on 30/09/17.
 */
class MapsAPIService {
    private val api: MapsAPI

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        api = retrofit.create(MapsAPI::class.java)
    }

    fun search(query: String, radius: Int, location: String) : Single<SearchResults> = api.search(query, location, radius)

    interface MapsAPI {
        @GET("/maps/api/place/nearbysearch/json")
        fun search(@Query("keyword") query: String, @Query("location") location: String, @Query("radius") radius: Int, @Query("key") key: String = BuildConfig.API_KEY) : Single<SearchResults>
    }
}