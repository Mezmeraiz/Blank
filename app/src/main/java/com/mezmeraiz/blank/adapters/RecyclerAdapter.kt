package com.mezmeraiz.blank.adapters

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.mezmeraiz.blank.BR
import com.mezmeraiz.blank.common.SingleLiveEvent
import com.mezmeraiz.blank.model.ItemClickValue

open class RecyclerAdapter<M>(private val viewModel: ViewModel,
                              private val data: List<M>,
                              private val layout: Int) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    val onItemClick = SingleLiveEvent<ItemClickValue<M>>()

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = data[position]
        holder?.binding?.setVariable(BR.viewModel, viewModel)
        holder?.binding?.setVariable(BR.item, item)
        holder?.binding?.setVariable(BR.position, position)
        holder?.binding?.executePendingBindings()
        holder?.itemView?.setOnClickListener { onItemClick.value = ItemClickValue(position, item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount() : Int  {
        return data.size
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ViewDataBinding? = DataBindingUtil.bind(itemView)
    }

}

