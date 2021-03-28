package de.amirrocker.hadesGatekeeper.domain.common

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * A representation of a value amount. It can be used anytime and anywhere a value has to be used.
 * This is a simple ValueType or ValueObject - VO - and should never be used on its own without an
 * enclosing aggregate.
 */
class ValueAmount(
        private val amount: BigDecimal?
) {

    companion object {

        fun zero(): ValueAmount? {
            return from(BigDecimal("0.00"))
        }

        fun from(amount: BigDecimal?): ValueAmount? {
            if (amount == null) {
                throw RuntimeException("Amount for ValueAmount cannot be null")
            }
            return ValueAmount(amount)
        }

        fun from(amount: String?): ValueAmount? {
            if (amount == null) {
                throw RuntimeException("Amount for ValueAmount cannot be null")
            }
            return ValueAmount(BigDecimal(amount))
        }

        fun from(amount: Int?): ValueAmount? {
            if (amount == null) {
                throw RuntimeException("Amount for ValueAmount cannot be null")
            }
            return ValueAmount(BigDecimal(amount))
        }
    }

    fun add(valueAmount: ValueAmount?): ValueAmount? {
        if (valueAmount == null) {
            throw RuntimeException("Cant save null MonetaryAmount")
        }
        return ValueAmount(amount?.add(valueAmount.toBigDecimal()))
    }

    fun subtract(valueAmount: ValueAmount?): ValueAmount? {
        if (valueAmount == null) {
            throw RuntimeException("Cant subtract null MonetaryAmount")
        }
        return ValueAmount(amount?.subtract(valueAmount.toBigDecimal()))
    }

    fun isPositive(): Boolean {
        return amount?.signum() == 1
    }

    fun isNegative(): Boolean {
        return amount?.signum() == -1
    }

    fun isZero(): Boolean = amount?.signum() == 0


    fun isPositiveOrZero(): Boolean = isPositive() || isZero()


    fun isNegativeOrZero(): Boolean = isNegative() || isZero()


    fun greaterThan(monetaryAmount: ValueAmount): Boolean =
            this.compareTo(monetaryAmount) == 1

    /* TODO Check and find Better solution than !! */
    fun greaterOrEqual(monetaryAmount: ValueAmount): Boolean =
            this.compareTo(monetaryAmount)!! >= 0


    fun lowerThan(monetaryAmount: ValueAmount): Boolean =
            this.compareTo(monetaryAmount) == -1


    fun lowerOrEqual(monetaryAmount: ValueAmount): Boolean =
            this.compareTo(monetaryAmount)!! <= 0


    fun equalTo(monetaryAmount: ValueAmount): Boolean =
            this.compareTo(monetaryAmount) == 0


    fun toWholeNumber(): ValueAmount? =
            ValueAmount(amount?.setScale(0, RoundingMode.HALF_UP))


    fun round(numerOfDecimalPlaces: Int): ValueAmount? =
            ValueAmount(amount?.setScale(numerOfDecimalPlaces, RoundingMode.HALF_UP))


    fun min(first: ValueAmount, second: ValueAmount): ValueAmount? =
            if (first.compareTo(second)!! < 0) first else second


    fun max(first: ValueAmount, second: ValueAmount): ValueAmount? =
            if (first.compareTo(second)!! >= 0) first else second


    fun multiply(multiplier: BigDecimal?): ValueAmount? =
            ValueAmount(amount?.multiply(multiplier))


    fun multiply(multiplier: Int): ValueAmount? =
            ValueAmount(amount?.let { it.multiply(BigDecimal.valueOf(multiplier.toLong())) })


    fun multiply(multiplier: BigDecimal?, rounding: RoundingMode?): ValueAmount? {
        val multiplication: BigDecimal? = amount?.let { it.multiply(multiplier) }
        return ValueAmount(multiplication?.let { it.setScale(2, rounding) })
    }

    fun toBigDecimal(): BigDecimal =
            BigDecimal(amount.toString()) // amount;


    operator fun compareTo(o: ValueAmount): Int =
            amount?.let { it.compareTo(o.amount) }!!


    override fun equals(`object`: Any?): Boolean {
        return if (`object` !is ValueAmount) {
            false
        } else amount == `object`.toBigDecimal()
    }

    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 29 + amount.hashCode()
        return hash
    }

    override fun toString(): String {
        val df = DecimalFormat()
        df.maximumFractionDigits = 2
        df.minimumFractionDigits = 0
        df.isGroupingUsed = false
        return df.format(this.amount)
    }



}