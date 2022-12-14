package com.ekenya.rnd.mycards.domain.validation

import android.text.TextUtils
import com.ekenya.rnd.mycards.R
import com.ekenya.rnd.mycards.domain.validation.base.ValidateResult
import com.ekenya.rnd.mycards.domain.validation.base.BaseValidator

class EmailValidator(private val email: String) : BaseValidator() {
    override fun validate(): ValidateResult {
        val isValid =
            !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        return ValidateResult(
            isValid,
            if (isValid) R.string.text_validation_success else R.string.text_validation_error_email
        )
    }
}