package com.ekenya.rnd.personalfinance.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.ekenya.rnd.personalfinance.PersonalFinanceMainActivity
import com.ekenya.rnd.personalfinance.ui.getstarted.GetStartedViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class PersonaFinanceActivityModule {

    /**
     * Main Activity
     */
    ///////////////////////////////////////////////////////////////////////////////////
    @ContributesAndroidInjector(modules = [TourismMainActivityModule::class])
    abstract fun contributeMainActivity(): PersonalFinanceMainActivity

    @Module
    abstract class TourismMainActivityModule {
        @Binds
        @IntoMap
        @ViewModelKey(GetStartedViewModel::class)
        abstract fun bindLoginViewModel(viewModel: GetStartedViewModel): ViewModel
    }

}
