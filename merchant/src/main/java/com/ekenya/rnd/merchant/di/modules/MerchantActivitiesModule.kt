package com.ekenya.rnd.merchant.di.modules

import com.ekenya.rnd.merchant.ui.ActivityMainMerchant
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MerchantActivitiesModule {

    @ContributesAndroidInjector(modules = [
//        MainActivityModule::class
    ])
    abstract fun contributeMainActivity(): ActivityMainMerchant

}
