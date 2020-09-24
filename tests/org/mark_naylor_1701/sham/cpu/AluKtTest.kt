package org.mark_naylor_1701.sham.cpu

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mark_naylor_1701.sham.Types.ShamWord

internal class AluKtTest {

    private val one by lazy { ShamWord(1) }
    private val ipName = RegisterName.newRegisterName("ip")
    private val ipCode = registerCode(ipName)!!
    private val axName = RegisterName.newRegisterName("ax")
    private val axCode = registerCode(axName)!!

    private lateinit var control: Control
    private lateinit var registers: Registers

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

    @Test
    fun negate() {
        registers += axCode to ShamWord(8)

        val newRegisters = negate(registers)

        assert(newRegisters[ipCode] == one) { "IP not advanced correctly." }
        assert(newRegisters[axCode] == ShamWord(-8)) { "Not negated." }

    }

    private fun notImplemented() = "Not Implemented."

}
