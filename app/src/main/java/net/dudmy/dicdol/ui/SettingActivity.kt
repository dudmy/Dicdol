package net.dudmy.dicdol.ui

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_setting.*
import net.dudmy.dicdol.BaseActivity
import net.dudmy.dicdol.R

/**
 * Created by yujin on 2017. 4. 24..
 */

class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setSupportActionBar(toolbar)

        enableHomeAsUp()

        when (intent.getStringExtra("type")) {
            "help" -> setTitle(R.string.help)
            "setting" -> setTitle(R.string.setting)
            else -> title = "Unknown"
        }
    }
}