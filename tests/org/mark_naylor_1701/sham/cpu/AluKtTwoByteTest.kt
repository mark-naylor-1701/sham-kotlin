package org.mark_naylor_1701.sham.cpu

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.mark_naylor_1701.sham.Types.ShamWord

internal class AluKtTwoByteTest {

    private lateinit var control: Control
    private lateinit var registers: Registers

    @BeforeEach
    internal fun setUp() {
        control = Control()
        registers = newRegisters()
    }

    @Test
    fun negate() {

        registers += axCode to ShamWord(8)

        val newRegisters = negate(registers)

        assert(newRegisters[ipCode] == one) { "IP not advanced correctly." }
        assert(newRegisters[axCode] == ShamWord(-8)) { "Not negated." }
    }

    @Test
    fun increment() {
        // Default to failure until the test is fleshed out.
        assert(false)
    }

    @Test
    fun decrement() {
        // Default to failure until the test is fleshed out.
        assert(false)
    }

    @Test
    fun push() {
        // Default to failure until the test is fleshed out.
        assert(false)
    }

    @Test
    fun pop() {
        // Default to failure until the test is fleshed out.
        assert(false)
    }

    @Test
    fun move() {
        // Default to failure until the test is fleshed out.
        assert(false)
    }

    @Test
    fun add() {
        // Default to failure until the test is fleshed out.
        assert(false)
    }

    @Test
    fun subtract() {
        // Default to failure until the test is fleshed out.
        assert(false)
    }

    @Test
    fun multiply() {
        // Default to failure until the test is fleshed out.
        assert(false)
    }

    @Test
    fun divide() {
        // Default to failure until the test is fleshed out.
        assert(false)
    }

    @Test
    fun compare() {
        // Default to failure until the test is fleshed out.
        assert(false)
    }
}
