package com.ekenya.rnd.merchant.di.modules


import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.ekenya.rnd.merchant.ui.bottom_sheets.BottomSheetFilter
import com.ekenya.rnd.merchant.ui.fragments.FragmentScanAndPayMerchant
import com.ekenya.rnd.merchant.ui.fragments.auth.AuthFragmentMerchant
import com.ekenya.rnd.merchant.ui.fragments.direct_payment.bottom_sheet_merchant_payment.BottomSheetMerchantPayment
import com.ekenya.rnd.merchant.ui.fragments.split_bill.FragmentSplitBill
import com.ekenya.rnd.merchant.ui.fragments.direct_payment.FragmentDirectPayment
import com.ekenya.rnd.merchant.ui.fragments.select_contact.FragmentSelectContact
import com.ekenya.rnd.merchant.ui.fragments.select_contact.ViewModelSelectContacts
import com.ekenya.rnd.merchant.ui.fragments.home.FragmentHomeMerchant
import com.ekenya.rnd.merchant.ui.fragments.successful_screen.SuccessfulFragmentMerchant
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MerchantFragmentsModule {

    @ContributesAndroidInjector()
    abstract fun contributeBottomSheetMerchant(): BottomSheetMerchantPayment

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeMainFragment(): FragmentHomeMerchant

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeDirectPaymentFragment(): FragmentDirectPayment

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeScanAndPayFragment(): FragmentScanAndPayMerchant

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeSplitBillFragment(): FragmentSplitBill

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeAuthFragment(): AuthFragmentMerchant

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeFilterBottomSheet(): BottomSheetFilter

    @ContributesAndroidInjector(modules = [FragmentSelectContactFragmentModule::class])
    abstract fun contributeFragmentSelectContact(): FragmentSelectContact

    @Module
    abstract class FragmentSelectContactFragmentModule {
        @Binds
        @IntoMap
        @ViewModelKey(ViewModelSelectContacts::class)
        abstract fun bindSelectContactsViewModel(viewModelSelectContacts: ViewModelSelectContacts): ViewModel
    }

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeSuccessfulFragment(): SuccessfulFragmentMerchant

}
