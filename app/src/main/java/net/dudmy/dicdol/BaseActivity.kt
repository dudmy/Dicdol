package net.dudmy.dicdol

import android.support.v7.app.AppCompatActivity

/**
 * Created by yujin on 2017. 4. 23..
 */

open class BaseActivity : AppCompatActivity() {

    fun enableHomeAsUp() {
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    fun disableHomeAsUp() {
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
        }
    }
}