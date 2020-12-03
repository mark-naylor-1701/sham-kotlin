package org.mark_naylor_1701.sham.cpu

import org.mark_naylor_1701.sham.Types.ShamWord


val one by lazy { ShamWord(1) }
val ipName = RegisterName.newRegisterName("ip")
val ipCode = registerCode(ipName)!!
val axName = RegisterName.newRegisterName("ax")
val axCode = registerCode(axName)!!

lateinit var control: Control
lateinit var registers: Registers
