package com.example.dsm_proyecto_api

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CRUDActivity : AppCompatActivity() {

    // variables para credenciales de autenticación
    var auth_username = ""
    var auth_password = ""
    var api_url = ""
    var tipo_registro = ""

    //FB Auth obj
    private lateinit var auth: FirebaseAuth

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlumnoAdapter
    private lateinit var api: APIAlumno


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crudalumnos)

        //check login status
        auth = FirebaseAuth.getInstance()
        this.checkUser()

        // Obtención de datos que envia actividad anterior
        val datos: Bundle? = intent.getExtras()
        if (datos != null) {
            api_url = datos.getString("api_url").toString()
            tipo_registro = datos.getString("tipo_registro").toString()
        }

        //get credentials from resource file
        auth_username = resources.getString(R.string.api_user_auth)
        auth_password = resources.getString(R.string.api_pass_auth)

        val fab_agregar: FloatingActionButton = findViewById<FloatingActionButton>(R.id.fab_agregar)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Crea un cliente OkHttpClient con un interceptor que agrega las credenciales de autenticación
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", Credentials.basic(auth_username, auth_password))
                    .build()
                chain.proceed(request)
            }
            .build()

        // Crea una instancia de Retrofit con el cliente OkHttpClient
        val retrofit = Retrofit.Builder()
            .baseUrl(api_url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        // Crea una instancia del servicio que utiliza la autenticación HTTP básica
        api = retrofit.create(APIAlumno::class.java)

        cargarDatos(api)

        // Cuando el usuario quiere agregar un nuevo registro
        fab_agregar.setOnClickListener(View.OnClickListener {
            val i = Intent(getBaseContext(), CrearAlumnoActivity::class.java)
            i.putExtra("auth_username", auth_username)
            i.putExtra("auth_password", auth_password)
            i.putExtra("tipo_registro", tipo_registro)
            i.putExtra("api_url",api_url)
            startActivity(i)
        })
    }

    private fun cargarDatos(api: APIAlumno) {
        val call = api.obtenerAlumnos()
        call.enqueue(object : Callback<List<Alumno>> {
            override fun onResponse(call: Call<List<Alumno>>, response: Response<List<Alumno>>) {
                if (response.isSuccessful) {
                    val alumnos = response.body()
                    if (alumnos != null) {
                        adapter = AlumnoAdapter(alumnos)
                        recyclerView.adapter = adapter

                        // Establecemos el escuchador de clics en el adaptador
                        adapter.setOnItemClickListener(object : AlumnoAdapter.OnItemClickListener {
                            override fun onItemClick(alumno: Alumno) {
                                val opciones = arrayOf("Modificar $tipo_registro", "Eliminar $tipo_registro")

                                AlertDialog.Builder(this@CRUDActivity)
                                    .setTitle(alumno.nombre)
                                    .setItems(opciones) { dialog, index ->
                                        when (index) {
                                            0 -> Modificar(alumno)
                                            1 -> eliminarAlumno(alumno, api)
                                        }
                                    }
                                    .setNegativeButton("Cancelar", null)
                                    .show()
                            }
                        })
                    }
                } else {
                    val error = response.errorBody()?.string()
                    Log.e("API", "Error al obtener los registros: $error")
                    Toast.makeText(
                        this@CRUDActivity,
                        "Error al obtener los registros 1",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Alumno>>, t: Throwable) {
                Log.e("API", "Error al obtener los registros: ${t.message}")
                Toast.makeText(
                    this@CRUDActivity,
                    "Error al obtener los registros 2",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun Modificar(alumno: Alumno) {
        // Creamos un intent para ir a la actividad de actualización de alumnos
        val i = Intent(getBaseContext(), ActualizarAlumnoActivity::class.java)
        // Pasamos el ID del alumno seleccionado a la actividad de actualización
        i.putExtra("alumno_id", alumno.id)
        i.putExtra("nombre", alumno.nombre)
        i.putExtra("apellido", alumno.apellido)
        i.putExtra("edad", alumno.edad)
        i.putExtra("tipo_registro", tipo_registro)
        i.putExtra("api_url",api_url)

        // Iniciamos la actividad de actualización de alumnos
        startActivity(i)
    }

    private fun eliminarAlumno(alumno: Alumno, api: APIAlumno) {
        val alumnoTMP = Alumno(alumno.id,"", "", -987)
        Log.e("API", "id : $alumno")
        val llamada = api.eliminarAlumno(alumno.id, alumnoTMP)
        llamada.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@CRUDActivity, "$tipo_registro eliminado", Toast.LENGTH_SHORT).show()
                    cargarDatos(api)
                } else {
                    val error = response.errorBody()?.string()
                    Log.e("API", "Error al eliminar $tipo_registro : $error")
                    Toast.makeText(this@CRUDActivity, "Error al eliminar $tipo_registro 1", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("API", "Error al eliminar $tipo_registro : $t")
                Toast.makeText(this@CRUDActivity, "Error al eliminar $tipo_registro 2", Toast.LENGTH_SHORT).show()
            }
        })
    }

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
    private fun goToLogin(){
        val itent = Intent(this, LoginActivity::class.java)
        startActivity(itent)
    }

}