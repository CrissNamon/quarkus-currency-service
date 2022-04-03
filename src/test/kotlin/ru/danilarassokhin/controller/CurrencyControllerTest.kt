package ru.danilarassokhin.controller

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.greaterThan
import org.junit.jupiter.api.Test
import ru.danilarassokhin.dto.CurrencyExchangeRateRequestDto
import javax.ws.rs.core.MediaType

@QuarkusTest
class CurrencyControllerTest {

    @Test
    fun testWrongCurrencyCodes() {
        val data = CurrencyExchangeRateRequestDto("WRONG BASE", "WRONG TARGET")

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(data)
            .`when`()
            .post("/api/convert")
            .then()
            .statusCode(500)
    }

    @Test
    fun testValidCurrencyCodes() {
        val data = CurrencyExchangeRateRequestDto("USD", "RUB")

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(data)
            .`when`()
            .post("/api/convert")
            .then()
            .statusCode(200)
            .body(
                "rate", greaterThan(0.0f),
                "code", `is`("RUB")
            )
    }

}