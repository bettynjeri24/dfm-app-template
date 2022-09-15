package com.ekenya.rnd.mycards.domain.validation.base

interface IValidator {
    fun validate() : ValidateResult
}