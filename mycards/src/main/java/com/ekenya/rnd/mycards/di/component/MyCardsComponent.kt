package com.ekenya.rnd.mycards.di.component

import com.ekenya.rnd.baseapp.di.AppComponent
import com.ekenya.rnd.baseapp.di.ModuleScope
import com.ekenya.rnd.baseapp.di.injectables.ViewModelModule
import com.ekenya.rnd.mycards.di.MyCardsInjector
import com.ekenya.rnd.mycards.di.modules.MyCardsActivityModule
import com.ekenya.rnd.mycards.di.modules.MyCardsDatabaseModule
import com.ekenya.rnd.mycards.di.modules.MyCardsFragmentModule
import com.ekenya.rnd.mycards.di.modules.MyCardsNetworkModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@ModuleScope
@Component(
    dependencies = [
        AppComponent::class
    ],
    modules = [
        AndroidSupportInjectionModule::class,
        MyCardsActivityModule::class,
        MyCardsFragmentModule::class,
        ViewModelModule::class,
        MyCardsDatabaseModule::class,
        MyCardsNetworkModule::class
    ]
)
interface MyCardsComponent {
    fun inject(injector: MyCardsInjector)
}