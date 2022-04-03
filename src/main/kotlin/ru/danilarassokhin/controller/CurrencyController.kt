package ru.danilarassokhin.controller

import ru.danilarassokhin.dto.CurrencyExchangeRateRequestDto
import ru.danilarassokhin.dto.ErrorResultDto
import ru.danilarassokhin.service.CurrencyService
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/convert")
class CurrencyController(val currencyService: CurrencyService) {
    
    @POST
    fun getExchangeRate(data: CurrencyExchangeRateRequestDto): Response {
        val result = currencyService.convert(data)
            ?: return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(
                    ErrorResultDto("Internal error")
                )
                .build()
        return Response
            .status(Response.Status.OK)
            .type(MediaType.APPLICATION_JSON)
            .entity(result)
            .build()
    }
}