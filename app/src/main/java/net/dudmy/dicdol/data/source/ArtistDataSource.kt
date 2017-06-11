package net.dudmy.dicdol.data.source

import net.dudmy.dicdol.data.Artist

/**
 * Created by yujin on 2017. 6. 11..
 */

interface ArtistDataSource {

    interface LoadArtistCallback {

        fun onArtistLoaded(artist: Artist)

        fun onDataNotAvailable()
    }

    fun getArtist(id: Int, callback: LoadArtistCallback)

    fun saveArtist(artist: Artist)
}