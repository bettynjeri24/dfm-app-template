package com.ekenya.rnd.wallet.di

import com.ekenya.rnd.baseapp.di.AppComponent
import com.ekenya.rnd.baseapp.di.ModuleScope
import com.ekenya.rnd.baseapp.di.injectables.ViewModelModule
import com.ekenya.rnd.wallet.di.injectables.ActivityWalletModule
import com.ekenya.rnd.wallet.di.injectables.FragmentWalletModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@ModuleScope
@Component(
    dependencies = [
        AppComponent::class
    ],
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityWalletModule::class,
        FragmentWalletModule::class,
        ViewModelModule::class,
        //MobileBankingDbModule::class
    ]
)
interface WalletComponent {
    fun inject(injector: WalletInjector)
}