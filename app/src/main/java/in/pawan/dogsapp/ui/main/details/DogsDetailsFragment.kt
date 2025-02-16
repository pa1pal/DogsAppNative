package `in`.pawan.dogsapp.ui.main.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.BuildConfig
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import `in`.pawan.dogsapp.data.ApiResponse
import `in`.pawan.dogsapp.data.dto.CustomData
import `in`.pawan.dogsapp.data.dto.LinkData
import `in`.pawan.dogsapp.databinding.FragmentDetailsBinding
import `in`.pawan.dogsapp.utils.Constants
import `in`.pawan.dogsapp.utils.hide
import `in`.pawan.dogsapp.utils.show
import perfetto.protos.EventName

@AndroidEntryPoint
class DogsDetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val detailsViewModel: DetailsViewModel by viewModels()
    private val args: DogsDetailsFragmentArgs by navArgs()
    private var breedName: String? = ""
    private var imageUrl: String? = ""

//    @Inject
//    lateinit var branchUniversalObject: BranchUniversalObject

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.breed?.let { detailsViewModel.fetchBreedDetails(it) }
        breedName = args.breed
        binding.breedNameTv.text = breedName
        viewInitialization()
        setObservers()
        setListeners()
        trackViewEvent(breedName)
        detailsViewModel.trackEventFromApi(breedName, Constants.CUSTOM_TRACK_VIEW_EVENT)

    }

    private fun viewInitialization() {
    }

    private fun trackViewEvent(breed: String?) {
//        BranchEvent(BRANCH_STANDARD_EVENT.VIEW_ITEM)
//            .addCustomDataProperty(Constants.VIEWED_BREED, breed)
//            .logEvent(requireContext())
    }


    private fun setListeners() {
        binding.sampleEvent.setOnClickListener {
            detailsViewModel.trackCommerceEvent(Constants.PURCHSE_EVENT)
        }

        binding.shareDeeplink.setOnClickListener {
            detailsViewModel.trackEventFromApi(breedName, Constants.CUSTOM_TRACK_SHARE_EVENT)
            val customData = CustomData(breed = breedName)
            val linkData = LinkData(customData = customData)

            if (linkData != null) {
                detailsViewModel.createDeepLink(linkData)
            }

        }
    }

    private fun setObservers() {
        detailsViewModel.breedDetailsLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiResponse.Error -> {}
                is ApiResponse.Loading -> {
                    binding.progressBar.show()
                }

                is ApiResponse.Success -> {
                    binding.progressBar.hide()
                    imageUrl = it.data?.imageUrl
                    Picasso.get().load(it.data?.imageUrl).into(binding.breedImage)
                }

                else -> {}
            }
        })

        detailsViewModel.createLinkLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiResponse.Error -> {}
                is ApiResponse.Loading -> {
                    binding.progressBar.show()
                }

                is ApiResponse.Success -> {
                    binding.progressBar.hide()
                    val link = it.data?.url

                    // Create an implicit intent to share the link
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Check out this $breedName dog here \n $link")
                        putExtra(Intent.EXTRA_TITLE, "Dog Breed Details")
                        type = "text/plain"
                    }
                    // Share the link
                    startActivity(Intent.createChooser(sendIntent, null))
                }

                else -> {}
            }
        })
    }
}