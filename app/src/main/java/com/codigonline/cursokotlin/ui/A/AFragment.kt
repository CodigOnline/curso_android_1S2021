package com.codigonline.cursokotlin.ui.A

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codigonline.cursokotlin.R
import com.codigonline.cursokotlin.databinding.ActivityABinding
import com.codigonline.cursokotlin.databinding.FragmentABinding
import com.codigonline.cursokotlin.ui.B.BActivity
import com.codigonline.cursokotlin.ui.B.BFragment

class AFragment : Fragment() {


    private var binding: FragmentABinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //binding = FragmentABinding.inflate(layoutInflater)
        binding = FragmentABinding.inflate(inflater, container, false)

        val view = binding!!.root

        binding!!.fragmentAButon1.setOnClickListener {
            clicarBoton(1)
        }
        binding!!.fragmentAButon2.setOnClickListener {
            clicarBoton(2)
        }
        binding!!.fragmentAButon3.setOnClickListener {
            clicarBoton(3)
        }
        binding!!.fragmentAButon4.setOnClickListener {
            clicarBoton(4)
        }
        binding!!.fragmentAButon5.setOnClickListener {
            clicarBoton(5)
        }

        return view
    }

    fun clicarBoton(boton: Int) {
        val main = activity as AActivity //Casteo
        val mainBinding =
            ActivityABinding.bind(main.binding.root) //Vinculamos al constraint layout del activity A

        if (mainBinding.activityAFrameLayout != null) { //NO ES NULO
            val fragment = BFragment.newInstance(boton)

            parentFragmentManager //fragment manager
                .beginTransaction() //Fragment transaction
                .replace(mainBinding.activityAFrameLayout.id, fragment) //fragment transaction
                .commit()
           /*val manager = parentFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(mainBinding.activityAFrameLayout.id, fragment)
            transaction.commit()*/

        } else {
            val intent = Intent(this.context, BActivity::class.java)
            intent.putExtra("BOTON_PULSADO", boton)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }
}