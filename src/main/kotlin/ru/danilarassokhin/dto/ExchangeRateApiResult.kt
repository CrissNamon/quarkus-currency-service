package ru.danilarassokhin.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ExchangeRateApiResult(
    @field:JsonProperty("rates")
    val rates: HashMap<String, Double>? = null
)