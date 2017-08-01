package net.dudmy.dicdol.ui.album

import android.os.Bundle
import net.dudmy.dicdol.BaseActivity
import net.dudmy.dicdol.data.Album
import net.dudmy.dicdol.data.source.AlbumRepository
import net.dudmy.dicdol.util.toast

/**
 * Created by yujin on 2017. 8. 1..
 */

class AlbumActivity : BaseActivity(), AlbumContract.View {

    private val id: Int by lazy { intent.getIntExtra("id", -1) }

    private lateinit var albumPresenter: AlbumPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableHomeAsUp()

        albumPresenter = AlbumPresenter(this, AlbumRepository).apply {
            loadAlbum(id)
        }
    }

    override fun showAlbum(album: Album) {
        album.let {
            title = it.name

            // TODO: Update view with data.
        }
    }

    override fun setLoadingIndicator(active: Boolean) {
        // TODO: Change active about loading indicator.
    }

    override fun showLoadingAlbumError() {
        baseContext.toast("Loading Error")
    }
}