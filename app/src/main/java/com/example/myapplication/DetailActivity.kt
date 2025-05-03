package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    lateinit var tituloDetail: TextView
    lateinit var autorDetail: TextView
    lateinit var editorialDetail: TextView
    lateinit var precioDetail: TextView
    lateinit var portadaDetail: ImageView
    lateinit var descripcionDetail: TextView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        button = findViewById(R.id.button)
        tituloDetail = findViewById(R.id.tvTituloDetail)
        autorDetail = findViewById(R.id.tvAutorDetail)
        editorialDetail = findViewById(R.id.tvEditorialDetail)
        precioDetail = findViewById(R.id.tvPrecioDetail)
        portadaDetail = findViewById(R.id.ivPortadaDetail)
        descripcionDetail = findViewById(R.id.tvDescripcionDetail)

//        Toma los datos transmitidos desde MainActivity.
        val bundle = intent.extras
        tituloDetail.text = bundle?.getString("titulo","")
        autorDetail.text = bundle?.getString("autor","")
        editorialDetail.text = "Editorial " + bundle?.getString("editorial", "")
        precioDetail.text = "$ " + bundle?.getString("precio", "")
        Glide.with(this)
            .load(bundle?.getString("portadaURL", ""))
            .into(portadaDetail)
        descripcionDetail.text = bundle?.getString("descripcion", "")

//        Botón para volver a la página anterior y seguir navegando el listado de libros.
        button.setOnClickListener {
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }
    }
}