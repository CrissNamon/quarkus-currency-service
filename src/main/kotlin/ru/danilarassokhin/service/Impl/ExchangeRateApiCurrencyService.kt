package ru.danilarassokhin.service.Impl

import org.eclipse.microprofile.rest.client.inject.RestClient
import ru.danilarassokhin.dao.CurrencyRateDao
import ru.danilarassokhin.dao.CurrencyRateId
import ru.danilarassokhin.dto.CurrencyExchangeRateRequestDto
import ru.danilarassokhin.dto.CurrencyExchangeRateResultDto
import ru.danilarassokhin.service.CurrencyService
import ru.danilarassokhin.service.ExchangeRateApiClient
import java.time.LocalDate
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class ExchangeRateApiCurrencyService(@RestClient val restClient: ExchangeRateApiClient): CurrencyService {

    @Transactional
    override fun convert(data: CurrencyExchangeRateRequestDto): CurrencyExchangeRateResultDto? {
        val currencyRateDao = CurrencyRateDao.findByIdAndDate(
            CurrencyRateId(data.from, data.to),
            LocalDate.now()
        ) ?: getRateFromApi(data)
        return CurrencyExchangeRateResultDto(data.to, currencyRateDao.rate)
    }

    private fun getRateFromApi(data: CurrencyExchangeRateRequestDto): CurrencyRateDao {
        val res = restClient.convertWithApi(data.from, data.to)
        val newRate = CurrencyRateDao()
        newRate.currencyRateId = CurrencyRateId(data.from, data.to)
        newRate.date = LocalDate.now()
        newRate.rate = res.rates[data.to] ?: 0.0
        newRate.persist()
        return newRate
    }
}