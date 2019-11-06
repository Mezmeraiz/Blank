package com.mezmeraiz.blank.ui.paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mezmeraiz.blank.R
import com.mezmeraiz.blank.adapters.PagedAdapter
import com.mezmeraiz.blank.common.MarginDecoration
import com.mezmeraiz.blank.model.NetworkState
import com.mezmeraiz.blank.model.Status
import com.mezmeraiz.blank.snackBar
import com.mezmeraiz.blank.ui.info.InfoFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_paging.*
import javax.inject.Inject

class PagingFragment: DaggerFragment() {

    private val layoutId = R.layout.fragment_paging

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var marginDecoration: MarginDecoration

    lateinit var viewModel: PagingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PagingViewModel::class.java)
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PagedAdapter(viewModel, viewModel.diffCallback, R.layout.item_paging)
        adapter.onItemClick.observe(this, Observer {
            it.item?.let {
                InfoFragment.start(view, it)
            }
        })
        pagingRecyclerView.adapter = adapter
        pagingRecyclerView.addItemDecoration(marginDecoration)
        viewModel.pagedList.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.loadMoreState.observe(this, Observer {
            adapter.loadMoreState = it == NetworkState.LOADING
            if (it.status == Status.FAILED){
                snackBar(it.msg)
            }
        })
        viewModel.loadInitialState.observe(this, Observer {
            if (it != NetworkState.LOADING){
                swipeRefresh.isRefreshing = false
            }
            if (it.status == Status.FAILED){
                snackBar(it.msg)
            }
        })
        swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

}