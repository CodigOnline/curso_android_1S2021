package com.codigonline.cursokotlin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.codigonline.cursokotlin.databinding.FragmentPlanetaBinding

//CONSTANTES
private const val NOMBRE: String = "nombre"
private const val IMAGEN: String = "imagen"

class PlanetaFragment : Fragment() {

    private lateinit var binding: FragmentPlanetaBinding

    private lateinit var nombre: String
    private lateinit var imagen: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            nombre = it!!.getString(NOMBRE)!!
            imagen = it.getString(IMAGEN)!!

        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlanetaBinding.inflate(layoutInflater)
        val view = binding.root

        return view
    }

    companion object {
        @JvmStatic
                /* fun newInstance(nombre: String, imagen: String) =
                         PlanetaFragment().apply { //AÑADEME INFORMACIÓN
                             //AÑADE ARGUMENTOS QUE SE PODRÁN USAR EN EL FRAGMENTO
                             arguments = Bundle().apply { //AÑADAME INFORMACIÓN AL ARGUMENTS
                                 //MAPA STRING, V
                                 putString(NOMBRE, nombre)
                                 putString(IMAGEN, imagen)
                             }

                         }*/

        fun newInstance(nombre: String, imagen: String) =
                PlanetaFragment().apply { //AÑADEME INFORMACIÓN
                    //AÑADE ARGUMENTOS QUE SE PODRÁN USAR EN EL FRAGMENTO
                    arguments = Bundle().apply { //AÑADAME INFORMACIÓN AL ARGUMENTS
                        //MAPA STRING, V
                        putString(NOMBRE, nombre)
                        putString(IMAGEN, imagen)
                    }
                }


    }
}