package com.ekenya.rnd.wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.ekenya.rnd.baseapp.MobileBankingApp
import com.ekenya.rnd.baseapp.di.helpers.features.Modules
import com.ekenya.rnd.common.abstractions.BaseActivity
import com.ekenya.rnd.wallet.databinding.ActivityMainWalletBinding
import dagger.android.AndroidInjector
import timber.log.Timber
import javax.inject.Inject

class WalletMainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainWalletBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    //private MainViewModel mViewModel;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWalletBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.mobile_navigation_wallet)

        when (intent.action) {
            Modules.FeatureWallet.WalletAction.ACTION_MY_ACCOUNTS.toString() -> {
                graph.startDestination = R.id.mainMyAccountWalletFragment
            }
            Modules.FeatureWallet.WalletAction.ACTION_DEPOSIT.toString() -> {
                graph.startDestination = R.id.mainDepositWalletFragment
            }
            Modules.FeatureWallet.WalletAction.ACTION_FUND_TRANSFER.toString() -> {
                graph.startDestination = R.id.mainFundsTransferFragmentWallet
            }
            Modules.FeatureWallet.WalletAction.ACTION_BILL_PAYMENT.toString() -> {
                graph.startDestination = R.id.mainBillWalletFragment
            }
            Modules.FeatureWallet.WalletAction.ACTION_BUY_AIRTIME.toString() -> {
                graph.startDestination = R.id.mainBuyAirTimeWalletFragment
            }
            Modules.FeatureWallet.WalletAction.ACTION_WITHDRAW.toString() -> {
                graph.startDestination = R.id.mainWithdrawWalletFragment
            }
            else -> {
                Timber.e("WalletMainActivity====> HomeFragment")
                graph.startDestination = R.id.homeWalletFragment
            }
        }
        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        // Fragment Injector should use the Application class
        // If necessary, I will use AndroidInjector as well as App class (I have not done this time)
        return (application as MobileBankingApp).supportFragmentInjector()
    }
}