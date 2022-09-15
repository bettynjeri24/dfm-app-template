package com.ekenya.rnd.personalfinance.di

import com.ekenya.rnd.baseapp.di.AppComponent
import com.ekenya.rnd.baseapp.di.ModuleScope
import com.ekenya.rnd.baseapp.di.injectables.ViewModelModule
import com.ekenya.rnd.personalfinance.di.injectables.PersonaFinanceActivityModule
import com.ekenya.rnd.personalfinance.di.injectables.PersonaFinanceFragmentModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@ModuleScope
@Component(
    dependencies = [
        AppComponent::class
    ],
    modules = [
        AndroidSupportInjectionModule::class,
        PersonaFinanceActivityModule::class,
        PersonaFinanceFragmentModule::class,
        ViewModelModule::class
    ]
)
interface PersonalFinanceComponent {
    fun inject(injector: PersonalFinanceInjector)
}