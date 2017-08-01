package net.dudmy.dicdol.ui.album

import net.dudmy.dicdol.BasePresenter
import net.dudmy.dicdol.BaseView
import net.dudmy.dicdol.data.Album

/**
 * Created by yujin on 2017. 8. 1..
 */

interface AlbumContract {

    interface Presenter : BasePresenter {

        fun loadAlbum(id: Int)
    }

    interface View : BaseView {

        fun showAlbum(album: Album)

        fun setLoadingIndicator(active: Boolean)

        fun showLoadingAlbumError()
    }
}