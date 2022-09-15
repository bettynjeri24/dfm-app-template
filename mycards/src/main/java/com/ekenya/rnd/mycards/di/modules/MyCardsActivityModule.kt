package com.ekenya.rnd.mycards.di.modules

import com.ekenya.rnd.mycards.ui.ActivityMainMyCards
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MyCardsActivityModule {

    @ContributesAndroidInjector(modules = [
//        MainActivityModule::class
    ])
    abstract fun contributeMainActivity(): ActivityMainMyCards

//    @Module
//    abstract class MainActivityModule {
//        @Binds
//        @IntoMap
//        @ViewModelKey(MainViewModel::class)
//        abstract fun bindPageViewModel(viewModel: MainViewModel): ViewModel
//    }

///////////////////////////////////////////////////////////////////////////////////
//
//    /**
//     * Slider
//     */
//    ///////////////////////////////////////////////////////////////////////////////////
//    @ContributesAndroidInjector(modules = [SmeSliderActivityModule::class])
//    abstract fun contributeSliderActivity(): SliderActivity
//
//    @Module
//    abstract class SmeSliderActivityModule {
//
//        @Binds
//        @IntoMap
//        @ViewModelKey(CountryViewModel::class)
//        abstract fun bindCountryViewModel(viewModel: CountryViewModel): ViewModel
//    }


}
