package net.dudmy.dicdol.ui.groupdetail

import net.dudmy.dicdol.data.Album
import net.dudmy.dicdol.data.Artist
import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.data.source.GroupDataSource
import net.dudmy.dicdol.data.source.GroupRepository

class GroupDetailPresenter(private var view: GroupDetailContract.View?,
                           private var artistAdapterView: GroupDetailAdapterContract.View,
                           private var artistAdapterModel: GroupDetailAdapterContract.Model,
                           private var albumAdapterView: GroupDetailAdapterContract.View,
                           private var albumAdapterModel: GroupDetailAdapterContract.Model) : GroupDetailContract.Presenter {

    private val repository = GroupRepository

    private var group: Group? = null

    init {
        artistAdapterView.clickListener = { onArtistClick(it) }
        albumAdapterView.clickListener = { onAlbumClick(it) }
    }

    override fun detachView() {
        this.view = null
    }

    override fun loadGroup(id: Int) {
        view!!.setLoadingIndicator(true)

        repository.getGroup(id, object : GroupDataSource.LoadGroupCallback {
            override fun onGroupLoaded(group: Group) {
                this@GroupDetailPresenter.group = group

                view?.run {
                    showGroup(group)

                    artistAdapterModel.addItems(group.artists)
                    artistAdapterView.refresh()

                    albumAdapterModel.addItems(group.albums)
                    albumAdapterView.refresh()

                    setLoadingIndicator(false)
                }
            }

            override fun onDataNotAvailable() {
                view?.run {
                    setLoadingIndicator(false)
                    showLoadingGroupError()
                }
            }
        })
    }

    override fun changeFavorite() {
        if (group == null) {
            view?.showLoadingGroupError()
            return
        }

        group?.let {
            it.favorite = !it.favorite
            repository.saveGroup(it)

            view?.toastFavoriteChanged(it.favorite, it.name)
        }
    }

    override fun onArtistClick(position: Int) {
        val artist = artistAdapterModel.getItem(position)
        view?.showArtistPage(artist as Artist)
    }

    override fun onAlbumClick(position: Int) {
        val album = albumAdapterModel.getItem(position)
        view?.showAlbumPage(album as Album)
    }
}