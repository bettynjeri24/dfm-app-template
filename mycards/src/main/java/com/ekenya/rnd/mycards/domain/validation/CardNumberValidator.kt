package com.ekenya.rnd.mycards.domain.validation

import com.ekenya.rnd.mycards.R
import com.ekenya.rnd.mycards.domain.validation.base.BaseValidator
import com.ekenya.rnd.mycards.domain.validation.base.ValidateResult

class CardNumberValidator(private val cardNumber : String) : BaseValidator() {

    override fun validate(): ValidateResult {
        return isValidCardNumber(cardNumber)
    }

    private fun isValidCardNumber(cardNumber: String) : ValidateResult{
        if (cardNumber.length < 16){
            return ValidateResult(false,R.string.card_number_short)
        }

        if (cardNumber.length > 16){
            return ValidateResult(false, R.string.card_number_validation_long)
        }

        return ValidateResult(true,R.string.text_validation_success)
    }
}