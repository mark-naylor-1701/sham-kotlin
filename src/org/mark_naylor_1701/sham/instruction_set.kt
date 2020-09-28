/*
author: Mark W. Naylor
file:  instruction_set.kt
date:  2020-Aug-23

/////////////////////////////////////////////////////////////////////////////
Data structures for machine codes and assembler mnemonics.
/////////////////////////////////////////////////////////////////////////////
 */

// TODO: Create value classes for opcodes and mnemonics.

// TODO: Make the values private to hide implementation, only access
// via the functions.

package org.mark_naylor_1701.sham

import org.mark_naylor_1701.sham.Types.*
import org.mark_naylor_1701.CollectUtils.butLast

object MachineCode {
    val oneByteCodes: List<Mnemonic> = listOf(
        "nop",
        "random",
        "return",
        "terminate",
        "trace_on",
        "trace_off",
        "enable",
        "disable"
    ).map { code -> Mnemonic(code) }

    val twoByteCodes: List<Mnemonic> = listOf(
        "negate",
        "increment",
        "decrement",
        "clear",
        "push",
        "pop",
        "move",
        "add",
        "subtract",
        "multiply",
        "divide",
        "compare"
    ).map { code -> Mnemonic(code)}

    val fourByteCodes: List<Mnemonic> = listOf(
        "in",
        "out",
        "branch",
        "call",
        "fetch_byte",
        "fetch_word",
        "store_byte",
        "store_word",
        "stream",
        "testandset",
        "interrupt"
    ).map { code -> Mnemonic(code) }

    val opcodeDirectionDiff: OpCode = OpCode(0x10.toByte())
    val invalidCode: OpCode = OpCode(Byte.MAX_VALUE)

    val fourByteIndirectCodes: List<Mnemonic> =
        fourByteCodes.butLast().map { directCode ->  Mnemonic("-" + directCode.value) }

    val partialCodeMap: Map<Mnemonic, OpCode> =
        (oneByteCodes + twoByteCodes + fourByteCodes).withIndex()
        .associate { codePair ->  codePair.value to OpCode(codePair.index.toByte()) }

    val indirectCodeMap: Map<Mnemonic, OpCode> = fourByteIndirectCodes.map { indirectName ->
        val indirectCode: OpCode =
            indirectName.value.substring(1).let { directName ->
                partialCodeMap[Mnemonic(directName)]?.let { directCode ->
                    OpCode((directCode.value + opcodeDirectionDiff.value).toByte())
            }
        } ?: invalidCode

        indirectName to indirectCode
    }.filter { it.second != invalidCode }.toMap()

    val mnemonicsToOpcode = partialCodeMap + indirectCodeMap
    val opcodesToMnemonic = mnemonicsToOpcode.map { it.value to it.key}.toMap()

    fun opcode(mnemonic: Mnemonic):OpCode? = mnemonicsToOpcode[mnemonic]

    fun mnemonic(opcode: OpCode):Mnemonic? = opcodesToMnemonic[opcode]

    fun isIndirect(code: OpCode) =
        opcode(fourByteCodes.last())?.let { code.value > it.value } ?: false

}

// ------------------------------------------------------------------------------
// BSD 3-Clause License

// Copyright © 2020, Mark W. Naylor
// All rights reserved.

// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:

// 1. Redistributions of source code must retain the above copyright notice, this
//    list of conditions and the following disclaimer.

// 2. Redistributions in binary form must reproduce the above copyright notice,
//    this list of conditions and the following disclaimer in the documentation
//    and/or other materials provided with the distribution.

// 3. Neither the name of the copyright holder nor the names of its
//    contributors may be used to endorse or promote products derived from
//    this software without specific prior written permission.

// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
// SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
// CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
// OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
