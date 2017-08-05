package net.dudmy.dicdol.ui.album

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_album.*
import kotlinx.android.synthetic.main.content_album.*
import net.dudmy.dicdol.BaseActivity
import net.dudmy.dicdol.R
import net.dudmy.dicdol.data.Album
import net.dudmy.dicdol.data.source.AlbumRepository
import net.dudmy.dicdol.ui.views.LinearOffsetDecoration
import net.dudmy.dicdol.util.loadImage
import net.dudmy.dicdol.util.toast

/**
 * Created by yujin on 2017. 8. 1..
 */

class AlbumActivity : BaseActivity(), AlbumContract.View {

    private val id: Int by lazy { intent.getIntExtra("id", -1) }

    private lateinit var songAdapter: SongAdapter

    private lateinit var albumPresenter: AlbumPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        setSupportActionBar(toolbar)

        enableHomeAsUp()

        songAdapter = SongAdapter()

        rv_songs.run {
            layoutManager = LinearLayoutManager(this@AlbumActivity)
            adapter = songAdapter
            addItemDecoration(LinearOffsetDecoration(30))
        }

        albumPresenter = AlbumPresenter(this, AlbumRepository).apply {
            loadAlbum(id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        albumPresenter.detachView()
    }

    override fun showAlbum(album: Album) {
        album.let {
            title = it.name

            tv_name.text = it.name
            tv_day.text = it.day
            tv_publishing.text = it.publishing
            tv_agency.text = it.agency
            tv_genre.text = it.genre

            iv_album.loadImage(it.image)

            songAdapter.addItems(it.getSongs())
        }
    }

    override fun setLoadingIndicator(active: Boolean) {
        progress_bar.visibility = if (active) View.VISIBLE else View.GONE
    }

    override fun showLoadingAlbumError() {
        baseContext.toast("Loading Error")
    }
}