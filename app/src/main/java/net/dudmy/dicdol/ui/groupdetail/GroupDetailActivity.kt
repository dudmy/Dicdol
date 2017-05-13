package net.dudmy.dicdol.ui.groupdetail

import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_group_detail.*
import kotlinx.android.synthetic.main.content_group_detail.*
import net.dudmy.dicdol.BaseActivity
import net.dudmy.dicdol.R
import net.dudmy.dicdol.data.Album
import net.dudmy.dicdol.data.Artist
import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.ui.views.GridOffsetDecoration
import net.dudmy.dicdol.util.loadImage
import net.dudmy.dicdol.util.toast

class GroupDetailActivity : BaseActivity(), GroupDetailContract.View {

    private val id: Int by lazy { intent.getIntExtra("id", -1) }

    private lateinit var groupDetailPresenter: GroupDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_detail)
        setSupportActionBar(toolbar)

        enableHomeAsUp()

        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        refresh_layout.setOnRefreshListener { groupDetailPresenter.loadGroup(id, true) }

        val artistAdapter = GroupDetailAdapter()
        val albumAdapter = GroupDetailAdapter()

        rv_artists.run {
            adapter = artistAdapter
            addItemDecoration(GridOffsetDecoration(40))
        }
        rv_albums.run {
            adapter = albumAdapter
            addItemDecoration(GridOffsetDecoration(40))
        }

        groupDetailPresenter = GroupDetailPresenter(this, artistAdapter, artistAdapter, albumAdapter, albumAdapter)
        groupDetailPresenter.loadGroup(id, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        groupDetailPresenter.detachView()
    }

    override fun showGroup(group: Group) {
        group.let {
            title = it.name
            iv_group.loadImage(it.getImgUrl())

            tv_agency.text = it.agency
            tv_type.text = it.getTypeStr()
            tv_debut.text = it.debut
            tv_debut_song.text = it.debutSong
        }
    }

    override fun setLoadingIndicator(active: Boolean) {
        refresh_layout.isRefreshing = active
    }

    override fun showLoadingGroupError() {
        baseContext.toast("Loading Error")
    }

    override fun showArtistPage(artist: Artist) {
        baseContext.toast("click $artist")
    }

    override fun showAlbumPage(album: Album) {
        baseContext.toast("click $album")
    }
}
