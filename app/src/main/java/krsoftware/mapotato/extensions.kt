package krsoftware.mapotato

import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by silver_android on 30/09/17.
 */

fun ViewGroup.inflate(@LayoutRes resource: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(resource, this, attachToRoot)

fun AppCompatActivity.showFragment(fragment: Fragment, addToStack: Boolean = false, name: String? = "") {
    val transaction = supportFragmentManager.beginTransaction()

    transaction.replace(R.id.container, fragment)
    if (addToStack)
        transaction.addToBackStack(name)
    transaction.commit()
}