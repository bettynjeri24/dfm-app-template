package com.ekenya.rnd.merchant.utils

sealed class PayForMeStates{
    object PayForMe : PayForMeStates()
    object IWillPAY: PayForMeStates()
}
