package net.dudmy.dicdol.data.source.remote

import com.google.gson.JsonElement
import net.dudmy.dicdol.data.Artist
import net.dudmy.dicdol.data.mapper.ArtistApiMapper
import net.dudmy.dicdol.data.source.ArtistDataSource
import net.dudmy.dicdol.services.ArtistService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistRemoteDataSource : ArtistDataSource {

    override fun getArtist(id: Int, callback: ArtistDataSource.LoadArtistCallback) {
        val artistService = ArtistService.retrofit.create(ArtistService::class.java)
        val call = artistService.getArtist(id)

        call.enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>?, response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    Artist().apply {
                        ArtistApiMapper().doMaterialize(response.body(), this)
                        callback.onArtistLoaded(this)
                    }
                } else {
                    callback.onDataNotAvailable()
                }
            }

            override fun onFailure(call: Call<JsonElement>?, t: Throwable) {
                t.printStackTrace()
                callback.onDataNotAvailable()
            }
        })
    }

    override fun saveArtist(artist: Artist) {
        // Not required.
    }
}