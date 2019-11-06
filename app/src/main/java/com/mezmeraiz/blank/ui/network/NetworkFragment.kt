package com.mezmeraiz.blank.ui.network

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
import kotlinx.android.synthetic.main.fragment_network.*
import javax.inject.Inject

class NetworkFragment: DaggerFragment() {

    private val layoutId = R.layout.fragment_network

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var marginDecoration: MarginDecoration

    lateinit var viewModel: NetworkViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NetworkViewModel::class.java)
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.users.observe(this, Observer {
            when (it.status){
                Status.SUCCESS ->{
                    it.data?.let {
                        val adapter = RecyclerAdapter(viewModel, it, R.layout.item_network)
                        adapter.onItemClick.observe(this, Observer {
                            it.item?.let {
                                InfoFragment.start(view, it)
                            }
                        })
                        networkRecyclerView.adapter = adapter
                        networkRecyclerView.addItemDecoration(marginDecoration)
                    }
                }
                Status.FAILED -> snackBar(it.message)
            }
        })
    }

}