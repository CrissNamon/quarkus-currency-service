package ru.danilarassokhin.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ExchangeRateApiResult(
    @JsonProperty("rates")
    var rates: HashMap<String, Double> = HashMap()
)