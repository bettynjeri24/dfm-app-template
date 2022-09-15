package com.ekenya.rnd.wallet.ui.fundtransfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.databinding.FragmentFundTransferWalletBinding
import com.ekenya.rnd.wallet.ui.fundtransfer.requestfunds.mpesatoaccount.MpesaToAccountFragmentWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.requestfunds.nfc.NFCFragmentWallet
import com.ekenya.rnd.wallet.utils.setBackButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FundsTransferFragmentWallet : BaseDaggerFragment() {

    private lateinit var binding: FragmentFundTransferWalletBinding

    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_open_anim_wallet
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_close_anim_wallet
        )
    }

    companion object {
        fun newInstance() = FundsTransferFragmentWallet()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFundTransferWalletBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clToolBar.toolbar.setBackButton(R.string.fund_transfer_wallet, requireActivity())
        setUpUI()
    }


    private fun setUpUI() {
        binding.apply {
            //pesalink send to phone
            clSendToPhone.setOnClickListener {
                findNavController().navigate(
                    FundsTransferFragmentWalletDirections
                        .actionFundsTransferFragmentWalletToPesaLinkToPhoneFragmentWallet()
                )

            }
            //pesalink send to account
            clSendToAccount.setOnClickListener {
                findNavController().navigate(
                    FundsTransferFragmentWalletDirections
                        .actionFundsTransferFragmentWalletToPesalinkToAccountFragmentWallet()
                )
            }
            //pesalink send to Card
            clSendToCard.setOnClickListener {
                findNavController().navigate(
                    R.id.pesalinkToCardFragmentWallet
                )
            }
            //pesalink settings
            clPesalinkSettings.setOnClickListener {
                findNavController().navigate(
                    R.id.pesaLinkSettingsFragmentWallet
                )
            }

            //bank transfer own transfer
            clOwnTransfer.setOnClickListener {
                findNavController().navigate(
                    FundsTransferFragmentWalletDirections
                        .actionFundsTransferFragmentWalletToOwnTransferFragmentWallet()
                )
            }

            //bank transfer own transfer
            clInternalTransfer.setOnClickListener {
                findNavController().navigate(
                    FundsTransferFragmentWalletDirections
                        .actionFundsTransferFragmentWalletToInternalTransferFragmentWallet()
                )
            }

            //bank transfer to RTGS
            clRTGS.setOnClickListener {
                findNavController().navigate(
                    FundsTransferFragmentWalletDirections
                        .actionFundsTransferFragmentWalletToRtgsFragmentWallet()
                )
            }

            //bank transfers to EFT
            clEFT.setOnClickListener {
                findNavController().navigate(FundsTransferFragmentWalletDirections.actionFundsTransferFragmentWalletToEftFragmentWallet())
            }

            //request funds mpesa to account
            clMpesaToAccount.setOnClickListener {
                showBottomSheetMpesaToAccount()
            }
            clNearFieldCommunication.setOnClickListener {
                val bottomSheetDialogFragment: BottomSheetDialogFragment =
                    NFCFragmentWallet()
                bottomSheetDialogFragment.show(
                    requireActivity().supportFragmentManager,
                    bottomSheetDialogFragment.tag
                )
            }
            clSendToMpesa.setOnClickListener {
                findNavController().navigate(R.id.mainMobileMoneyFundTransferWalletFragment)
            }
            clSendToAirtelMoney.setOnClickListener {
                findNavController().navigate(R.id.mainMobileMoneyFundTransferWalletFragment)
            }
            clSendToTKash.setOnClickListener {
                findNavController().navigate(R.id.mainMobileMoneyFundTransferWalletFragment)
            }

            clPesaLink.setOnClickListener {
                if (viewGroupPesaLink.isVisible) {
                    iVPesaArrow.startAnimation(rotateClose)
                    viewGroupPesaLink.isVisible = false

                    viewGroupMobileMoney.isVisible = false
                    viewGroupBankTransfers.isVisible = false
                    viewGroupRequestFunds.isVisible = false
                } else {
                    iVPesaArrow.startAnimation(rotateOpen)
                    viewGroupPesaLink.isVisible = true

                    viewGroupMobileMoney.isVisible = false
                    viewGroupBankTransfers.isVisible = false
                    viewGroupRequestFunds.isVisible = false
                }

            }
            clMobileMoney.setOnClickListener {
                if (viewGroupMobileMoney.isVisible) {
                    iVMobileMoneyArrow.startAnimation(rotateClose)
                    viewGroupMobileMoney.isVisible = false

                    viewGroupPesaLink.isVisible = false
                    viewGroupBankTransfers.isVisible = false
                    viewGroupRequestFunds.isVisible = false
                } else {
                    iVMobileMoneyArrow.startAnimation(rotateOpen)
                    viewGroupMobileMoney.isVisible = true

                    viewGroupPesaLink.isVisible = false
                    viewGroupBankTransfers.isVisible = false
                    viewGroupRequestFunds.isVisible = false
                }
            }

            clBankTransfers.setOnClickListener {
                if (viewGroupBankTransfers.isVisible) {
                    iVBankTransfersArrow.startAnimation(rotateClose)
                    viewGroupBankTransfers.isVisible = false

                    viewGroupPesaLink.isVisible = false
                    viewGroupMobileMoney.isVisible = false
                    viewGroupRequestFunds.isVisible = false
                } else {
                    iVBankTransfersArrow.startAnimation(rotateOpen)
                    viewGroupBankTransfers.isVisible = true

                    viewGroupPesaLink.isVisible = false
                    viewGroupMobileMoney.isVisible = false
                    viewGroupRequestFunds.isVisible = false

                }
            }

            clRequestFunds.setOnClickListener {
                if (viewGroupRequestFunds.isVisible) {
                    iVRequestFundssArrow.startAnimation(rotateClose)
                    viewGroupRequestFunds.isVisible = false

                    viewGroupPesaLink.isVisible = false
                    viewGroupMobileMoney.isVisible = false
                    viewGroupBankTransfers.isVisible = false
                } else {
                    iVRequestFundssArrow.startAnimation(rotateOpen)
                    viewGroupRequestFunds.isVisible = true

                    viewGroupPesaLink.isVisible = false
                    viewGroupMobileMoney.isVisible = false
                    viewGroupBankTransfers.isVisible = false
                }
            }
        }

    }

    private fun showBottomSheetMpesaToAccount() {
        val bottomSheetDialogFragment: BottomSheetDialogFragment =
            MpesaToAccountFragmentWallet()
        bottomSheetDialogFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetDialogFragment.tag
        )
        //toasty("MpesaToAccountFragmentWallet")
    }

}