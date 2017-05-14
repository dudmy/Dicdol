package net.dudmy.dicdol.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.dudmy.dicdol.BaseActivity
import net.dudmy.dicdol.R
import net.dudmy.dicdol.ui.group.GroupFragment

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        showGroupPage(null)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_home) {
            setTitle(R.string.app_name)
            showGroupPage(null)
        } else if (id == R.id.nav_boy) {
            setTitle(R.string.boy)
            showGroupPage("boy")
        } else if (id == R.id.nav_girl) {
            setTitle(R.string.girl)
            showGroupPage("girl")
        } else if (id == R.id.nav_favorite) {
            setTitle(R.string.favorite)
            showGroupPage("favorite")
        } else if (id == R.id.nav_help) {
            startSettingPage("help")
        } else if (id == R.id.nav_setting) {
            startSettingPage("setting")
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showGroupPage(type: String?) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, GroupFragment.newInstance(type), GroupFragment.TAG)
                .commit()
    }

    private fun startSettingPage(type: String) {
        val intent = Intent(this, SettingActivity::class.java)
        intent.putExtra("type", type)
        startActivity(intent)
    }
}
