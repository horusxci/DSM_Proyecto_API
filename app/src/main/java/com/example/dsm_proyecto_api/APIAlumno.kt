package com.example.dsm_proyecto_api

import retrofit2.Call
import retrofit2.http.*

interface APIAlumno {
    @GET("alumno.php")
    fun obtenerAlumnos(): Call<List<Alumno>>

    @GET("alumno/{id}")
    fun obtenerAlumnoPorId(@Path("id") id: Int): Call<Alumno>

    @POST("alumno")
    fun crearAlumno(@Body alumno: Alumno): Call<Alumno>

    @PUT("alumno/{id}")
    fun actualizarAlumno(@Path("id") id: Int, @Body alumno: Alumno): Call<Alumno>

    @PUT("alumno/{id}")
    fun eliminarAlumno(@Path("id") id: Int, @Body alumno: Alumno): Call<Void>
}