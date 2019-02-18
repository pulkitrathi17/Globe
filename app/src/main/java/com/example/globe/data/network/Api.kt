package com.example.globe.data.network

import com.example.globe.data.network.response.NewsResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY ="70411829ddc74c02a560e76cca559238"
const val BASE_URL = "https://newsapi.org/v2/"

interface Api {

    @GET("top-headlines")
    fun fetchTopNews(@Query("country") country: String): Deferred<NewsResponse>

    @GET("everything")
    fun fetchEverything(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("to") to: String
    ):  Deferred<NewsResponse>

    companion object {
        operator fun invoke( connectivityInterceptor: ConnectivityInterceptorImpl): Api {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("apiKey", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}