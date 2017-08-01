package net.dudmy.dicdol.ui.album

import net.dudmy.dicdol.data.Album
import net.dudmy.dicdol.data.source.AlbumDataSource
import net.dudmy.dicdol.data.source.AlbumRepository

class AlbumPresenter(
        private var view: AlbumContract.View?,
        private val repository: AlbumRepository
) : AlbumContract.Presenter {

    override fun detachView() {
        this.view = null
    }

    override fun loadAlbum(id: Int) {
        view!!.setLoadingIndicator(true)

        repository.getAlbum(id, object : AlbumDataSource.LoadAlbumCallback {
            override fun onAlbumLoaded(album: Album) {
                view?.run {
                    showAlbum(album)
                    setLoadingIndicator(false)
                }
            }

            override fun onDataNotAvailable() {
                view?.run {
                    showLoadingAlbumError()
                    setLoadingIndicator(false)
                }
            }
        })
    }
}