package com.actividad.transiciones.fragments

import android.content.Intent
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.core.app.ActivityOptionsCompat
import com.actividad.transiciones.MainActivity
import com.actividad.transiciones.R
import com.actividad.transiciones.DetailActivity // Asegúrate de importar DetailActivity

class FragmentA : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var buttonAnimate: Button

    // Variables para almacenar el estado original
    private var isAnimated = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_a, container, false)

        imageView = view.findViewById(R.id.imageViewA)
        textView = view.findViewById(R.id.textViewA)
        buttonAnimate = view.findViewById(R.id.buttonAnimate)

        val buttonNavigate = view.findViewById<Button>(R.id.buttonNavigate)
        buttonNavigate.setOnClickListener {
            (activity as MainActivity).replaceFragment(FragmentB())
        }

        buttonAnimate.setOnClickListener {
            animateViews() // Llamar a la función de animación
        }

        // Configura el clic en el ImageView para iniciar DetailActivity con la transición compartida
        imageView.setOnClickListener {
            val intent = Intent(activity, DetailActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(), imageView, "image_transition"
            )
            startActivity(intent, options.toBundle())  // Asegúrate de que esto esté aquí
        }





        return view
    }

    private fun animateViews() {
        // Configura la transición
        val changeBounds = ChangeBounds()
        changeBounds.duration = 500 // Duración de la animación en milisegundos

        // Inicia la animación
        TransitionManager.beginDelayedTransition(view as ViewGroup, changeBounds)

        if (!isAnimated) {
            // Cambia las propiedades de ImageView y TextView al estado animado
            val params = imageView.layoutParams
            params.width = 800 // Cambia el tamaño del ImageView
            params.height = 800 // Aumenta el tamaño del ImageView
            imageView.layoutParams = params

            // Cambia la posición del TextView
            textView.translationY = 50f // Mueve el TextView hacia abajo
            textView.text = "Animación Completa" // Cambia el texto como un efecto adicional
        } else {
            // Restaura las propiedades a su estado original
            val params = imageView.layoutParams
            params.width = 550 // Restaura el tamaño original del ImageView
            params.height = 550 // Asegúrate de que este valor sea adecuado
            imageView.layoutParams = params

            // Restaura la posición del TextView
            textView.translationY = 0f // Restaura la posición original
            textView.text = "Ejemplo de textView" // Restaura el texto original
        }

        // Alternar el estado de animación
        isAnimated = !isAnimated
    }
}
