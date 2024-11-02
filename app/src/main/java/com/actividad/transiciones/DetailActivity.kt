package com.actividad.transiciones

import android.os.Bundle
import android.transition.TransitionInflater
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var imageViewDetail: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imageViewDetail = findViewById(R.id.imageViewDetail)

        // Configura la transición
        val transition = TransitionInflater.from(this).inflateTransition(R.transition.image_transition)
        window.sharedElementEnterTransition = transition

        // Obtén la imagen que se pasó en el Intent
        // Obtén la imagen que se pasó en el Intent
        val imageResId = intent.getIntExtra("IMAGE_RES_ID", -1) // Cambia esto por tu imagen por defecto
        if (imageResId != -1) {
            imageViewDetail.setImageResource(imageResId)
        } else {
            // Manejar el caso en que no se recibe la imagen correctamente
            imageViewDetail.setImageResource(R.drawable.imagen1) // Cambia esto por tu imagen por defecto
        }


        // Activa la transición compartida
        setSharedElementTransition()
    }

    private fun setSharedElementTransition() {
        supportPostponeEnterTransition()
        imageViewDetail.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }
}