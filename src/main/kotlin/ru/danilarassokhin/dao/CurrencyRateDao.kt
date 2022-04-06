package ru.danilarassokhin.dao

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanionBase
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.time.LocalDate
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "currency_cache")
class CurrencyRateDao : PanacheEntityBase {

    companion object : PanacheCompanionBase<CurrencyRateDao, CurrencyRateId> {
        fun findByIdAndDate(currencyRateId: CurrencyRateId, date: LocalDate): CurrencyRateDao? =
            findById(currencyRateId)?.takeIf { it.date.isEqual(date) }
    }

    @EmbeddedId
    lateinit var currencyRateId: CurrencyRateId
    var rate: Double = 0.0
    lateinit var date: LocalDate
}
