package com.ekenya.rnd.merchant.utils

sealed class MerchantHomeOptionState {
    object DirectPayment : MerchantHomeOptionState()
    object ScanAndPay : MerchantHomeOptionState()
    object HotDeals : MerchantHomeOptionState()
    object EventsAndTickets : MerchantHomeOptionState()

}

