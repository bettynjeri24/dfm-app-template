package com.ekenya.rnd.wallet.ui.myaccount

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.AccountDetailsWalletModel
import com.ekenya.rnd.wallet.data.MyAccountWalletModel
import com.ekenya.rnd.wallet.databinding.FragmentMyAccountDetailsWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.AccountsDetailsWalletAdapter
import com.ekenya.rnd.wallet.ui.adapters.listeners.OnAccountsDetailsItemClickedListener
import com.ekenya.rnd.wallet.utils.*
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.HashMap

class MyAccountDetailsPremierWalletFragment :
    BaseWalletFragment<FragmentMyAccountDetailsWalletBinding>(FragmentMyAccountDetailsWalletBinding::inflate),
    OnAccountsDetailsItemClickedListener {

    private lateinit var accountsDetailsWalletAdapter: AccountsDetailsWalletAdapter
    private lateinit var myAccountWalletModel: MyAccountWalletModel
    private var isAvailableBalanceVisible = true

    private var hashMap: HashMap<String, String> = HashMap()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            myAccountWalletModel =
                bundle.getParcelable("myAccountWalletModel")!!
        }
        fragmentResultListener(
            createSuccessBundle = createSuccessBundle(
                title = getString(R.string.unlink_account_wallet),
                subTitle = getString(R.string.unlink_account_wallet),
                cardTitle = getString(R.string.unlink_account_wallet),
                cardContent = getString(R.string.cardContent_wallet),
                hashMap = hashMap
            )
        )

        Timber.e("${myAccountWalletModel}+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")

        setUpUi()


    }

    private fun setUpUi() {
        binding.clToolBar.toolbar.setBackButton(
            myAccountWalletModel.titleMyAccount,
            requireActivity()
        )

        accountsDetailsWalletAdapter =
            AccountsDetailsWalletAdapter(
                myAccountWalletModel.accountDetailsWalletModel,
                this
            )
        binding.rvAllMyAccounts.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = accountsDetailsWalletAdapter
        }

        binding.tvAvailableBalanceAmount.text =
            " ${getString(R.string._10000)} "
        binding.tvAvailableBalanceAmount.transformationMethod = maskTextInTextView

        binding.ivAvailableBalanceAmount.setOnClickListener {
            if (isAvailableBalanceVisible) {
                binding.tvAvailableBalanceAmount.text =
                    " ${getString(R.string._10000)} "
                binding.tvAvailableBalanceAmount.transformationMethod = maskTextInTextView
                binding.ivAvailableBalanceAmount.setImageResource(R.drawable.ic_outline_visibility_on_wallet)
                isAvailableBalanceVisible = !isAvailableBalanceVisible
            } else {
                binding.tvAvailableBalanceAmount.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.tvAvailableBalanceAmount.text =
                    "${getString(R.string._10000)} "
                binding.ivAvailableBalanceAmount.setImageResource(R.drawable.ic_outline_visibility_off_wallet)
                isAvailableBalanceVisible = !isAvailableBalanceVisible
            }
        }
    }

    companion object {
        fun newInstance() =
            MyAccountDetailsPremierWalletFragment()
    }

    override fun onClickedItem(view: View, model: AccountDetailsWalletModel) {
        when (view.id) {
            R.id.cvAccountDetails -> {
                if (model.titleAccountDetails == getString(R.string.withdraw_wallet)) {
                    findNavController().navigate(R.id.withdrawMyAccountWalletFragment)
                }
                if (model.titleAccountDetails == "Mini\nStatements") {
                    findNavController().navigate(R.id.miniStatementAccountWalletFragment)
                }
                if (model.titleAccountDetails == "Full\nStatements") {
                    findNavController().navigate(R.id.fullStatementAccountWalletFragment)
                }
                if (model.titleAccountDetails == "Cheque Book\nRequests") {
                    findNavController().navigate(R.id.checkBookRequestAccountWalletFragment)
                }
                if (model.titleAccountDetails == "Notification\nSettings") {
                    Toasty.info(
                        requireContext(),
                        "Feature Coming",
                        Toast.LENGTH_SHORT,
                        true
                    )
                        .show()
                }
                if (model.titleAccountDetails == "Unlink\nAccount") {
//                    Toasty.info(
//                        requireContext(),
//                        "Feature Coming",
//                        Toast.LENGTH_SHORT,
//                        true
//                    )
//                        .show()
                    hashMap["Phone Number:"] = getString(R.string.unlink_account_wallet)

                    requireContext().showDialog(
                        title = getString(R.string.unlink_account_wallet),
                        description = getString(
                            R.string.would_you_like_to_unlink_your_account_from_mobile_banking
                        ),
                        positiveButtonFunction = {
                            lifecycleScope.launch {
                                showHideProgress(getString(R.string.sending_request_wallet))
                                delay(2000)
                                showHideProgress(null)
                                showConfirmationDialog(
                                    "${getString(R.string.unlink_account_wallet)} ",
                                    getString(R.string.unlink_account_wallet),
                                    hashMap,
                                    dialogCallback
                                )
                            }

                        },
                        negativeButtonFunction = {}
                    )
                }

            }
        }
    }


}