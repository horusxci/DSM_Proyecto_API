package com.example.dsm_proyecto_api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class RegisterActivity : AppCompatActivity() {

    //referencia a obj firebase
    private lateinit var auth:FirebaseAuth
    //ref layout elems
    private lateinit var buttonRegister:Button
    private lateinit var textViewLogin:TextView

    //firebase listener
    private lateinit var authStateListener: AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //init object
        auth = FirebaseAuth.getInstance()

        buttonRegister = findViewById<Button>(R.id.btnRegister)

        buttonRegister.setOnClickListener{
            val email = findViewById<EditText>(R.id.txtEmail).text.toString()
            val password = findViewById<EditText>(R.id.txtPass).text.toString()
            this.register(email,password)
        }

        textViewLogin = findViewById<TextView>(R.id.btnLoginView)
        textViewLogin.setOnClickListener{
            this.goToLogin()
        }

        this.checkUser()
    }

    //registro
    private fun register(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{ task->
            if(task.isSuccessful){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener{excep->
            Toast.makeText(applicationContext,excep.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun goToLogin(){
        val itent = Intent(this, LoginActivity::class.java)
        startActivity(itent)
    }

    private fun checkUser(){
        authStateListener = FirebaseAuth.AuthStateListener {auth->
            if(auth.currentUser != null){
                //redirect to main
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    override fun onResume(){
        super.onResume()
        auth.addAuthStateListener(authStateListener)
    }
    override fun onPause(){
        super.onPause()
        auth.addAuthStateListener(authStateListener)
    }

}