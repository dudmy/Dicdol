package net.dudmy.dicdol.ui

import android.content.Intent
import android.os.Bundle
import net.dudmy.dicdol.BaseActivity

/**
 * Created by yujin on 2017. 4. 23..
 */

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish()
    }
}