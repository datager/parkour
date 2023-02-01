package com.bignerdranch.android.jetpack3navigationuitest

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController


class MainActivity : AppCompatActivity() {
    private var appBarConfiguration: AppBarConfiguration? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController!!.addOnDestinationChangedListener { _, _, _ ->
            Toast.makeText(this@MainActivity, "onDestinationChanged() called", Toast.LENGTH_SHORT).show()
        }
        //绑定当前的ActionBar，除此之外NavigationUI还能绑定Toolbar和CollapsingToolbarLayout
        //绑定后，系统会默认处理ActionBar左上角区域，为你添加返回按钮，将所切换到的Fragment在导航图里的name属性中的内容显示到Title
        appBarConfiguration = AppBarConfiguration.Builder(navController!!.graph).build()
        setupActionBarWithNavController(this, navController!!, appBarConfiguration!!)
    }

    // 加载菜单
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_settings, menu)
        return true
    }

    // ActionBar中的按钮被点击时，根据菜单中的Id，自动跳转到相应的页面
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, navController!!) || super.onOptionsItemSelected(item);
    }

    // 左上角的返回按钮被点击时调用到
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController!!, appBarConfiguration!!) || super.onSupportNavigateUp();
    }
}