package com.example.firestoreauth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.snackbar.Snackbar

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
        if (textEmail.text.toString().isEmpty() || textPassword.text.toString().isEmpty())
            return

        auth.signInWithEmailAndPassword(textEmail.text.toString(), textPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    goToAddActivity()
                    println("!!! User logged in")
                } else {
                    //Snackbar
                    println("! User Not logged in !")
                }
            }
    }

    fun createUser() {
        if (textEmail.text.toString().isEmpty() || textPassword.text.toString().isEmpty())
            return

        auth.createUserWithEmailAndPassword(textEmail.text.toString(), textPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    goToAddActivity()
                    println("!!! User created")
                } else {
                    //Snackbar.make(textEmail, "User Not created!", Snackbar.LENGTH_LONG)
                    println("! User Not created !")
                }
            }
        }

}


