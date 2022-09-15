package com.ekenya.rnd.mycards.utils

sealed class HomeOption{
    object TopUp : HomeOption()
    object Travel : HomeOption()
    object MiniStatement : HomeOption()
    object UpdateLimits : HomeOption()
    object ChangePin : HomeOption()
    object BlockCard : HomeOption()
    object NotFound : HomeOption()
}
