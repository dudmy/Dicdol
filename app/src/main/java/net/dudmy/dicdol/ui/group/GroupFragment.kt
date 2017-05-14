package net.dudmy.dicdol.ui.group

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_group.*
import net.dudmy.dicdol.BaseFragment
import net.dudmy.dicdol.R
import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.ui.groupdetail.GroupDetailActivity
import net.dudmy.dicdol.ui.views.LinearOffsetDecoration
import net.dudmy.dicdol.util.toast

/**
 * Created by yujin on 2017. 4. 23..
 */

class GroupFragment : BaseFragment(), GroupContract.View {

    private lateinit var groupPresenter: GroupPresenter

    private val buttonList by lazy { listOf(btn_sort_name, btn_sort_agency) }

    private var type: String? = null

    companion object {
        const val TAG = "GroupFragment"

        fun newInstance(type: String?): GroupFragment {
            val bundle = Bundle()
            bundle.putString("type", type)

            return GroupFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_group

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        type = arguments.getString("type")

        val groupAdapter = GroupAdapter()

        recycler_view.run {
            layoutManager = LinearLayoutManager(context)
            adapter = groupAdapter
            addItemDecoration(LinearOffsetDecoration(30))
        }

        refresh_layout.setOnRefreshListener {
            groupPresenter.loadGroups(type, true)
        }

        buttonList.map { it.setOnClickListener { groupPresenter.sortGroups(it) } }

        groupPresenter = GroupPresenter(this, groupAdapter, groupAdapter)
    }

    override fun onResume() {
        super.onResume()
        groupPresenter.loadGroups(type, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        groupPresenter.detachView()
    }

    override fun setLoadingIndicator(active: Boolean) {
        refresh_layout.isRefreshing = active
    }

    override fun showLoadingGroupsError() {
        context.toast("Loading Error")
    }

    override fun toastOutOfPosition() {
        context.toast("Position Error")
    }

    override fun selectCurrentButton(selectButton: View) {
        buttonList.map { it.isSelected = false }
        selectButton.isSelected = true
    }

    override fun startArtistPage(group: Group) {
        val intent = Intent(activity, GroupDetailActivity::class.java)
        intent.putExtra("id", group.id)
        startActivity(intent)
    }
}