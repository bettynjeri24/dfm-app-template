package com.ekenya.rnd.mycards.di

import android.app.Activity
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import com.ekenya.rnd.baseapp.MobileBankingApp
import com.ekenya.rnd.baseapp.di.BaseModuleInjector
import com.ekenya.rnd.mycards.di.component.DaggerMyCardsComponent
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

@Keep
class MyCardsInjector : BaseModuleInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun inject(app: MobileBankingApp) {
        DaggerMyCardsComponent.builder()
            .appComponent(app.appComponent)
            .build()
            .inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityInjector
    }

    override fun fragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return fragmentInjector
    }
}
