package com.mezmeraiz.blank.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.mezmeraiz.blank.R
import com.mezmeraiz.blank.model.Status
import com.mezmeraiz.blank.databinding.FragmentInfoBinding
import com.mezmeraiz.blank.model.User
import com.mezmeraiz.blank.snackBar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class InfoFragment: DaggerFragment() {

    private val layoutId = R.layout.fragment_info

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: InfoViewModel

    lateinit var binding: FragmentInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(InfoViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var user = arguments?.getParcelable<User>("user")
        binding.user = user
        binding.fab.setOnClickListener {
            user?.let {
                viewModel.insertUser(user).observe(this, Observer {
                    when (it.status){
                        Status.SUCCESS ->{
                            it.data?.let {
                                snackBar(if (it != -1L) getString(R.string.saved_success) else
                                    getString(R.string.saved_error))
                            }
                        }
                        Status.FAILED -> snackBar(it.message)
                    }
                })
            }
        }
    }

    companion object {

        fun start(view: View, user: User){
            val bundle = Bundle()
            bundle.putParcelable("user", user)
            Navigation.findNavController(view).navigate(R.id.infoFragment, bundle)
        }

    }
}