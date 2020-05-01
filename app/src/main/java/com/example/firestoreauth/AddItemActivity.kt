package com.example.firestoreauth

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AddItemActivity : AppCompatActivity() {

    lateinit var db : FirebaseFirestore
    lateinit var textField : EditText
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        textField = findViewById(R.id.editText)
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            saveItem()
        }


    }

    fun saveItem() {
        val item = Item(textField.text.toString(), false , "mat")
        val user = auth.currentUser
        if (user == null)
            return

        db.collection("users").document(user!!.uid).collection("items").add(item)
            .addOnSuccessListener{
            println("write")
        }

            .addOnFailureListener {
                println("did not write")
            }
    }


}
