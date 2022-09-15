package com.ekenya.rnd.wallet.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.ekenya.rnd.common.auth.CommonAuthFragment
import com.ekenya.rnd.common.successful.SuccessfulFragmentCommon
import com.ekenya.rnd.wallet.ui.airtime.BuyAirTimeWalletFragment
import com.ekenya.rnd.wallet.ui.airtime.HomeBuyAirTimeFragment
import com.ekenya.rnd.wallet.ui.airtime.SelectBuyAirTimeFromFragment
import com.ekenya.rnd.wallet.ui.bills.GeneralBillPaymentFragmentWallet
import com.ekenya.rnd.wallet.ui.bills.KplcPrepaidFragmentWallet
import com.ekenya.rnd.wallet.ui.bills.ZukuInternetFragmentWallet
import com.ekenya.rnd.wallet.ui.bills.tabs.Tab1AllBillPaymentWalletFragment
import com.ekenya.rnd.wallet.ui.bills.tabs.Tab2UpcomingBillsPaymentWalletFragment
import com.ekenya.rnd.wallet.ui.deposit.DepositMpesaToAccountFragment
import com.ekenya.rnd.wallet.ui.deposit.cheque.StartChequeDepositWalletFragment
import com.ekenya.rnd.wallet.ui.deposit.cheque.Tab1DepositChequeFragmentWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.FundsTransferFragmentWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.banktransfers.eft.EftFragmentWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.banktransfers.internaltransfer.InternalTransferFragmentWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.banktransfers.owntransfer.OwnTransferFragmentWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.banktransfers.rtgs.RtgsFragmentWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.MainMobileMoneyFundTransferWalletFragment
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.MobileMoneyFundTransferWalletFragment
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.selectcontacts.FragmentSelectContactWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.selectcontacts.FragmentSplitSendToMobileMoneyWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.tabs.TabAllContactsWalletFragment
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.tabs.TabFrequentContactsFragment
import com.ekenya.rnd.wallet.ui.fundtransfer.pesalink.settings.PesaLinkSettingsFragmentWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.pesalink.toaccount.PesalinkToAccountFragmentWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.pesalink.tocard.PesalinkToCardFragmentWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.pesalink.tophone.PesaLinkToPhoneFragmentWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.requestfunds.mpesatoaccount.MpesaToAccountFragmentWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.requestfunds.nfc.NFCFragmentWallet
import com.ekenya.rnd.wallet.ui.home.HomeViewModel
import com.ekenya.rnd.wallet.ui.home.HomeWalletFragment
import com.ekenya.rnd.wallet.ui.home.mainFragments.MainBuyAirTimeWalletFragment
import com.ekenya.rnd.wallet.ui.home.mainFragments.MainDepositWalletFragment
import com.ekenya.rnd.wallet.ui.home.mainFragments.MainFundsTransferFragmentWallet
import com.ekenya.rnd.wallet.ui.myaccount.*
import com.ekenya.rnd.wallet.ui.myaccount.tabs.TabHolderMyAccountsFragment
import com.ekenya.rnd.wallet.ui.myaccount.tabs.dormant.Tab3DormantMyAccountFragment
import com.ekenya.rnd.wallet.ui.myaccount.tabs.linked.Tab1LinkedMyAccountFragment
import com.ekenya.rnd.wallet.ui.myaccount.tabs.unlink.Tab2UnLinkedMyAccountFragment
import com.ekenya.rnd.wallet.ui.withdraw.CardlessWithdrawalFragmentWallet
import com.ekenya.rnd.wallet.ui.withdraw.StartWithdrawMoneyFragmentWallet
import com.ekenya.rnd.wallet.ui.withdraw.WithdrawFromAgentFragmentWallet
import com.ekenya.rnd.wallet.ui.withdraw.w2.WithdrawFragmentWallet
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FragmentWalletModule {

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_HomeFragment(): HomeWalletFragment

    @Module
    abstract class WalletHomeFragmentModule {
        @Binds
        @IntoMap
        @ViewModelKey(HomeViewModel::class)
        abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
    }

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_FundTransferFragment(): FundsTransferFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_WithdrawFragment(): WithdrawFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_DepositMpesaToAccountFragment(): DepositMpesaToAccountFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_MainDepositWalletFragment(): MainDepositWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_StartChequeDepositWalletFragment(): StartChequeDepositWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_MainFundsTransferFragmentWallet(): MainFundsTransferFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_AddAccountWalletFragment(): AddAccountWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_WithdrawMyAccountWalletFragment(): WithdrawMyAccountWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_CheckBookRequestAccountWalletFragment(): CheckBookRequestAccountWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_PesalinkToAccountFragmentWallet(): PesalinkToAccountFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_PesaLinkToPhoneFragmentWallet(): PesaLinkToPhoneFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_MobileMoneyFundTransferWalletFragment(): MobileMoneyFundTransferWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_MainMobileMoneyFundTransferWalletFragment():
        MainMobileMoneyFundTransferWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_OwnTransferFragmentWallet():
        OwnTransferFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_InternalTransferFragmentWallet():
        InternalTransferFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_EftFragmentWallet():
        EftFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_RtgsFragmentWallet():
        RtgsFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_MpesaToAccountFragmentWallet():
        MpesaToAccountFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_StartWithdrawMoneyFragmentWallet():
        StartWithdrawMoneyFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_WithdrawFromAgentFragmentWallet():
        WithdrawFromAgentFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_CardlessWithdrawalFragmentWallet():
        CardlessWithdrawalFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_Tab1DepositChequeFragmentWallet():
        Tab1DepositChequeFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_BuyAirTimeWalletFragment():
        BuyAirTimeWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_FullStatementAccountWalletFragment():
        FullStatementAccountWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_MiniStatementAccountWalletFragment():
        MiniStatementAccountWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_ZukuInternetFragmentWallet():
        ZukuInternetFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_KplcPrepaidFragmentWallet():
        KplcPrepaidFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_GeneralBillPaymentFragmentWallet():
        GeneralBillPaymentFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_MainBuyAirTimeWalletFragment():
        MainBuyAirTimeWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_TabHolderMyAccountsFragment():
        TabHolderMyAccountsFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_Tab1LinkedMyAccountFragment():
        Tab1LinkedMyAccountFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_Tab2UnLinkedMyAccountFragment():
        Tab2UnLinkedMyAccountFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_Tab3DormantMyAccountFragment():
        Tab3DormantMyAccountFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_MyAccountDetailsPremierWalletFragment():
        MyAccountDetailsPremierWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_Tab2UpcomingBillsPaymentWalletFragment():
        Tab2UpcomingBillsPaymentWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_Tab1AllBillPaymentWalletFragment():
        Tab1AllBillPaymentWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_SelectBuyAirTimeFromFragment():
        SelectBuyAirTimeFromFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_HomeBuyAirTimeFragment():
        HomeBuyAirTimeFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_FragmentSelectContactWallet():
        FragmentSelectContactWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_FragmentSplitSendToMobileMoneyWallet():
        FragmentSplitSendToMobileMoneyWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_NFCFragmentWallet():
        NFCFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_PesalinkToCardFragmentWallet():
        PesalinkToCardFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_PesaLinkSettingsFragmentWallet():
        PesaLinkSettingsFragmentWallet

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_TabAllContactsWalletFragment():
        TabAllContactsWalletFragment

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_TabFrequentContactsFragment():
        TabFrequentContactsFragment

    /*    @ContributesAndroidInjector(modules = [TourismDashboardFragmentModule::class])
       abstract fun contributeDashboardFragment(): DashboardFragment

       @Module
       abstract class TourismDashboardFragmentModule {
           @Binds
           @IntoMap
           @ViewModelKey(DashboardViewModel::class)
           abstract fun bindHomeViewModel(viewModel: DashboardViewModel): ViewModel
       }
       //LIST THE OTHER INJECTABLE FRAGMENTS AS ABOVE*/

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_SuccessfulFragmentWallet():
        SuccessfulFragmentCommon

    @ContributesAndroidInjector(modules = [WalletHomeFragmentModule::class])
    abstract fun contribute_CommonAuthFragment():
        CommonAuthFragment
}
