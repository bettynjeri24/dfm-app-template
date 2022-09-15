package com.ekenya.rnd.personalfinance.ui.getstarted

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.personalfinance.R
import com.ekenya.rnd.personalfinance.databinding.FragmentGetstartedPersonalFinanceBinding
import com.ekenya.rnd.personalfinance.utils.setBackButton
import javax.inject.Inject

class GetStartedFragment : BaseDaggerFragment() {
    @JvmField
    @Inject
    var viewModelFactory: ViewModelProvider.Factory? = null
    private var getStartedViewModel: GetStartedViewModel? = null
    private lateinit var binding: FragmentGetstartedPersonalFinanceBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        getStartedViewModel = ViewModelProvider(this, viewModelFactory!!).get<GetStartedViewModel>(
            GetStartedViewModel::class.java
        )
        binding = FragmentGetstartedPersonalFinanceBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.toolbar.setBackButton(R.string.title_personal_finance, requireActivity(), Color.WHITE)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGetStarted.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_getstarted_to_navigation_home)
        }
    }

}