package com.ekenya.rnd.mycards.di.modules

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.ekenya.rnd.common.auth.CommonAuthFragment
import com.ekenya.rnd.common.successful.SuccessfulFragmentCommon
import com.ekenya.rnd.mycards.ui.fragments.auth.AuthFragmentMyCards
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.limits.BottomSheetLimits
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.change_pin.BottomSheetChangePin
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.link_card.BottomSheetLinkCard
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.top_up_card.BottomSheetTopUpCard
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.travel_notification.BottomSheetTravelNotification
import com.ekenya.rnd.mycards.ui.fragments.home.MyCardsHomeFragment
import com.ekenya.rnd.mycards.ui.fragments.home.MyCardsHomeViewModel
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.block_card.BottomSheetBlockCard
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.link_card.LinkCardViewModel
import com.ekenya.rnd.mycards.ui.fragments.home.bottom_sheets.request_card.BottomSheetRequestCard
import com.ekenya.rnd.mycards.ui.fragments.mini_statement.FragmentMiniStatement
import com.ekenya.rnd.mycards.ui.fragments.successful_fragment.SuccessfulFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MyCardsFragmentModule {

    @ContributesAndroidInjector(modules = [FragmentMyCardsHomeModule::class])
    abstract fun contributeMainFragment(): MyCardsHomeFragment

    @Module
    abstract class FragmentMyCardsHomeModule {
        @Binds
        @IntoMap
        @ViewModelKey(MyCardsHomeViewModel::class)
        abstract fun bindMyCardsHomeViewModel(viewModelSelectContacts: MyCardsHomeViewModel): ViewModel
    }

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeMiniStatementFragment(): FragmentMiniStatement

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeBottomSheetAtmWithdrawalFragment(): BottomSheetLimits

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeBottomSheetChangePinFragment(): BottomSheetChangePin

    @ContributesAndroidInjector(modules = [BottomSheetLinkCardModule::class])
    abstract fun contributeBottomSheetLinkCardFragment(): BottomSheetLinkCard

    @Module
    abstract class BottomSheetLinkCardModule {
        @Binds
        @IntoMap
        @ViewModelKey(LinkCardViewModel::class)
        abstract fun bindMyCardsHomeViewModel(linkCardViewModel: LinkCardViewModel): ViewModel
    }

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeBottomSheetRequestCardFragment(): BottomSheetRequestCard

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeBottomSheetTopUpCardFragment(): BottomSheetTopUpCard

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeBottomSheetBlockCardFragment(): BottomSheetBlockCard


    @ContributesAndroidInjector(modules = [])
    abstract fun contributeBottomSheetTravelNotificationFragment(): BottomSheetTravelNotification

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeBottomAuthFragment(): CommonAuthFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeSuccessFragment(): SuccessfulFragmentCommon

}
