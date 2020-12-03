package org.mark_naylor_1701.sham.cpu

import org.mark_naylor_1701.sham.Types.ShamWord


val shamOne by lazy { ShamWord(1) }
val shamZero by lazy { ShamWord(0) }
val ipName = RegisterName.newRegisterName("ip")
val ipCode = registerCode(ipName)!!
val axName = RegisterName.newRegisterName("ax")
val axCode = registerCode(axName)!!
val spName = RegisterName.newRegisterName("sp")
val spCode = registerCode(spName)!!
val drName = RegisterName.newRegisterName("dr")
val drCode = registerCode(drName)!!
val frName = RegisterName.newRegisterName("fr")
val frCode = registerCode(frName)!!

lateinit var control: Control
lateinit var registers: Registers
