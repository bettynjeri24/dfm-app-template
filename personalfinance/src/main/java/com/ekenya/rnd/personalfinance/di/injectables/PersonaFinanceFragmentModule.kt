package com.ekenya.rnd.personalfinance.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.ekenya.rnd.personalfinance.ui.getstarted.GetStartedFragment
import com.ekenya.rnd.personalfinance.ui.getstarted.GetStartedViewModel
import com.ekenya.rnd.personalfinance.ui.home.HomeFragment
import com.ekenya.rnd.personalfinance.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class PersonaFinanceFragmentModule {

    @ContributesAndroidInjector(modules = [TourismHomeFragmentModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @Module
    abstract class TourismHomeFragmentModule {
        @Binds
        @IntoMap
        @ViewModelKey(HomeViewModel::class)
        abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
    }

    @ContributesAndroidInjector(modules = [TourismDashboardFragmentModule::class])
    abstract fun contributeDashboardFragment(): GetStartedFragment

    @Module
    abstract class TourismDashboardFragmentModule {
        @Binds
        @IntoMap
        @ViewModelKey(GetStartedViewModel::class)
        abstract fun bindHomeViewModel(viewModel: GetStartedViewModel): ViewModel
    }
    //LIST THE OTHER INJECTABLE FRAGMENTS AS ABOVE
}
