package net.dudmy.dicdol.data.source

import net.dudmy.dicdol.data.Album

/**
 * Created by yujin on 2017. 8. 1..
 */

interface AlbumDataSource {

    interface LoadAlbumCallback {

        fun onAlbumLoaded(album: Album)

        fun onDataNotAvailable()
    }

    fun getAlbum(id: Int, callback: LoadAlbumCallback)

    fun saveAlbum(album: Album)
}