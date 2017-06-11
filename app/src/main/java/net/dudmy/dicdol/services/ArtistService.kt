package net.dudmy.dicdol.services

import com.google.gson.JsonElement
import net.dudmy.dicdol.util.assetsPrefix
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by yujin on 2017. 4. 26..
 */

interface ArtistService {

    companion object {
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                    .baseUrl(assetsPrefix)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
    }

    @GET("artist/{id}.json")
    fun getArtist(@Path("id") id: Int): Call<JsonElement>
}