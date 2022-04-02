package edu.qc.seclass.parstagram

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import edu.qc.seclass.parstagram.fragments.ComposeFragment
import edu.qc.seclass.parstagram.fragments.FeedFragment
import edu.qc.seclass.parstagram.fragments.ProfileFragment
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        findViewById<Button>(R.id.logOutBtn).setOnClickListener{
            logoutUser()
        }


        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
           item ->
            var fragmentToShow: Fragment? = null
            when(item.itemId){
                R.id.action_home -> {
                    fragmentToShow = FeedFragment()

                }
                R.id.action_compose -> {
                    fragmentToShow = ComposeFragment()
                }
                R.id.action_profile -> {
                    fragmentToShow = ProfileFragment()
                }
            }

            if (fragmentToShow!=null){
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            true
        }

       findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home


    }


    private fun goToLogin(){
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun logoutUser(){
        ParseUser.logOut()
        val currentUser = ParseUser.getCurrentUser() // this will now be null
        goToLogin()
    }

    companion object{
        const val TAG = "MainActivity"
    }
}
