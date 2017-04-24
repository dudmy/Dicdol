package net.dudmy.dicdol.ui.groupdetail

import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_group_detail.*
import net.dudmy.dicdol.BaseActivity
import net.dudmy.dicdol.R
import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.util.loadImage

class GroupDetailActivity : BaseActivity() {

    private val group: Group by lazy { intent.getParcelableExtra<Group>("group") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_detail)
        setSupportActionBar(toolbar)

        enableHomeAsUp()

        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        group.run {
            title = name
            iv_group.loadImage(image)
        }
    }
}
