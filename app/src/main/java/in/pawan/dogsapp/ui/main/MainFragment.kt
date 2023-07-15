package `in`.pawan.dogsapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import `in`.pawan.dogsapp.data.ApiResponse
import `in`.pawan.dogsapp.databinding.FragmentMainBinding
import `in`.pawan.dogsapp.ui.main.list.BreedAdapter
import `in`.pawan.dogsapp.utils.Constants
import `in`.pawan.dogsapp.utils.hide
import `in`.pawan.dogsapp.utils.show
import io.branch.referral.util.BranchEvent

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val breedsAdapter: BreedAdapter = BreedAdapter() { breed ->
        trackClickEvent(breed)
        navigateToBreedDetailsPage(breed)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewInitialization()
        viewModel.fetchBreedList()
        setObservers()
    }

    private fun viewInitialization() {
        val mLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.breedsRv.layoutManager = mLayoutManager
        binding.breedsRv.adapter = breedsAdapter
    }

    private fun navigateToBreedDetailsPage(breed: String) {
        val action = MainFragmentDirections.actionMainFragmentToDogsDetailsFragment(
            breed = breed
        )
        binding.root.findNavController().navigate(action)
    }

    private fun trackClickEvent(breed: String) {
        BranchEvent("Dog click")
            .addCustomDataProperty(Constants.CLICKED_BREED, breed)
            .logEvent(requireContext())
    }

    private fun setObservers() {
        viewModel.breedListLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiResponse.Error -> {}
                is ApiResponse.Loading -> {
                    binding.progressBar.show()
                }
                is ApiResponse.Success -> {
                    binding.progressBar.hide()
                    breedsAdapter.submitList(it.data?.message)
                }

                else -> {}
            }
        })
    }
}