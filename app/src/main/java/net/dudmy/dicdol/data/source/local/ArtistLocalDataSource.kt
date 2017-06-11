package net.dudmy.dicdol.data.source.local

import io.realm.Realm
import net.dudmy.dicdol.data.Artist
import net.dudmy.dicdol.data.source.ArtistDataSource

class ArtistLocalDataSource : ArtistDataSource {

    override fun getArtist(id: Int, callback: ArtistDataSource.LoadArtistCallback) {
        Realm.getDefaultInstance()
                .where(Artist::class.java)
                .equalTo("id", id)
                .findFirst()
                .let {
                    if (it == null || it.needRefresh()) {
                        callback.onDataNotAvailable()
                    } else {
                        callback.onArtistLoaded(it)
                    }
                }
    }

    override fun saveArtist(artist: Artist) {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                it.copyToRealmOrUpdate(artist)
            }
        }
    }
}