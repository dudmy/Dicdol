package net.dudmy.dicdol.data.source

import net.dudmy.dicdol.data.Album
import net.dudmy.dicdol.data.source.AlbumDataSource.LoadAlbumCallback
import net.dudmy.dicdol.data.source.local.AlbumLocalDataSource
import net.dudmy.dicdol.data.source.remote.AlbumRemoteDataSource

object AlbumRepository : AlbumDataSource {

    private val remoteDataSource by lazy { AlbumRemoteDataSource() }

    private val localDataSource by lazy { AlbumLocalDataSource() }

    override fun getAlbum(id: Int, callback: LoadAlbumCallback) {
        localDataSource.getAlbum(id, object : LoadAlbumCallback {
            override fun onAlbumLoaded(album: Album) {
                callback.onAlbumLoaded(album)
            }

            override fun onDataNotAvailable() {
                getAlbumFromRemoteDataSource(id, callback)
            }
        })
    }

    private fun getAlbumFromRemoteDataSource(id: Int, callback: LoadAlbumCallback) {
        remoteDataSource.getAlbum(id, object : LoadAlbumCallback {
            override fun onAlbumLoaded(album: Album) {
                refreshLocalDataSource(album)
                callback.onAlbumLoaded(album)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    private fun refreshLocalDataSource(album: Album) {
        localDataSource.saveAlbum(album)
    }

    override fun saveAlbum(album: Album) {
        // Not required.
    }
}