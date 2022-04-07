package com.honeycomb.casestudy.presentation.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.honeycomb.casestudy.R
import com.honeycomb.casestudy.databinding.FragmentHomeBinding
import com.honeycomb.casestudy.presentation.home.adapter.AllServiceAdapter
import com.honeycomb.casestudy.presentation.home.adapter.PopularServiceAdapter
import com.honeycomb.casestudy.presentation.home.adapter.PostAdapter
import com.honeycomb.casestudy.presentation.home.viewmodel.PostState
import com.honeycomb.casestudy.presentation.home.viewmodel.PostViewModel
import com.honeycomb.casestudy.utils.AndroidUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),IClick,IPostClick {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val allServicesViewModel : AllServicesViewModel by viewModels()
    private val popularServicesViewModel : PopularServicesViewModel by viewModels()
    private val postViewModel : PostViewModel by viewModels()

    private lateinit var allServiceAdapter : AllServiceAdapter
    private lateinit var popularServiceAdapter : PopularServiceAdapter
    private lateinit var postAdapter : PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        observeAllServices()
        observeAllServicesState()
        allServicesViewModel.fetchAllServices()

        observePopularServices()
        observePopularServicesState()
        popularServicesViewModel.fetchPopularServices()

        observePost()
        observePostState()
        postViewModel.fetchPosts()

        binding.homeSearchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

    }

    //AllService Value and State Handle
    private fun observeAllServices(){
        allServicesViewModel.mUsers
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { service ->
                if(service.isNotEmpty())
                {
                    service.forEach { item ->
                        Log.d("observeServices","${item.id}  ${item.service_id} ${item.name} ${item.long_name}  ${item.image_url} ${item.pro_count}")

                    }
                    allServiceAdapter = AllServiceAdapter(requireActivity(), service,this)

                    binding.serviceRecyclerview.apply {
                        adapter = allServiceAdapter
                        layoutManager  = GridLayoutManager(requireContext(),2, GridLayoutManager.HORIZONTAL,false)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
    private fun observeAllServicesState(){
        allServicesViewModel.mState
            .flowWithLifecycle (viewLifecycleOwner.lifecycle,  Lifecycle.State.STARTED)
            .onEach { state ->
                handleAllServicesState(state)
            }
            .launchIn (viewLifecycleOwner.lifecycleScope)
    }
    private fun handleAllServicesState(state: ServicesState){
        when(state){
            is ServicesState.IsLoading -> handleAllServicesLoading(state.isLoading)
            is ServicesState.IsError -> handleAllServicesError(state.isError)
            is ServicesState.IsErrorMessage -> handleAllServicesErrorMessage(state.isErrorValue)
            is ServicesState.Init -> Unit
        }
    }
    private fun handleAllServicesLoading(isLoading: Boolean){
        AndroidUtils.showServiceProgressBar(binding.blogServiceErrorProgressBar,isLoading)
    }
    private fun handleAllServicesError(isError: Boolean){
        AndroidUtils.showServiceErrorImage(binding.blogServiceErrorImage,isError)
    }
    private fun handleAllServicesErrorMessage(isErrorValue: String){
        AndroidUtils.showServiceError(requireView(),isErrorValue)
    }

    //PopularService Value and State Handle
    private fun observePopularServices(){
        popularServicesViewModel.mUsers
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { service ->
                if(service.isNotEmpty())
                {
                    service.forEach { item ->
                        Log.d("observePopularServices","${item.id}  ${item.service_id} ${item.name} ${item.long_name}  ${item.image_url} ${item.pro_count}")

                    }
                    popularServiceAdapter = PopularServiceAdapter(service,this)

                    binding.popularRecyclerview.apply {
                        adapter = popularServiceAdapter
                        layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL,false)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
    private fun observePopularServicesState(){
        popularServicesViewModel.mState
            .flowWithLifecycle (viewLifecycleOwner.lifecycle,  Lifecycle.State.STARTED)
            .onEach { state ->
                handlePopularServicesState(state)
            }
            .launchIn (viewLifecycleOwner.lifecycleScope)
    }
    private fun handlePopularServicesState(state: PopularServicesState){
        when(state){
            is PopularServicesState.IsLoading -> handlePopularServicesLoading(state.isLoading)
            is PopularServicesState.IsError -> handlePopularServicesError(state.isError)
            is PopularServicesState.IsErrorMessage -> handlePopularServicesErrorMessage(state.isErrorValue)
            is PopularServicesState.Init -> Unit
        }
    }
    private fun handlePopularServicesLoading(isLoading: Boolean){
        AndroidUtils.showServiceProgressBar(binding.blogServiceErrorProgressBar,isLoading)
    }
    private fun handlePopularServicesError(isError: Boolean){
        AndroidUtils.showServiceErrorImage(binding.blogServiceErrorImage,isError)
    }
    private fun handlePopularServicesErrorMessage(isErrorValue: String){
        AndroidUtils.showServiceError(requireView(),isErrorValue)
    }

    //Post Value and State Handle
    private fun observePost(){
        postViewModel.mUsers
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { service ->
                if(service.isNotEmpty())
                {
                    postAdapter = PostAdapter(service,this)

                    binding.blogRecyclerview.apply {
                        adapter = postAdapter
                        layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL,false)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
    private fun observePostState(){
        postViewModel.mState
            .flowWithLifecycle (viewLifecycleOwner.lifecycle,  Lifecycle.State.STARTED)
            .onEach { state ->
                handlePostState(state)
            }
            .launchIn (viewLifecycleOwner.lifecycleScope)
    }
    private fun handlePostState(state: PostState){
        when(state){
            is PostState.IsLoading -> handlePostLoading(state.isLoading)
            is PostState.IsError -> handlePostError(state.isError)
            is PostState.IsErrorMessage -> handlePostErrorMessage(state.isErrorValue)
            is PostState.Init -> Unit
        }
    }
    private fun handlePostLoading(isLoading: Boolean){
        AndroidUtils.showServiceProgressBar(binding.blogServiceErrorProgressBar,isLoading)
    }
    private fun handlePostError(isError: Boolean){
        AndroidUtils.showServiceErrorImage(binding.blogServiceErrorImage,isError)
    }
    private fun handlePostErrorMessage(isErrorValue: String){
        AndroidUtils.showServiceError(requireView(),isErrorValue)
    }

    override fun itemClicked(id: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailServiceFragment(id)
        requireView().findNavController().navigate(action)
        Log.d("itemClicked","Id : $id")
    }

    override fun postClicked(url: String) {
        val defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
        defaultBrowser.data = Uri.parse(url)
        startActivity(defaultBrowser)
    }

}