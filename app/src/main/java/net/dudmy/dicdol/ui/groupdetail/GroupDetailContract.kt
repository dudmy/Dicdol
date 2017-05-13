package net.dudmy.dicdol.ui.groupdetail

import net.dudmy.dicdol.BasePresenter
import net.dudmy.dicdol.BaseView
import net.dudmy.dicdol.data.Album
import net.dudmy.dicdol.data.Artist
import net.dudmy.dicdol.data.Group

/**
 * Created by yujin on 2017. 4. 26..
 */

interface GroupDetailContract {

    interface Presenter : BasePresenter {

        fun loadGroup(id: Int, forceUpdate: Boolean)

        fun onArtistClick(position: Int)

        fun onAlbumClick(position: Int)
    }

    interface View : BaseView {

        fun showGroup(group: Group)

        fun setLoadingIndicator(active: Boolean)

        fun showLoadingGroupError()

        fun showArtistPage(artist: Artist)

        fun showAlbumPage(album: Album)
    }
}