package net.dudmy.dicdol.ui.artist

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_artist.*
import kotlinx.android.synthetic.main.content_artist.*
import net.dudmy.dicdol.BaseActivity
import net.dudmy.dicdol.R
import net.dudmy.dicdol.data.Artist
import net.dudmy.dicdol.data.source.ArtistRepository
import net.dudmy.dicdol.util.toast

/**
 * Created by yujin on 2017. 6. 11..
 */

class ArtistActivity : BaseActivity(), ArtistContract.View {

    private val id: Int by lazy { intent.getIntExtra("id", -1) }

    private lateinit var artistPresenter: ArtistPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)
        setSupportActionBar(toolbar)

        enableHomeAsUp()

        artistPresenter = ArtistPresenter(this, ArtistRepository).apply {
            loadArtist(id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        artistPresenter.detachView()
    }

    override fun showArtist(artist: Artist) {
        artist.let {
            title = it.name

            tv_name.text = "${it.name} (${it.fullname})"
            tv_gender.text = it.getGenderStr()
            tv_birth.text = it.birth
            tv_nationality.text = it.nationality
            tv_agency.text = it.agency
            tv_debut.text = it.debut

            view_pager.adapter = ArtistPagerAdapter(baseContext, it.getPhotoList())
            indicator.setViewPager(view_pager)
        }
    }

    override fun setLoadingIndicator(active: Boolean) {
        progress_bar.visibility = if (active) View.VISIBLE else View.GONE
    }

    override fun showLoadingArtistError() {
        baseContext.toast("Loading Error")
    }
}