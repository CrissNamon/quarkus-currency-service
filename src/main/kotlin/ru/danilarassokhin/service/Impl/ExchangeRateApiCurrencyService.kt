package ru.danilarassokhin.service.Impl

import org.eclipse.microprofile.rest.client.inject.RestClient
import ru.danilarassokhin.dto.CurrencyExchangeRateRequestDto
import ru.danilarassokhin.dto.CurrencyExchangeRateResultDto
import ru.danilarassokhin.service.CurrencyService
import ru.danilarassokhin.service.ExchangeRateApiClient
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ExchangeRateApiCurrencyService(@RestClient val restClient: ExchangeRateApiClient): CurrencyService {
    override fun convert(data: CurrencyExchangeRateRequestDto): CurrencyExchangeRateResultDto? {
        val res = restClient.convertWithApi(data.from, data.to)
        if(res.rates == null) {
            return null
        }
        return res.rates[data.to]?.let { CurrencyExchangeRateResultDto(data.to, it) }
    }
}