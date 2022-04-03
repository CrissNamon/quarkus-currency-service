package ru.danilarassokhin.dto

import javax.validation.constraints.NotEmpty

data class CurrencyExchangeRateRequestDto(
    @field:NotEmpty(message = "Base currency can't be empty")
    val from: String,
    @field:NotEmpty(message = "Target currency can't be empty")
    val to: String
    )
