package com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.travel_notification

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.common.abstractions.BaseBottomSheetDialogFragment
import com.ekenya.rnd.mycards.ui.dialog.alertDialog.showAlertDialog
import com.ekenya.rnd.mycards.R
import com.ekenya.rnd.mycards.databinding.BottomSheetTravelNotificationBinding
import com.ekenya.rnd.mycards.domain.model.PlaceDataModel
import com.ekenya.rnd.common.dialogs.base.adapter_detail.model.DialogDetailCommon
import com.ekenya.rnd.mycards.ui.dialog.datePickerDialog.DatePickerDialogUi
import com.ekenya.rnd.mycards.ui.fragments.home.MyCardsHomeFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*

class BottomSheetTravelNotification(val navigateToAuth: () -> Unit) : BaseBottomSheetDialogFragment(), DatePickerDialogUi.OnTagListener {

    private lateinit var binding: BottomSheetTravelNotificationBinding
    private var mMap: GoogleMap? = null
    private var placeAdapter: PlaceArrayAdapter? = null
    private lateinit var mPlacesClient: PlacesClient

    private val fadeIn: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.fade_in
        )
    }

    private val fadeOut: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.fade_out
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomSheetTravelNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpUi()
    }

    private fun setUpUi() {
        setUpBinding()
    }

    private fun setUpBinding() {
        binding.apply {
            //configure datePicker dialogs
            textinputEditTextDepartureDate.configureShowDateDialog(DEPART)
            textinputEditTextReturnDate.configureShowDateDialog(RETURN)
        }

        binding.button.setOnClickListener {
            showDialog()
        }
    }

    private fun getDatePickerDialog() = DatePickerDialogUi(this@BottomSheetTravelNotification)

    private fun showDatePickerDialog(tag: String) =
        getDatePickerDialog().show(parentFragmentManager, tag)


    private fun TextInputEditText.configureShowDateDialog(tag: String) {

        inputType = InputType.TYPE_NULL

        setOnTouchListener { view, motionEvent ->

            when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                    //do nothing
                }

                MotionEvent.ACTION_UP -> {

                    showDatePickerDialog(tag)
                    view.performClick()
                    view.onTouchEvent(motionEvent)
                }

            }

            false
        }

    }

    override fun onDateAdjusted(year: Int, month: Int, day: Int, tag: String?) {

        val calendar = Calendar.getInstance()
        calendar[year, month] = day
        val dFormat: DateFormat = DateFormat.getDateInstance()
        val date = dFormat.format(calendar.time)

        when (tag) {

            DEPART -> {
                binding.textinputEditTextDepartureDate.setText(date)
            }
            RETURN -> {
                binding.textinputEditTextReturnDate.setText(date)
            }
        }
    }


    companion object {
        private const val DEPART = "Depart"
        private const val RETURN = "Return"

    }

    override fun onResume() {
        super.onResume()

        Places.initialize(requireContext(), "AIzaSyCmx5wZoFxFoCmr3r-WSoMpTx8ydM7Fef0")
        mPlacesClient = Places.createClient(requireContext())

//        val mapFragment: SupportMapFragment? = binding. as? SupportMapFragment
//        mapFragment?.getMapAsync(requireActivity())

        placeAdapter = PlaceArrayAdapter(requireContext(), R.layout.item_layout_places_cards, mPlacesClient)
        binding.autoCompleteTextPlaces.setAdapter(placeAdapter)

        binding.autoCompleteTextPlaces.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val place = parent.getItemAtPosition(position) as PlaceDataModel
//            binding.autoCompleteTextPlaces.apply {
//                setText(place.fullText)
//                setSelection(autoCompleteEditText.length())
//            }

        }
    }

    private fun showDialog() {

        val merchantPaymentDialog = showAlertDialog {

            // not cancellable
            cancelable = false

            // set title
            setDialogTitle("Confirm Travel Notification")

            // set sub title
            setDialogSubtitle("Please confirm you want to set a card limit for your Gold credit 5535•••• ••••22978")

            // add dialog details
            setUpRecyclerAdapter(getDetails())

            // add execution logic to confirm button click
            confirmButtonClickListener {
                MyCardsHomeFragment.apply {
                    title = "Travel Notification Successful"
                    subtitle = "You have successfully set travel notification for your card"
                    cardTitle = "Travel Notification For"
                    cardText = "Current Account  A/C 1234 •••• •••• 2344"
                    details = getDetails().apply {
                        add(DialogDetailCommon("TIME & DATE:", "12-04-2021 | 4:00PM"))
                    }
                }
                lifecycleScope.launch {
                    simulateSearching()
                    navigateToAuth.invoke()
                    this@BottomSheetTravelNotification.dismiss()
                }
            }

            cancelButtonClickListener {

            }


        }

        //  show the dialog
        merchantPaymentDialog.show()
    }

    private fun getDetails() = mutableListOf<DialogDetailCommon>().apply {
        add(DialogDetailCommon("DEPARTURE DATE:", binding.textinputEditTextDepartureDate.text.toString()))
        add(DialogDetailCommon("RETURN DATE:", binding.textinputEditTextReturnDate.text.toString()))
        add(DialogDetailCommon("DESTINATION:", "Canada"))
        add(DialogDetailCommon("EMERGENCY CONTACT::", binding.textInputLayoutContact.text.toString()))
    }

    private suspend fun simulateSearching(){
        showHideProgress("Updating Travel notifications")
        delay(2000)
        showHideProgress(null)
    }


}