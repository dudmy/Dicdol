package net.dudmy.dicdol.ui.artist

import net.dudmy.dicdol.BasePresenter
import net.dudmy.dicdol.BaseView
import net.dudmy.dicdol.data.Artist

/**
 * Created by yujin on 2017. 6. 11..
 */

interface ArtistContract {

    interface Presenter : BasePresenter {

        fun loadArtist(id: Int)
    }

    interface View : BaseView {

        fun showArtist(artist: Artist)

        fun setLoadingIndicator(active: Boolean)

        fun showLoadingArtistError()
    }
}