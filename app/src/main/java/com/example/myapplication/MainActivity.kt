package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class MainActivity : AppCompatActivity() {
//    Elemento que contiene otras views. Estas son los objetos ViewHolder, asociados a determinados datos.
    lateinit var recyclerView: RecyclerView

//    Proporciona views que representan datos.
    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvRecyclerView)

//        Determina de qué manera se acomodan las views en el RecyclerView. LinearLayoutManager funciona como un ListView, es decir, los elementos se ordenan verticalmente en un listado scrolleable.
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = Adapter(applicationContext)
        recyclerView.adapter = adapter

        adapter.submitList(getBookList())

        /*
        * Desde Adapter, se instancian objetos ViewHolder que se asocian a los datos de un libro y luego son retornados al RecyclerView para mostrar datos dentro de él.
        * Cada ViewHolder tiene un listener que retorna un libro cuando se hace click en él.
        * Desde MainActivity, se crea un Intent con el contexto actual y la actividad a la cual deben derivarse los datos, DetailActivity.
        * Una vez que se inicialize esta activity, se puede acceder a los datos de ese libro en particular.
        * */
        adapter.onItemClickListener = { libro ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("titulo", libro.titulo)
            intent.putExtra("autor", libro.autor)
            intent.putExtra("editorial", libro.editorial)
            intent.putExtra("precio", libro.precio.toString())
            intent.putExtra("portadaURL", libro.portadaURL)
            intent.putExtra("descripcion", libro.descripcion)
            startActivity(intent)
        }
    }
}

private fun getBookList(): MutableList<Libro> {
    val bookList = mutableListOf(
        Libro(1, "Los hermanos Karamazov", "Fiódor Dostoievski", "Juventud", 12320.0, "https://i.pinimg.com/736x/d8/f0/92/d8f0923f8dafa3ce1cd418ca73c35485.jpg", "Título original: Brátya Karamázovy. Edición de 2009, cuenta con 752 páginas. ISBN: 978-84-261-0598-1."),
        Libro(2, "El sueño de los héroes", "Adolfo Bioy Casares", "Nuevo Siglo", 5500.5, "https://i.pinimg.com/736x/52/46/5b/52465b23b8a2148cfbdace647438f021.jpg", "Edición de 1994, cuenta con 219 páginas. ISBN: 987-9049-57-8."),
        Libro(3, "Crimen y castigo", "Fiódor Dostoievski", "Planeta", 10000.0, "https://i.pinimg.com/736x/67/60/3a/67603a559aafd554dc87556e460c701c.jpg", "Título original: Prestuplenie i nakazanie. Edición de 1998, cuenta con 504 páginas. ISBN: 84-08-01883-3"),
        Libro(4, "El retrato de Dorian Gray", "Oscar Wilde", "Losada", 11500.7, "https://i.pinimg.com/736x/17/46/bf/1746bf61af2ed592540a036fa0f443bb.jpg", "Título original: The Picture of Dorian Gray. Edición de 2007, cuenta con 290 páginas. ISBN: 978-950-03-7401-9"),
        Libro(5, "Memorias del subsuelo", "Fiódor Dostoievski", "Libertador", 7500.0, "https://i.pinimg.com/736x/94/45/87/94458737d44da4019bbc5789dfa80072.jpg", "Título original: Zapiski iz podpolya. Edición de 2009, cuenta con 185 páginas. ISBN: 978-987-1512-17-1."),
        Libro(6, "Ficciones", "Jorge Luis Borges", "Alianza", 4223.5, "https://i.pinimg.com/736x/b3/75/a3/b375a3e3b36d0c25f209a702db9d890b.jpg", "Edición de 1996, cuenta con 208 páginas. ISBN: 84-395-8721."),
        Libro(7, "El Aleph", "Jorge Luis Borges", "Emecé", 5000.0, "https://i.pinimg.com/736x/26/ab/33/26ab3310a4aae9743b34a4e65a3024dd.jpg", "Edición de 1994, cuenta con 273 páginas. ISBN: 950-04-0112-6."),
        Libro(8, "El extranjero", "Albert Camus", "Emecé", 9560.0, "https://i.pinimg.com/736x/fe/63/95/fe639576085cb9c889dfb8e05b2c3913.jpg", "Título original: L'étranger. Edición de 1994, cuenta con 175 páginas. ISBN: 950-04-0013-8."),
        Libro(9, "Rebeca", "Daphne du Maurier", "Círculo de Lectores", 8500.0, "https://i.pinimg.com/736x/78/d2/8d/78d28de474ce1e1809890329fd75a0e2.jpg", "Título original: Rebecca. Edición de 1974, cuenta con 385 páginas. ISBN: 89-226-0590-2."),
        Libro(10, "El idiota", "Fiódor Dostoievski", "Juventud", 12350.6, "https://i.pinimg.com/736x/58/73/d4/5873d4bc161cf4f9837e3e08e19fbd75.jpg", "Título original: Idiot. Edición de 1999, cuenta con 774 páginas. ISBN: 84-261-0545-9.")
    )
    return bookList
}