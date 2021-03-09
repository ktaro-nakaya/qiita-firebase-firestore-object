package jp.co.casareal.firestoreobjectandreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import jp.co.casareal.firestoreobjectandreference.fragment.MainFragmentDirections

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.optionAddUser) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment
            val navHostController = navHostFragment.navController
            navHostController.navigate(MainFragmentDirections.actionMainFragmentToAddFragment())
        }
        return super.onOptionsItemSelected(item)
    }
}