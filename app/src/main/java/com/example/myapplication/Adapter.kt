package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

/*
* Adapter recibe el contexto de la aplicación desde MainActivity y retorna un ListAdapter, un adaptador estándar para RecyclerView.
* Es capaz de mostrar objetos ViewHolder, los cuales representan datos de objetos Libro.
* */
class Adapter(val context: Context): ListAdapter<Libro, Adapter.ViewHolder>(DiffCallBack) {

//    Retorna un objeto Libro sobre el que se haga click, no espera recibir nada en respuesta.
    lateinit var onItemClickListener: (Libro) -> Unit

    /*
    * ViewHolder es un contenedor que describe una view.
    * View es un bloque de interfaz que, en este caso, se genera a partir de la layout definida en libro_list_item.xml.
    * Una vez que se creó la estructura para el objeto ViewHolder, la función bind lo a asocia los datos de un libro.
    * */
    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val tituloView: TextView = view.findViewById(R.id.tvTitulo)
        private val autorView: TextView = view.findViewById(R.id.tvAutor)
        private val editorialView: TextView = view.findViewById(R.id.tvEditorial)
        private val precioView: TextView = view.findViewById(R.id.tvPrecio)
        private val portadaView: ImageView = view.findViewById(R.id.ivPortada)

        fun bind(libro: Libro) {
            tituloView.text = libro.titulo
            autorView.text = libro.autor
            editorialView.text = "Editorial " + libro.editorial
            precioView.text = "$ " + libro.precio
            Glide.with(context)
                .load(libro.portadaURL)
                .into(portadaView)

            view.setOnClickListener {
                onItemClickListener(libro)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

//        "Infla" una view a partir del recurso que se pasa como parámetro, dentro de RecyclerView.
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.libro_list_item, parent, false)
        return ViewHolder(view)
    }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//         Busca un objeto almacenado en determinada posición del listado de Adapter y lo asocia al ViewHolder creado.
         val libro = getItem(position)
         holder.bind(libro)
     }

    /*
    * Objeto asociado a la clase Adapter. Retorna un a función callback que eventualmente puede ser llamada por el adaptador retornado por el constructor.
    * */
    companion object DiffCallBack: DiffUtil.ItemCallback<Libro>() {
        override fun areItemsTheSame(oldItem: Libro, newItem: Libro): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Libro, newItem: Libro): Boolean {
            return oldItem == newItem
        }
    }
}