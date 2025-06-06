package `in`.pawan.dogsapp.ui.main.details

import android.os.Bundle
import android.util.Log
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
import `in`.pawan.dogsapp.data.ApiResponse
import `in`.pawan.dogsapp.databinding.FragmentDetailsBinding
import `in`.pawan.dogsapp.utils.Constants
import `in`.pawan.dogsapp.utils.hide
import `in`.pawan.dogsapp.utils.show
import io.branch.indexing.BranchUniversalObject
import io.branch.referral.Branch
import io.branch.referral.Branch.BranchLinkCreateListener
import io.branch.referral.BranchError
import io.branch.referral.SharingHelper
import io.branch.referral.util.BRANCH_STANDARD_EVENT
import io.branch.referral.util.BranchContentSchema
import io.branch.referral.util.BranchEvent
import io.branch.referral.util.ContentMetadata
import io.branch.referral.util.CurrencyType
import io.branch.referral.util.LinkProperties
import io.branch.referral.util.ProductCategory
import io.branch.referral.util.ShareSheetStyle
import java.lang.Exception
import java.util.Arrays
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
        Branch.getInstance().setIdentity("1234")
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
        BranchEvent(BRANCH_STANDARD_EVENT.VIEW_ITEM)
            .addCustomDataProperty(Constants.VIEWED_BREED, breed)
            .logEvent(requireContext())
    }


    private fun setListeners() {
        binding.sampleEvent.setOnClickListener {
            val buo = BranchUniversalObject()
                .setCanonicalUrl("https://test_canonical_url")
                .setTitle("test_title")
                .setContentMetadata(
                    ContentMetadata()
                        .addCustomMetadata("custom_metadata_key1", "custom_metadata_val1")
                        .addCustomMetadata("custom_metadata_key2", "custom_metadata_val2")
                        .addCustomMetadata("custom metadata key3", "custom_metadata_val3")
                        .addImageCaptions("image_caption_1", "image_caption2", "image_caption3")
                        .setAddress("Street_Name", "test city", "test_state", "test_country", "test_postal_code")
                        .setRating(5.2, 6.0, 5)
                        .setLocation(-151.67, -124.0)
                        .setPrice(10.0, CurrencyType.INR)
                        .setProductBrand("test_prod_brand")
                        .setProductCategory(ProductCategory.APPAREL_AND_ACCESSORIES)
                        .setProductName("test_prod_name")
                        .setProductCondition(ContentMetadata.CONDITION.EXCELLENT)
                        .setProductVariant("test_prod_variant")
                        .setQuantity(1.5)
                        .setSku("test_sku")
                        .setContentSchema(BranchContentSchema.COMMERCE_PRODUCT)
                )

                .addKeyWord("keyword1")
                .addKeyWord("keyword2")

            val buo2 = BranchUniversalObject()
                .setCanonicalIdentifier("myprod/1234")
                .setCanonicalUrl("https://test_canonical_url")
                .setTitle("test_title")
                .setContentMetadata(
                    ContentMetadata()
                        .addCustomMetadata("custom_metadata_key1", "custom_metadata_val1")
                        .addCustomMetadata("custom_metadata_key2", "custom_metadata_val2")
                        .addCustomMetadata("custom metadata key3", "custom_metadata_val3")
                        .addImageCaptions("image_caption_1", "image_caption2", "image_caption3")
                        .setAddress("Street_Name", "test city", "test_state", "test_country", "test_postal_code")
                        .setRating(5.2, 6.0, 5)
                        .setLocation(-151.67, -124.0)
                        .setPrice(100.0, CurrencyType.INR)
                        .setProductBrand("test_prod_brand2")
                        .setProductCategory(ProductCategory.BABY_AND_TODDLER)
                        .setProductName("test_prod_name2")
                        .setProductCondition(ContentMetadata.CONDITION.EXCELLENT)
                        .setProductVariant("test_prod_variant2")
                        .setQuantity(1.5)
                        .setSku("test_sku2")
                        .setContentSchema(BranchContentSchema.COMMERCE_PRODUCT)
                )


                .addKeyWord("keyword1")
                .addKeyWord("keyword2")


//  Do not add an empty branchUniversalObject to the BranchEvent
            BranchEvent(BRANCH_STANDARD_EVENT.PURCHASE)
                .setAffiliation("test_affiliation")
                .setCustomerEventAlias("my_custom_alias3")
                .setCoupon("Coupon Code")
                .setCurrency(CurrencyType.INR)
                .setDescription("Customer added item to cart")
                .setShipping(0.0)
                .setTax(9.75)
                .setRevenue(100.0)
                .setSearchQuery("Test Search query")
                .addCustomDataProperty("value", "1.2")
                .addCustomDataProperty("custom_param_1", "Custom_Event_Property_val1")
                .addCustomDataProperty("custom_param_2", "Custom_Event_Property_val2")
                .addCustomDataProperty("custom_param_3", "Custom_Event_Property_val3")
                .addContentItems(buo, buo2)
                .logEvent(requireContext())
            }

        binding.shareDeeplink.setOnClickListener {
            detailsViewModel.trackEventFromApi(breedName, Constants.CUSTOM_TRACK_SHARE_EVENT)
            val lp = LinkProperties()
                .addControlParameter("breed", args.breed)
//            branchUniversalObject.generateShortUrl(requireContext(), lp, object : BranchLinkCreateListener{
//                override fun onLinkCreate(url: String?, error: BranchError?) {
//                    Log.d("Dogs details", "onLinkCreate: $url")
//                    Log.d("Dogs details", "onLinkCreate: $error")
//                }
//            })
//             Create a Link Properties instance

            Branch.getInstance().share(
                requireActivity(),
                branchUniversalObject,
                lp,
                object : Branch.BranchNativeLinkShareListener {
                    override fun onLinkShareResponse(sharedLink: String, error: BranchError) {
                        Log.d("Dogs details", "onLinkCreate form share sheet: $sharedLink")
                        if (error != null) {
                            Log.d("Share dog error", "error: $error")
                        }
                    }
                    override fun onChannelSelected(channelName: String) { }
                },
                "Sharing Branch Short URL",
                "Using Native Chooser Dialog"
            )
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