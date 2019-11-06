package com.mezmeraiz.blank.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mezmeraiz.blank.BR
import com.mezmeraiz.blank.R
import com.mezmeraiz.blank.common.SingleLiveEvent
import com.mezmeraiz.blank.model.ItemClickValue

class PagedAdapter<M>(private val viewModel: ViewModel,
                      diffCallback: DiffUtil.ItemCallback<M>,
                      private val itemLayout: Int,
                      private val progressLayout: Int = R.layout.progress_paging) :
    PagedListAdapter<M, RecyclerView.ViewHolder>(diffCallback) {

    val onItemClick = SingleLiveEvent<ItemClickValue<M>>()

    var loadMoreState = false
        set(value) {
            if (field != value){
                field = value
                if (field){
                    notifyItemInserted(itemCount)
                }else{
                    notifyItemRemoved(itemCount)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            itemLayout -> PagedViewHolder(LayoutInflater.from(parent.context)
                .inflate(itemLayout, parent, false))
            progressLayout -> ProgressViewHolder(LayoutInflater.from(parent.context)
                .inflate(progressLayout, parent, false))
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (loadMoreState) 1 else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType != itemLayout) return
        holder as PagedViewHolder
        val item = getItem(position)
        holder.binding?.setVariable(BR.viewModel, viewModel)
        holder.binding?.setVariable(BR.item, item)
        holder.binding?.setVariable(BR.position, position)
        holder.binding?.executePendingBindings()
        holder.itemView?.setOnClickListener { onItemClick.value = ItemClickValue(position, item) }
    }

    override fun getItemViewType(position: Int): Int {
        return if (loadMoreState && position == itemCount - 1) {
            progressLayout
        } else {
            itemLayout
        }
    }

    class PagedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var binding: ViewDataBinding? = DataBindingUtil.bind(itemView)
    }

    class ProgressViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}
