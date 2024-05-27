package io.github.josephgm.boxofficeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val retrofit = Retrofit.Builder()
    .baseUrl("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface ApiService {
    @GET("searchDailyBoxOfficeList.json")
    suspend fun getMovies(@Query("key") apiKey: String, @Query("targetDt") targetDate: String? = "20240524"):DailyBoxOfficeResponse
}
val apiService: ApiService = retrofit.create(ApiService::class.java)