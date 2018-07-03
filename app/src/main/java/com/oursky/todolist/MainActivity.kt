package com.oursky.todolist

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    enum class DrawerView {
        TODO, FINISHED,
    }

    private val currentDrawerViewKey = "currentDrawerViewKey"
    private var mCurrentDrawerView = DrawerView.TODO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if (savedInstanceState != null) {
            mCurrentDrawerView = DrawerView.values()[savedInstanceState.getInt(currentDrawerViewKey)]
        }

        goToCurrentDrawerView()
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
        when (item.itemId) {
            R.id.nav_todo -> {
                switchToTodoList()
            }
            R.id.nav_finished -> {
                switchToFinishedList()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putInt(currentDrawerViewKey, mCurrentDrawerView.ordinal)
    }

    private fun goToCurrentDrawerView() {
        if (mCurrentDrawerView == DrawerView.TODO) {
            switchToTodoList()
        } else {
            switchToFinishedList()
        }
    }

    private fun switchToTodoList() {
        setTitle(R.string.action_bar_title_todo_list)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, TodoListFragment())
        ft.commit()
        mCurrentDrawerView = DrawerView.TODO
    }

    private fun switchToFinishedList() {
        setTitle(R.string.action_bar_title_finish_list)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, FinishedListFragment())
        ft.commit()
        mCurrentDrawerView = DrawerView.FINISHED
    }

}
