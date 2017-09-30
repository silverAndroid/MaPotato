package krsoftware.mapotato

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by silver_android on 30/09/17.
 */

fun AppCompatActivity.showFragment(fragment: Fragment, addToStack: Boolean = false, name: String? = "") {
    val transaction = supportFragmentManager.beginTransaction()

    transaction.replace(R.id.container, fragment)
    if (addToStack)
        transaction.addToBackStack(name)
    transaction.commit()
}