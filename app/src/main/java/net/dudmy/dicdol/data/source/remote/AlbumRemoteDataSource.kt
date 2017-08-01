package net.dudmy.dicdol.data.source.remote

import com.google.gson.JsonElement
import net.dudmy.dicdol.data.Album
import net.dudmy.dicdol.data.mapper.AlbumApiMapper
import net.dudmy.dicdol.data.source.AlbumDataSource
import net.dudmy.dicdol.services.AlbumService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumRemoteDataSource : AlbumDataSource {

    override fun getAlbum(id: Int, callback: AlbumDataSource.LoadAlbumCallback) {
        val albumService = AlbumService.retrofit.create(AlbumService::class.java)
        val call = albumService.getAlbum(id)

        call.enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>?, response: Response<JsonElement>?) {
                if (response?.isSuccessful ?: false) {
                    Album().apply {
                        AlbumApiMapper().doMaterialize(response!!.body(), this)
                        callback.onAlbumLoaded(this)
                    }
                } else {
                    callback.onDataNotAvailable()
                }
            }

            override fun onFailure(call: Call<JsonElement>?, t: Throwable?) {
                t?.printStackTrace()
                callback.onDataNotAvailable()
            }
        })
    }

    override fun saveAlbum(album: Album) {
        // Not required.
    }
}