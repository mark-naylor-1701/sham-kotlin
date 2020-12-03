package org.mark_naylor_1701.sham.cpu

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mark_naylor_1701.sham.Types.ShamWord

internal class AluKtOneByteTest {

    @BeforeEach
    internal fun setUp() {
        control = Control()
        registers = newRegisters()
    }

    @Test
    fun nop() {
        val newRegisters = nop(registers)

        assert(newRegisters[ipCode] == one) { "IP not advanced correctly." }
    }

    @Test
    fun random() {
        registers += Pair(axCode, ShamWord(-1))

        var newRegisters = random(registers)

        assert(newRegisters[ipCode] == one) { "IP not advanced correctly." }
        assert(newRegisters[axCode]!!.value >= 0)
    }

    @Test
    fun `return`() {
        // Default to failure until the test is fleshed out.
        assert(false)
    }


    @Test
    fun terminate() {
        val (_, newControl) = terminate(registers, control)

        assert(!newControl.isRunning) { "SHAM still running." }
    }

    @Test
    fun traceOn() {
        val (newRegisters, newControl) = traceOn(registers, control)

        assert(newControl.isTraced) { "Tracing is off." }
        assert(newRegisters[ipCode] == one) { "IP not advanced correctly." }
    }

    @Test
    fun traceOff() {
        val (newRegisters, newControl) = traceOff(registers, control)

        assert(!newControl.isTraced) { "Tracing is on." }
        assert(newRegisters[ipCode] == one) { "IP not advanced correctly." }
    }

    private fun notImplemented() = "Not Implemented."

    @Test
    fun enable() {
        // Default to failure until the test is fleshed out.
        assert(false)
    }

    @Test
    fun disable() {
        // Default to failure until the test is fleshed out.
        assert(false)
    }

}
