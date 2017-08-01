package net.dudmy.dicdol.data.source.local

import io.realm.Realm
import net.dudmy.dicdol.data.Album
import net.dudmy.dicdol.data.source.AlbumDataSource

class AlbumLocalDataSource : AlbumDataSource {

    override fun getAlbum(id: Int, callback: AlbumDataSource.LoadAlbumCallback) {
        Realm.getDefaultInstance()
                .where(Album::class.java)
                .equalTo("id", id)
                .findFirst()
                .let {
                    if (it == null || it.needRefresh()) {
                        callback.onDataNotAvailable()
                    } else {
                        callback.onAlbumLoaded(it)
                    }
                }
    }

    override fun saveAlbum(album: Album) {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                it.copyToRealmOrUpdate(album)
            }
        }
    }
}