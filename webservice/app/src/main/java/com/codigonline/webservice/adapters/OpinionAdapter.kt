package com.codigonline.webservice.adapters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.webservice.R
import com.codigonline.webservice.databinding.ItemOpinionBinding
import com.codigonline.webservice.services.entities.Opinion
import java.util.*

class OpinionAdapter : RecyclerView.Adapter<OpinionAdapter.ViewHolder>() {
    val lista = mutableListOf<Opinion>()
    fun addOpiniones(lista: List<Opinion>) {
        this.lista.addAll(lista)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpinionAdapter.ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: OpinionAdapter.ViewHolder, position: Int) {
        holder.rellanarDatos(lista[position])
        setAnimations(holder.itemView)
    }

    private fun setAnimations(view: View) {
        val pos = 500f
        val init = 0f
        val duration = 600L
        view.translationX = pos
        view.alpha = 0f
        val set = AnimatorSet()
        val translateX = ObjectAnimator.ofFloat(view, "translationX", pos, init)
        translateX.duration = duration
        val changeAlpha = ObjectAnimator.ofFloat(view, "alpha", 1f)
        changeAlpha.duration = duration
        set.playTogether(translateX, changeAlpha)
        set.start()
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    class ViewHolder(val binding: ItemOpinionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun rellanarDatos(op: Opinion) {
            binding.opinionName.text = op.nombre
            binding.opinionDesc.text = op.descripcion
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemOpinionBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    val itemMover = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            val from = viewHolder.adapterPosition
            val to = target.adapterPosition
            Collections.swap(lista, from, to)
            notifyItemMoved(from, to)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            TODO("Not yet implemented")
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            super.onSelectedChanged(viewHolder, actionState)
            if (actionState == ACTION_STATE_DRAG) {
                viewHolder?.itemView?.alpha = 0.5f
                viewHolder?.itemView?.setBackgroundColor(viewHolder.itemView.context.getColor(R.color.design_default_color_secondary))
            }
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            super.clearView(recyclerView, viewHolder)
            viewHolder.itemView.alpha = 1f
            viewHolder?.itemView?.setBackgroundColor(viewHolder.itemView.context.getColor(R.color.white))
        }

    }
}