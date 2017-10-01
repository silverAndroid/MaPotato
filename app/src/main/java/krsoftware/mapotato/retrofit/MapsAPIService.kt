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
class MapsAPIService private constructor() {
    private val api: MapsAPI

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        api = retrofit.create(MapsAPI::class.java)
    }

    fun search(query: String, radius: Int, location: String = "12.45,31.95") : Single<SearchResults> = api.search(query, location, radius)

    interface MapsAPI {
        @GET("/maps/api/place/nearbysearch/json")
        fun search(@Query("keyword") query: String, @Query("location") location: String = "123.45,231.45", @Query("radius") radius: Int, @Query("key") key: String = BuildConfig.API_KEY) : Single<SearchResults>
    }

    companion object {
        val instance: MapsAPIService = MapsAPIService()
    }
}