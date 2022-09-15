package com.ekenya.rnd.wallet.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.ekenya.rnd.wallet.WalletMainActivity
import com.ekenya.rnd.wallet.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityWalletModule {

    /**
     * Main Activity
     */
    @ContributesAndroidInjector(modules = [WalletMainActivityModule::class])
    abstract fun contributeMainActivity(): WalletMainActivity


    @Module
    abstract class WalletMainActivityModule {
        @Binds
        @IntoMap
        @ViewModelKey(HomeViewModel::class)
        abstract fun bindLoginViewModel(viewModel: HomeViewModel): ViewModel
    }




}
