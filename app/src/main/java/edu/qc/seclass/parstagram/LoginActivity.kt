package edu.qc.seclass.parstagram

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val currentUser = ParseUser.getCurrentUser()
        if(currentUser != null){
            goToMainActivity()
        }

        findViewById<Button>(R.id.login_button).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            loginUser(username,password)
        }

        findViewById<Button>(R.id.signup_button).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            signUpUser(username,password)
        }


    }

    private fun signUpUser(username: String, password: String){
        val user = ParseUser()

// Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                Toast.makeText(this, "Account created successfully",Toast.LENGTH_SHORT).show()
                goToMainActivity()
            } else {
                Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                goToMainActivity()
                // Hooray!  The user is logged in.
            } else {
                e.printStackTrace()
                Toast.makeText(this, "Error Logging in",Toast.LENGTH_SHORT).show()

            }})
        )
    }





    private fun goToMainActivity(){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object{
        val TAG = "LoginActity"
    }
}

