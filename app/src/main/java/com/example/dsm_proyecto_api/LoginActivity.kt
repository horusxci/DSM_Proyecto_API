package com.example.dsm_proyecto_api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


private lateinit var auth:FirebaseAuth
private lateinit var btnLogin:Button
private lateinit var textViewRegister:TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener{
            val email = findViewById<EditText>(R.id.editTextEmailAddress).text.toString()
            val password = findViewById<EditText>(R.id.txtPassword).text.toString()
            this.login(email,password)
        }

        textViewRegister = findViewById<TextView>(R.id.textViewRegister)
        textViewRegister.setOnClickListener{
            this.goToRegister()
        }

    }

    private fun login(email:String, password:String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{task->
            if(task.isSuccessful){
                val itent = Intent(this,MainActivity::class.java)
                startActivity(itent)
                finish()
            }
        }.addOnFailureListener{exc->
            Toast.makeText(applicationContext, exc.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun goToRegister(){
        val intn = Intent(this,RegisterActivity::class.java)
        startActivity(intn)
    }
}