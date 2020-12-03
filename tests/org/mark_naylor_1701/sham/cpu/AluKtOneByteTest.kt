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

        val currentSP: ShamWord = registers[spCode]!!
        val expectedSP = currentSP + ShamWord(6)

        val twenty = ShamWord(20)
        val eighty = ShamWord(80)

        val virginMemory = newMemory(64)
        val pushOneMemory = write(virginMemory, ShamWord(4), one)
        val pushTwoMemory = write(pushOneMemory, ShamWord(2), eighty)
        val pushThreeMemory = write(pushTwoMemory, zero, twenty)

        val (newRegisters, _) = `return`(registers, pushThreeMemory)

        val newSP: ShamWord = newRegisters[spCode]!!
        assert(newSP == expectedSP) { "SP error: $newSP should be $expectedSP"}

        val newIP: ShamWord = newRegisters[ipCode]!!
        assert(newIP == twenty) { "IP error: $newIP should be $twenty" }

        val newDR: ShamWord = newRegisters[drCode]!!
        assert(newDR == eighty) { "DR errro: $newDR should be $eighty" }

        val newFR: ShamWord = newRegisters[frCode]!!
        assert(newFR == one) { "FR error: $newFR should be $one" }
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
        val (newRegisters, newControl) = enable(registers, control)

        assert(newControl.isInterrupted) { "Not interrupt not enabled."}
        assert(newRegisters[ipCode] == one) { "IP not advanced correctly." }
    }

    @Test
    fun disable() {
        val (newRegisters, newControl) = enable(registers, control)

        assert(newControl.isInterrupted) { "Not interrupt not enabled."}
        assert(newRegisters[ipCode] == one) { "IP not advanced correctly." }
    }

}
