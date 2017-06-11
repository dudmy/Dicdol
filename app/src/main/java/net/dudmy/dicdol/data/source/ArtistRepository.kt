package net.dudmy.dicdol.data.source

import net.dudmy.dicdol.data.Artist
import net.dudmy.dicdol.data.source.ArtistDataSource.LoadArtistCallback
import net.dudmy.dicdol.data.source.local.ArtistLocalDataSource
import net.dudmy.dicdol.data.source.remote.ArtistRemoteDataSource

object ArtistRepository : ArtistDataSource {

    private val remoteDataSource by lazy { ArtistRemoteDataSource() }

    private val localDataSource by lazy { ArtistLocalDataSource() }

    override fun getArtist(id: Int, callback: LoadArtistCallback) {
        localDataSource.getArtist(id, object : LoadArtistCallback {
            override fun onArtistLoaded(artist: Artist) {
                callback.onArtistLoaded(artist)
            }

            override fun onDataNotAvailable() {
                getArtistFromRemoteDataSource(id, callback)
            }
        })
    }

    private fun getArtistFromRemoteDataSource(id: Int, callback: LoadArtistCallback) {
        remoteDataSource.getArtist(id, object : LoadArtistCallback {
            override fun onArtistLoaded(artist: Artist) {
                refreshLocalDataSource(artist)
                callback.onArtistLoaded(artist)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    override fun saveArtist(artist: Artist) {
        // Not required.
    }

    private fun refreshLocalDataSource(artist: Artist) {
        localDataSource.saveArtist(artist)
    }
}