package `in`.pawan.dogsapp.ui.main.details

import `in`.pawan.dogsapp.data.ApiResponse
import `in`.pawan.dogsapp.databinding.FragmentDetailsBinding
import `in`.pawan.dogsapp.utils.Constants
import `in`.pawan.dogsapp.utils.hide
import `in`.pawan.dogsapp.utils.show
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import io.branch.indexing.BranchUniversalObject
import io.branch.referral.Branch
import io.branch.referral.BranchError
import io.branch.referral.SharingHelper
import io.branch.referral.util.BRANCH_STANDARD_EVENT
import io.branch.referral.util.BranchEvent
import io.branch.referral.util.LinkProperties
import io.branch.referral.util.ShareSheetStyle
import javax.inject.Inject

@AndroidEntryPoint
class DogsDetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val detailsViewModel: DetailsViewModel by viewModels()
    private val args: DogsDetailsFragmentArgs by navArgs()
    private var breedName: String? = ""
    private var imageUrl: String? = ""

    @Inject
    lateinit var branchUniversalObject: BranchUniversalObject

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
        trackEventFromAPI(breedName)
    }

    private fun viewInitialization() {
    }

    private fun trackViewEvent(breed: String?) {
        BranchEvent(BRANCH_STANDARD_EVENT.VIEW_ITEM)
            .addCustomDataProperty(Constants.VIEWED_BREED, breed)
            .logEvent(requireContext())
    }

    private fun trackEventFromAPI(breedName: String?) {
        detailsViewModel.trackEventFromApi(breedName)
    }

    private fun setListeners() {
        binding.shareDeeplink.setOnClickListener {
            val lp = LinkProperties()
                .setFeature("sharing")
                .setCampaign("$breedName")
                .addControlParameter("\$desktop_url", "$imageUrl")
                .addControlParameter("breed", args.breed)

            val shareStyle =
                ShareSheetStyle(requireActivity(), "Check this out!", "This Dog is cute: ")
                    .setCopyUrlStyle(
                        ResourcesCompat.getDrawable(
                            resources,
                            android.R.drawable.ic_menu_send,
                            null
                        ), "Copy", "Added to clipboard"
                    )
                    .setMoreOptionStyle(
                        ResourcesCompat.getDrawable(
                            resources,
                            android.R.drawable.ic_menu_search,
                            null
                        ), "Show more"
                    )
                    .addPreferredSharingOption(SharingHelper.SHARE_WITH.FACEBOOK)
                    .addPreferredSharingOption(SharingHelper.SHARE_WITH.EMAIL)
                    .addPreferredSharingOption(SharingHelper.SHARE_WITH.MESSAGE)
                    .setAsFullWidthStyle(true)
                    .setSharingTitle("Share With")

            branchUniversalObject.showShareSheet(
                requireActivity(),
                lp,
                shareStyle,
                object : Branch.BranchLinkShareListener {
                    override fun onShareLinkDialogLaunched() {}
                    override fun onShareLinkDialogDismissed() {}
                    override fun onLinkShareResponse(
                        sharedLink: String?,
                        sharedChannel: String?,
                        error: BranchError?
                    ) {
                    }

                    override fun onChannelSelected(channelName: String) {}
                })

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
    }
}