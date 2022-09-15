package com.ekenya.rnd.mycards.domain.use_case

interface BaseUseCase <in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Result
}