package ru.danilarassokhin.dao

import java.io.Serializable

data class CurrencyRateId(val original: String, val target: String): Serializable
