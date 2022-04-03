package ru.danilarassokhin.service

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import ru.danilarassokhin.dto.ExchangeRateApiResult
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@RegisterRestClient
interface ExchangeRateApiClient {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun convertWithApi(@QueryParam("base") base: String,
                       @QueryParam("symbols") symbols: String): ExchangeRateApiResult
}