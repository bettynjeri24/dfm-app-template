package com.ekenya.rnd.personalfinance

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ekenya.rnd.baseapp.MobileBankingApp
import com.ekenya.rnd.common.abstractions.BaseActivity
import com.ekenya.rnd.personalfinance.databinding.ActivityMainPersonalFinanceBinding
import dagger.android.AndroidInjector
import javax.inject.Inject

class PersonalFinanceMainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainPersonalFinanceBinding

    @JvmField
    @Inject
    var viewModelFactory: ViewModelProvider.Factory? = null

    //private MainViewModel mViewModel;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPersonalFinanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        // Fragment Injector should use the Application class
        // If necessary, I will use AndroidInjector as well as App class (I have not done this time)
        return (application as MobileBankingApp).supportFragmentInjector()
    }

}