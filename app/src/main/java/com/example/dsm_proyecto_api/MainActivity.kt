package com.example.dsm_proyecto_api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    //FB Auth obj
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //check login status
        auth = FirebaseAuth.getInstance()
        this.checkUser()
    }

    //inflater del menu de la aplicacion
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return  true
    }

    //menu options switch case
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_crud_alumno->{
                this.goToAlumno()
            }
            R.id.item_crud_profesor->{
                this.goToProfesor()
            }
            R.id.item_sign_out->{
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this,"Sesion cerrada", Toast.LENGTH_SHORT).show()

                    val intn = Intent(this, LoginActivity::class.java)
                    startActivity(intn)
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }



    //check whether or not user is logged
    private fun checkUser(){
        if(auth.currentUser == null){
            //redirect to Login
            goToLogin()
            finish()
        }
        else{
            Toast.makeText(applicationContext,"Bienvenido", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToRegister(){
        val intn = Intent(this,RegisterActivity::class.java)
        startActivity(intn)
    }
    private fun goToLogin(){
        val itent = Intent(this, LoginActivity::class.java)
        startActivity(itent)
    }
    private fun goToAlumno(){
        val intn = Intent(this, CRUDActivity::class.java)
        intn.putExtra("api_url", resources.getString(R.string.api_alumno))
        intn.putExtra("tipo_registro", "Alumno")
        startActivity(intn)
    }
    private fun goToProfesor(){
        val intn = Intent(this, CRUDActivity::class.java)
        intn.putExtra("api_url", resources.getString(R.string.api_profesor))
        intn.putExtra("tipo_registro", "Profesor")
        startActivity(intn)
    }
}