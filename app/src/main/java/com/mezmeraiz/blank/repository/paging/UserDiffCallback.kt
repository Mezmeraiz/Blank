package com.mezmeraiz.blank.repository.paging

import androidx.recyclerview.widget.DiffUtil
import com.mezmeraiz.blank.model.User
import javax.inject.Inject

class UserDiffCallback @Inject constructor() : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.name.last == newItem.name.last
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.name.first == newItem.name.first &&
                oldItem.picture.thumbnail == newItem.picture.thumbnail
    }

}