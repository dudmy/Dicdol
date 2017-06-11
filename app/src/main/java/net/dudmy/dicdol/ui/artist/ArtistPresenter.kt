package net.dudmy.dicdol.ui.artist

import net.dudmy.dicdol.data.Artist
import net.dudmy.dicdol.data.source.ArtistDataSource
import net.dudmy.dicdol.data.source.ArtistRepository

class ArtistPresenter(private var view: ArtistContract.View?,
                      private val repository: ArtistRepository) : ArtistContract.Presenter {

    override fun detachView() {
        this.view = null
    }

    override fun loadArtist(id: Int) {
        view!!.setLoadingIndicator(true)

        repository.getArtist(id, object : ArtistDataSource.LoadArtistCallback {
            override fun onArtistLoaded(artist: Artist) {
                view?.run {
                    showArtist(artist)
                    setLoadingIndicator(false)
                }
            }

            override fun onDataNotAvailable() {
                view?.run {
                    showLoadingArtistError()
                    setLoadingIndicator(false)
                }
            }
        })
    }
}