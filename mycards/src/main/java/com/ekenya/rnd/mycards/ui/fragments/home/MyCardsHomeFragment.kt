package com.ekenya.rnd.mycards.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.auth.AuthResult
import com.ekenya.rnd.common.dialogs.base.adapter_detail.model.DialogDetailCommon
import com.ekenya.rnd.mycards.R
import com.ekenya.rnd.mycards.data.room.entities.CardEntity
import com.ekenya.rnd.mycards.databinding.FragmentHomeCardsBinding
import com.ekenya.rnd.mycards.ui.dialog.alertDialog.showAlertDialog
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.block_card.BottomSheetBlockCard
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.change_pin.BottomSheetChangePin
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.limits.BottomSheetLimits
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.link_card.BottomSheetLinkCard
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.request_card.BottomSheetRequestCard
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.top_up_card.BottomSheetTopUpCard
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.travel_notification.BottomSheetTravelNotification
import com.ekenya.rnd.mycards.ui.fragments.home.recycler_adapters.MyCardsViewPagerAdapter
import com.ekenya.rnd.mycards.ui.fragments.home.recycler_adapters.OptionsAdapter
import com.ekenya.rnd.mycards.utils.HomeOption
import com.ekenya.rnd.mycards.utils.MyCardsListUtils
import com.ekenya.rnd.mycards.utils.Resource
import com.ekenya.rnd.mycards.utils.toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject


class MyCardsHomeFragment : BaseDaggerFragment() {

