package ru.danilarassokhin.service

import ru.danilarassokhin.dto.CurrencyExchangeRateRequestDto
import ru.danilarassokhin.dto.CurrencyExchangeRateResultDto

interface CurrencyService {
    fun convert(data: CurrencyExchangeRateRequestDto): CurrencyExchangeRateResultDto?
}