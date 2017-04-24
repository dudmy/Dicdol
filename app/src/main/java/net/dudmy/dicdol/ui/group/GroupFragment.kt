package net.dudmy.dicdol.ui.group

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_group.*
import net.dudmy.dicdol.BaseFragment
import net.dudmy.dicdol.R
import net.dudmy.dicdol.data.source.GroupRepository
import net.dudmy.dicdol.ui.views.OffsetItemDecoration

/**
 * Created by yujin on 2017. 4. 23..
 */

class GroupFragment : BaseFragment(), GroupContract.View {

    private lateinit var groupPresenter: GroupPresenter

    private val groupAdapter: GroupAdapter by lazy { GroupAdapter() }

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

        recycler_view.run {
            layoutManager = LinearLayoutManager(context)
            adapter = groupAdapter
            addItemDecoration(OffsetItemDecoration(30))
        }

        refresh_layout.setOnRefreshListener {
            groupPresenter.loadGroups(type, true)
        }

        groupPresenter = GroupPresenter(this, groupAdapter, groupAdapter, GroupRepository())
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
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }
}