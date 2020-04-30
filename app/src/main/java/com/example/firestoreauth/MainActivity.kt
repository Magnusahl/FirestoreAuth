package com.example.firestoreauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var textEmail : EditText
    lateinit var textPassword : EditText
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textEmail = findViewById(R.id.editTextEmail)
        textPassword = findViewById(R.id.editTextPassword)
        auth = FirebaseAuth.getInstance()

        val createButton = findViewById<Button>(R.id.buttonCreate)
        createButton.setOnClickListener{
            createUser()
        }

        val loginButton = findViewById<Button>(R.id.buttonLogin)
        loginButton.setOnClickListener {
            loginUser()
        }

    }

    fun goToAddActivity() {
        val intent = Intent(this, AddItemActivity::class.java)
        startActivity(intent)
    }

    fun loginUser() {
        auth.signInWithEmailAndPassword(textEmail.text.toString(), textPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    println("!!! User logged in")
                } else {
                    Snackbar.make(textEmail, "User not loggedIn", Snackbar.LENGTH_LONG)
                    println("! User Not logged in !")
                }
            }
    }

    fun createUser() {
        auth.createUserWithEmailAndPassword(textEmail.text.toString(), textPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    println("!!! User created")
                } else {
                    Snackbar.make(textEmail, "User not created", Snackbar.LENGTH_LONG)
                    println("! User Not created !")
                }

            }
    }

}


