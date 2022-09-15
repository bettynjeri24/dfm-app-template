package com.ekenya.rnd.wallet.ui.deposit.cheque

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentTab1DepositChequeWalletBinding
import com.ekenya.rnd.wallet.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.File
import java.util.HashMap


class Tab1DepositChequeFragmentWallet : BaseWalletFragment<FragmentTab1DepositChequeWalletBinding>(
    FragmentTab1DepositChequeWalletBinding::inflate
) {
    private var takeFrontPhoto: Boolean? = null

    //private var hashMap: HashMap<String, String> = HashMap()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        makeCameraPermissionsRequest()
    }

    private fun initUI() {

        if (ID_FRONT_IMAGE.isNotEmpty()) {
            binding.tvFrontSideOfCheque.isVisible = false
            binding.tvFrontSideOfCheque.text = getString(R.string.remove_photo)
            binding.iVBackSideOfCheque.setImageBitmap(CAMERA_IMAGE_FRONT)
        } else {
            binding.tvFrontSideOfCheque.isVisible = (true)
            binding.tvFrontSideOfCheque.text = getString(R.string.front_side_of_cheque_wallet)
        }

        if (ID_BACK_IMAGE.isNotEmpty()) {
            binding.tvBackSideOfCheque.isVisible = (false)
            binding.tvBackSideOfCheque.text = getString(R.string.remove_photo)
            binding.iVBackSideOfCheque.setImageBitmap(CAMERA_IMAGE_BACK)
        } else {
            binding.tvBackSideOfCheque.isVisible = (true)
            binding.tvBackSideOfCheque.text = getString(R.string.back_side_of_cheque_wallet)
        }

        binding.cVFrontSideOfCheque.setOnClickListener {
            if (binding.tvFrontSideOfCheque.text == getString(R.string.remove_photo)) {
                binding.iVFrontSideOfCheque.setImageDrawable(null)
                binding.tvFrontSideOfCheque.isVisible = (true)
                binding.tvFrontSideOfCheque.text = getString(R.string.front_side_of_cheque_wallet)
                ID_FRONT_IMAGE = ""
            } else {
                takeFrontPhoto = true
                openCameraForPickingImage(CAPTURE_CAMERA)
            }


        }
        binding.cVBackSideOfCheque.setOnClickListener {
            if (binding.tvBackSideOfCheque.text == getString(R.string.remove_photo)) {
                binding.iVBackSideOfCheque.setImageDrawable(null)
                binding.tvBackSideOfCheque.isVisible = (true)
                ID_BACK_IMAGE = ""
                binding.tvBackSideOfCheque.text = getString(R.string.back_side_of_cheque_wallet)
            } else {
                takeFrontPhoto = false
                openCameraForPickingImage(CAPTURE_CAMERA)
            }
        }

        binding.actSelectAccount.setUpSpinner(
            R.array.account_type_wallet,
            onItemClick = { parent, view, pos, id ->
                val selectedItem = parent?.adapter?.getItem(pos).toString()
                Timber.e("SPINNER", selectedItem)
            })

        binding.btnContinue.setOnClickListener {
            if (addValidations()) {

                hashMapBaseWallet["Reference"] =
                    " ${binding.edtReference.text.toString()}"
                hashMapBaseWallet["Cheque Amount"] = " ${binding.edtChequeAmount.text.toString()}"
                hashMapBaseWallet["Deposit To"] = binding.actSelectAccount.text.toString()
                hashMapBaseWallet["Front side of cheque"] = getString(R.string.back_side_of_cheque_wallet)

                lifecycleScope.launch {
                    showHideProgress(getString(R.string.sending_request_wallet))
                    delay(2000)
                    showHideProgress(null)
                    showConfirmationDialog(
                        getString(R.string.Confirm_Cheque_Deposit),
                        getString(R.string.Make_sure_the_details_on_your_cheque_are_accurate_And_valid),
                        hashMapBaseWallet,
                        dialogCallback
                    )
                }
            }

        }

    }


    private fun addValidations(): Boolean {
        if (binding.edtChequeAmount.text.isNullOrEmpty() || binding.edtChequeAmount.text.isNullOrBlank()) {
            binding.edtChequeAmount.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.edtReference.text.isNullOrEmpty()
            || binding.edtReference.text.isNullOrBlank()
        ) {
            binding.edtReference.error = getString(R.string.enter_all_field_wallet)
            return false
        }
        if (binding.actSelectAccount.text.isNullOrEmpty() || binding.actSelectAccount.text.isNullOrBlank()) {
            binding.actSelectAccount.error = getString(R.string.enter_all_field_wallet)
            return false
        } else {
            return true
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == CAPTURE_CAMERA) {
            if (takeFrontPhoto == true) {
                val thumbnailFrontID = data!!.extras!!.get("data") as Bitmap
                CAMERA_IMAGE_FRONT = thumbnailFrontID
                binding.iVFrontSideOfCheque.setImageBitmap(thumbnailFrontID)

                if (thumbnailFrontID != null) {

                    //binding.tvFrontSideOfCheque.isVisible=(false)
                    binding.tvFrontSideOfCheque.setText(getString(R.string.remove_photo))

                    val rnds = (100..10000).random()
                    val uri =
                        convertBitmapToFile("${rnds}FrontId", thumbnailFrontID)
                    //val uri = getImageUri( "FrontId",thumbnailFrontID)

                    val uriPathHelper = URIPathHelper()

                    ID_FRONT_IMAGE =
                        uriPathHelper.getPath(requireContext(), uri)!!


//*************************************************************************************************
                    val mediaType = "image/png".toMediaTypeOrNull()
                    val fileFrontID = File(ID_FRONT_IMAGE)
                    val requestFileFrontID: RequestBody = fileFrontID.asRequestBody(mediaType)
                    val frontID =
                        MultipartBody.Part.createFormData(
                            "files",
                            fileFrontID.name,
                            requestFileFrontID
                        )

                    Timber.d("FrontID 1 @@@@@@@@@@@@@@@@@@@@@@@@@@ ${uri}")
                    Timber.d("FrontID 1 @@@@@@@@@@@@@@@@@@@@@@@@@@ ${fileFrontID.absoluteFile}")
                    Timber.d("FrontID 2 @@@@@@@@@@@@@@@@@@@@@@@@@@ ${uri}")
                    Timber.d("FrontID 3 @@@@@@@@@@@@@@@@@@@@@@@@@@ ${fileFrontID.path}")
                    Timber.d("FrontID 4 @@@@@@@@@@@@@@@@@@@@@@@@@@ ${ID_FRONT_IMAGE}")
//*************************************************************************************************
                }

            }
            if (takeFrontPhoto == false) {
                val thumbnailIDBack = data!!.extras!!.get("data") as Bitmap
                CAMERA_IMAGE_BACK = thumbnailIDBack

                binding.iVBackSideOfCheque.setImageBitmap(thumbnailIDBack)
                if (thumbnailIDBack != null) {

                    //binding.tvBackSideOfCheque.isVisible=(false)
                    binding.tvBackSideOfCheque.text = getString(R.string.remove_photo)

                    val rnds = (100..10000).random()

                    val uri = convertBitmapToFile("${rnds}BackId", thumbnailIDBack)

                    //val uri = getImageUri(thumbnailIDBack, "BackId")
                    val uriPathHelper = URIPathHelper()
                    ID_BACK_IMAGE =
                        uriPathHelper.getPath(requireContext(), uri)!!

//*************************************************************************************************
                    val mediaType = "image/png".toMediaTypeOrNull()
                    val fileBackID = File(ID_BACK_IMAGE)
                    val requestFileBackID = fileBackID.asRequestBody(mediaType)
                    val backID =
                        MultipartBody.Part.createFormData(
                            "files",
                            fileBackID.name,
                            requestFileBackID
                        )

                    Timber.d("BackID @@@@@@@@@@@@@@@@@@@@@@@@@@ ${fileBackID.absoluteFile}")
                    Timber.d("BackID @@@@@@@@@@@@@@@@@@@@@@@@@@ ${uri}")
                    Timber.d("BackID @@@@@@@@@@@@@@@@@@@@@@@@@@ ${fileBackID.path}")
                    Timber.d("BackID @@@@@@@@@@@@@@@@@@@@@@@@@@ ${ID_BACK_IMAGE}")
//*************************************************************************************************
                }

            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}