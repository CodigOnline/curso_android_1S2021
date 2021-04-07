package com.codigonline.firebase.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.codigonline.firebase.App
import com.codigonline.firebase.R
import com.codigonline.firebase.databinding.FragmentProfileBinding
import com.codigonline.firebase.entities.Usuario
import com.codigonline.firebase.utils.Constantes
import com.codigonline.firebase.viewModel.UsuarioViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.UserProfileChangeRequest


class ProfileFragment : Fragment() {

    private val TAG = "PROFILE_FRAGMENT"
    private val CODE_IMAGE_PICK = 1
    private val CODE_IMAGE_CAM = 2


    private var uriFoto: Uri? = null
    private var _binding: FragmentProfileBinding? = null
    private val model: UsuarioViewModel by viewModels()
    private lateinit var usuario: Usuario


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val binding = _binding!!
        val view = binding.root
        binding.progressLayout.myProgressBar.visibility = View.VISIBLE
        model.findOneById(App.getAuth().currentUser.uid).observe(viewLifecycleOwner, {
            usuario = it
            if (!usuario.imageUrl.isNullOrBlank()) {

                val circularProgress = CircularProgressDrawable(requireContext())
                circularProgress.strokeWidth = 5f
                circularProgress.centerRadius = 30f
                circularProgress.start()

                Glide
                        .with(this)
                        .load(usuario.imageUrl)
                        .fitCenter()
                        .placeholder(circularProgress)
                        .into(binding.imageProfile)


            }
            binding.tieEdad.setText(it.edad.toString())
            binding.tieNombre.setText(it.nombre)
            binding.tieEmail.setText(it.email)
            binding.progressLayout.myProgressBar.visibility = View.GONE
        })

        binding.imageProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, CODE_IMAGE_PICK)
        }

        binding.btnActualizar.setOnClickListener {
            save()
        }
        return view
    }

    fun save() {
        if (uriFoto != null) {
            val storage = App.getStorage()
            val reference =
                    storage.reference.child(Constantes.IMAGES + "/" + App.getAuth().currentUser.uid + "/image_profile")
            reference.putFile(uriFoto!!)
                    .addOnProgressListener {
                        val progress = (100 * it.bytesTransferred / it.totalByteCount).toDouble()
                        Log.d(TAG, progress.toString())
                    }
                    .addOnSuccessListener {
                        it.storage.downloadUrl.addOnSuccessListener { url ->
                            Log.d(TAG, url.toString())
                            usuario.imageUrl = url.toString()
                            saveUser()

                        }
                    }
                    .addOnFailureListener {
                        Snackbar.make(
                                _binding!!.root,
                                "No se ha podido subir la imagen a Storage",
                                Snackbar.LENGTH_SHORT
                        ).show()
                    }

        } else {
            saveUser()
        }
    }

    fun saveUser() {
        model.updateUsuario(usuario).observe(viewLifecycleOwner, {
            if (it) {
                Snackbar.make(_binding!!.root, "Usuario Actualizado", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(_binding!!.root, "ERROR: al actualizar", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODE_IMAGE_PICK -> {
                if (resultCode == RESULT_OK) {
                    uriFoto = data?.data
                    _binding!!.imageProfile.setImageURI(uriFoto)
                }
            }
            CODE_IMAGE_CAM -> {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}