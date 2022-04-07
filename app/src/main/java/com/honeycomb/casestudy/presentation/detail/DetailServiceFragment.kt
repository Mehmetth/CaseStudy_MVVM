package com.honeycomb.casestudy.presentation.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.honeycomb.casestudy.R
import com.honeycomb.casestudy.databinding.FragmentDetailServiceBinding
import com.honeycomb.casestudy.databinding.FragmentHomeBinding
import com.honeycomb.casestudy.presentation.home.AllServicesViewModel
import com.honeycomb.casestudy.presentation.home.ServicesState
import com.honeycomb.casestudy.presentation.home.adapter.AllServiceAdapter
import com.honeycomb.casestudy.utils.AndroidUtils
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.popular_service_rv_item.view.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailServiceFragment : Fragment(R.layout.fragment_detail_service) {

    private var _binding: FragmentDetailServiceBinding? = null
    private val binding get() = _binding!!

    private val detailServiceViewModel : DetailServiceViewModel by viewModels()

    val args : DetailServiceFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailServiceBinding.bind(view)

        observeDetailService()
        observeDetailServiceState()
        detailServiceViewModel.fetchDetailServices(args.serviceId!!)
    }

    //ServiceDetail Value and State Handle
    private fun observeDetailService(){
        detailServiceViewModel.mUsers
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { service ->

                if(service != null){
                    Picasso.get()
                        .load(service.image_url)
                        .placeholder(R.drawable.image_placeholder)
                        .error(R.drawable.error)
                        .into(binding.detailServiceImage)
                    binding.detailServiceName.text = service.long_name
                    binding.avarageRatingTextValue.text = service.average_rating.toString()
                    binding.prosNearTextValue.text = service.pro_count.toString()
                    binding.lastMonthTextValue.text = service.completed_jobs_on_last_month.toString()
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
    private fun observeDetailServiceState(){
        detailServiceViewModel.mState
            .flowWithLifecycle (viewLifecycleOwner.lifecycle,  Lifecycle.State.STARTED)
            .onEach { state ->
                handleDetailServiceState(state)
            }
            .launchIn (viewLifecycleOwner.lifecycleScope)
    }
    private fun handleDetailServiceState(state: DetailServicesState){
        when(state){
            is DetailServicesState.IsLoading -> handleDetailServiceLoading(state.isLoading)
            is DetailServicesState.IsError -> handleDetailServiceError(state.isError)
            is DetailServicesState.IsErrorMessage -> handleDetailServiceErrorMessage(state.isErrorValue)
            is DetailServicesState.Init -> Unit
        }
    }
    private fun handleDetailServiceLoading(isLoading: Boolean){
        AndroidUtils.showServiceProgressBar(binding.detailImageErrorProgressBar,isLoading)
    }
    private fun handleDetailServiceError(isError: Boolean){
        AndroidUtils.showServiceErrorImage(binding.detailImageErrorImage,isError)
    }
    private fun handleDetailServiceErrorMessage(isErrorValue: String){
        AndroidUtils.showServiceError(requireView(),isErrorValue)
    }

}