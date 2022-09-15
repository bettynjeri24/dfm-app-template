package com.ekenya.rnd.merchant.di.component

import com.ekenya.rnd.baseapp.di.AppComponent
import com.ekenya.rnd.baseapp.di.ModuleScope
import com.ekenya.rnd.baseapp.di.injectables.ViewModelModule
import com.ekenya.rnd.merchant.di.MerchantInjector
import com.ekenya.rnd.merchant.di.modules.MerchantActivitiesModule
import com.ekenya.rnd.merchant.di.modules.MerchantFragmentsModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@ModuleScope
@Component(
    dependencies = [
        AppComponent::class
    ],
    modules = [
        AndroidSupportInjectionModule::class,
        MerchantActivitiesModule::class,
        MerchantFragmentsModule::class,
        ViewModelModule::class
    ]
)

interface MerchantComponent {
    fun inject(injector: MerchantInjector)
}