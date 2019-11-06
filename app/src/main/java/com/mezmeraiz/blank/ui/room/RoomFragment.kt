package com.mezmeraiz.blank.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mezmeraiz.blank.R
import com.mezmeraiz.blank.adapters.RecyclerAdapter
import com.mezmeraiz.blank.common.MarginDecoration
import com.mezmeraiz.blank.model.Status
import com.mezmeraiz.blank.snackBar
import com.mezmeraiz.blank.ui.info.InfoFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_room.*
import javax.inject.Inject

class RoomFragment: DaggerFragment() {

    private val layoutId = R.layout.fragment_room

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var marginDecoration: MarginDecoration

    lateinit var viewModel: RoomViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RoomViewModel::class.java)
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.users.observe(this, Observer {
            when (it.status){
                Status.SUCCESS ->{
                    it.data?.let {
                        val adapter = RecyclerAdapter(viewModel, it, R.layout.item_room)
                        adapter.onItemClick.observe(this, Observer {
                            it.item?.let {
                                InfoFragment.start(view, it)
                            }
                        })
                        roomRecyclerView.adapter = adapter
                        roomRecyclerView.addItemDecoration(marginDecoration)
                    }
                }
                Status.FAILED -> snackBar(it.message)
            }
        })
    }

}