    private lateinit var binding: FragmentHomeCardsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MyCardsHomeViewModel::class.java]
    }

    //Hold Boolean state if fab buttons are open
    private var fabOpen = false

    //Initializing animations
    //Rotate fab 45 degrees when opening the other two extended fab buttons
    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_open_anim
        )
    }

    //Rotate fab button 45 degrees back to normal
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_close_anim
        )
    }

    // Animate extended fab button to top
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.from_bottom_anim
        )
    }

    // Animate extended fab back to bottom
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.to_bottom_anim
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         return FragmentHomeCardsBinding.inflate(inflater, container, false).also {
             binding = it
         }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpUserInterface()

        setFragmentResultListener("requestKey") { _ , bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result : AuthResult = bundle.get("authResult") as AuthResult

            when (result){

                AuthResult.AUTH_SUCCESS -> {

                    findNavController().navigate(R.id.action_homeFragment_to_successfulFragment2,createSuccessBundle())

                }
                AuthResult.AUTH_ERROR -> {
                    //handle error
                }
            }

        }


    }

    private fun createSuccessBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString("title", title)
        bundle.putString("subtitle", subtitle)
        bundle.putString("cardTitle", cardTitle)
        bundle.putString("cardContent", cardText)

        val hashMap = HashMap<String,String>()
        hashMap.put("Example", "Example")
        bundle.putSerializable("content", hashMap)

        return bundle
    }

    //sets up the user Interface
    private fun setUpUserInterface() {
        setUpCardViewPager()
        setUpOptionsGrid()
        setBindings()
    }


    private fun setBindings() {
        binding.apply {

            //Navigate back
            binding.toolbar.setNavigationOnClickListener {
                requireActivity().finish()
            }

            floatingActionButtonOpenFabs.setOnClickListener {
                showHideFabButtons()
            }

            floatingActionButtonLinkedCard.setOnClickListener {
                showLinkCardBottomSheet()
            }

            floatingActionButtonRequestCard.setOnClickListener {
                showRequestCardBottomSheet()
            }
        }
    }

    private fun showHideFabButtons() {
        setVisibility()
        setAnimation()
//        flip the boolean state
        fabOpen = !fabOpen
    }

    //Flip the visibility states of extended fab buttons
    private fun setVisibility() {
        binding.floatingActionButtonLinkedCard.isVisible =
            !binding.floatingActionButtonLinkedCard.isVisible
        binding.floatingActionButtonRequestCard.isVisible =
            !binding.floatingActionButtonRequestCard.isVisible

    }

    //Animate fab buttons when main fab is clicked
    private fun setAnimation() {
        binding.apply {
            if (!fabOpen) {
                //start opening animations
                floatingActionButtonOpenFabs.startAnimation(rotateOpen)
                floatingActionButtonLinkedCard.startAnimation(fromBottom)
                floatingActionButtonRequestCard.startAnimation(fromBottom)
            } else {
                //start closing animations
                floatingActionButtonOpenFabs.startAnimation(rotateClose)
                floatingActionButtonLinkedCard.startAnimation(toBottom)
                floatingActionButtonRequestCard.startAnimation(toBottom)
            }
        }
    }


    //set up card viewpager
    private fun setUpCardViewPager() {

        //Create a Pager Transformer for the Viewpager
        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(24))
            addTransformer { page, position ->
                val transform = 1 - kotlin.math.abs(position)
                page.scaleY = (0.85f + transform * 0.15f)
            }
        }

        //Sets adapter to the viewpager
        val cardAdapter = MyCardsViewPagerAdapter()
        binding.viewpagerCard.apply {
            adapter = cardAdapter
            setPageTransformer(compositePageTransformer)
            clipToPadding = false
            offscreenPageLimit = 20
            setPadding(48, 0, 48, 0)
            offscreenPageLimit = 3
            clipToPadding = false
            clipChildren = false
            isNestedScrollingEnabled = true
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        binding.viewpagerCard.adapter = cardAdapter

        viewModel.cards.observe(viewLifecycleOwner) { resource ->

            when (resource) {
                is Resource.Error -> {
                    toast(resource.error.toString())
                    //hide progress
                    hideProgress()
                }
                is Resource.Loading -> {
                    // show progress
                    showProgress()
                }
                is Resource.Success -> {

                    // submit list to the viewpager
                    cardAdapter.submitList(resource.data)

                    // set up the dots indicator
                    binding.dashboardIndicatorLayout.setIndicatorCount(resource.data?.size ?: 0)

                    // hide progress
                    hideProgress()

                    // show empty screen
                    binding.groupEmpty.isVisible = resource.data?.isEmpty() ?: false

                    // show content screen
                    binding.groupContent.isVisible = resource.data?.isNotEmpty() ?: false

                    cards = resource.data!!
                }
            }
        }


        //register on page change callback
        binding.viewpagerCard.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                binding.dashboardIndicatorLayout.selectCurrentPosition(position)
                selected = cards[position]
            }

        })


    }

    //Populate the options in the main page
    private fun setUpOptionsGrid() {

        val optionsAdapter = OptionsAdapter { onOptionClicked(it) }
        binding.optionsList.adapter = optionsAdapter
        optionsAdapter.submitList(MyCardsListUtils.options)
    }

    //handle option clicks from
    private fun onOptionClicked(homeOption: HomeOption) {

        when (homeOption) {

            HomeOption.BlockCard -> {
                showRequestBlockBottomSheet()
            }
            HomeOption.ChangePin -> {
                showChangePinBottomSheet()
            }
            HomeOption.MiniStatement -> {
                findNavController(this).navigate(R.id.action_home_to_miniStatement)
            }
            HomeOption.TopUp -> {
                showTopUpBottomSheet()
            }
            HomeOption.Travel -> {
                showTravelSheetNotificationBottomSheet()
            }
            HomeOption.UpdateLimits -> {
                showLimitsBottomSheet()
            }

            else -> {
                toast("Invalid Option")
            }
        }
    }

    //blocking card
    private fun blockCard() {
        //display an alert dialog
        showDialog()
    }

    //show limits bottom sheet
    private fun showLimitsBottomSheet() {
        val bottomSheetDialogFragment: BottomSheetDialogFragment =
            BottomSheetLimits { navigateToAuth() }
        bottomSheetDialogFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetDialogFragment.tag
        )
    }

    //show change pin bottom sheet
    private fun showChangePinBottomSheet() {
        val bottomSheetDialogFragment: BottomSheetDialogFragment =
            BottomSheetChangePin { navigateToAuth() }
        bottomSheetDialogFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetDialogFragment.tag
        )
    }

    //show link card bottom sheet
    private fun showLinkCardBottomSheet() {
        val bottomSheetDialogFragment: BottomSheetDialogFragment =
            BottomSheetLinkCard { navigateToAuth() }
        bottomSheetDialogFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetDialogFragment.tag
        )
    }

    //show top up bottom sheet
    private fun showTopUpBottomSheet() {
        val bottomSheetDialogFragment: BottomSheetDialogFragment =
            BottomSheetTopUpCard { navigateToAuth() }
        bottomSheetDialogFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetDialogFragment.tag
        )
    }

    //show travel notification bottom sheet
    private fun showTravelSheetNotificationBottomSheet() {
        val bottomSheetDialogFragment: BottomSheetDialogFragment =
            BottomSheetTravelNotification { navigateToAuth() }
        bottomSheetDialogFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetDialogFragment.tag
        )
    }

    //show Request card bottom sheet
    private fun showRequestCardBottomSheet() {
        val bottomSheetDialogFragment: BottomSheetDialogFragment =
            BottomSheetRequestCard { navigateToAuth() }

        bottomSheetDialogFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetDialogFragment.tag
        )
    }

    // show block card bottom sheet
    private fun showRequestBlockBottomSheet() {
        val bottomSheetDialogFragment: BottomSheetDialogFragment =
            BottomSheetBlockCard { navigateToAuth() }
        bottomSheetDialogFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetDialogFragment.tag
        )
    }


    private fun showProgress() {
        binding.progressBar.isVisible = true
    }

    private fun hideProgress() {
        binding.progressBar.isVisible = false
    }



    private fun navigateToAuth() {
        findNavController().navigate(R.id.action_homeFragment_to_authFragment)
    }


    private fun showDialog() {

        val merchantPaymentDialog = showAlertDialog {

            // not cancellable
            cancelable = false

            // set title
            setDialogTitle("Confirm Block Card")

            // set sub title
            setDialogSubtitle("Please confirm you want to block your gold credit Card 1234 •••• •••• 2344")

            // add dialog details
            setUpRecyclerAdapter(details)

            // add execution logic to confirm button click
            confirmButtonClickListener {
                title = "Block Card"
                subtitle = "Your card was blocked successfully"
                cardTitle = "Card Blocked For"
                cardText = "Gold credit 1234 •••• •••• 2344"
                navigateToAuth()
                details = details.apply {
                    clear()
                    add(DialogDetailCommon("REASON:", ""))
                    add(DialogDetailCommon("ACCOUNT:", ""))
                    add(DialogDetailCommon("TIME & DATE:", "Kes 522.06"))
                }
            }

            cancelButtonClickListener {

            }


        }

        //  show the dialog
        merchantPaymentDialog.show()
    }


    companion object {
        var details = mutableListOf<DialogDetailCommon>()
        var title = ""
        var subtitle = ""
        var cardTitle = ""
        var cardText = ""
        var cards = listOf<CardEntity>()
        var selected = CardEntity(0,"","","","")
    }

    override fun onResume() {
        super.onResume()
        details.clear()
    }


}