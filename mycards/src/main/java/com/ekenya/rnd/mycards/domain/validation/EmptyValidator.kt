package com.ekenya.rnd.mycards.domain.validation

import com.ekenya.rnd.mycards.R
import com.ekenya.rnd.mycards.domain.validation.base.ValidateResult
import com.ekenya.rnd.mycards.domain.validation.base.BaseValidator

class EmptyValidator(private val input: String) : BaseValidator() {
    override fun validate(): ValidateResult {
        val isValid = input.isNotEmpty()
        return ValidateResult(
            isValid,
            if (isValid) R.string.text_validation_success else R.string.text_validation_error_empty_field
        )
    }
}