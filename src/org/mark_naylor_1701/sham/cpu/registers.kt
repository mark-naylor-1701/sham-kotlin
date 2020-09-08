// author: Mark W. Naylor
// file:  registers.kt
// date:  2020-Aug-30

package org.mark_naylor_1701.sham.cpu

import org.mark_naylor_1701.sham.Types.*


data class RegisterName private constructor(val value: String) {
    companion object {
        fun newRegisterName(name: String): RegisterName {
            val fixedName: String = name.toLowerCase()
            return when (fixedName) {
                in legalRegisterNames -> RegisterName(fixedName)
                else -> throw RegisterNameException("$name is not a legal register name.")
            }
        }
    }
}

data class RegisterCode private constructor (val value: Byte) {
    companion object {
        fun newRegisterCode(sb: ShamByte): RegisterCode = when (sb.value) {
            in (0 until legalRegisterNames.size) -> RegisterCode(sb.value)
            else -> throw RegisterCodeException("$sb.value outside legal register code fun.")
        }

        fun newRegisterCode(n: Number): RegisterCode = when (n) {
            in (0 until legalRegisterNames.size) -> RegisterCode(n.toByte())
            else -> throw RegisterCodeException("$n.value outside legal register code fun.")
        }
    }
}

typealias Registers = Map<RegisterCode, ShamWord>


class RegisterNameException(msg: String): Exception(msg)
class RegisterCodeException(msg: String): Exception(msg)
class BranchNameException(msg: String): Exception(msg)

private val legalRegisterNames: List<String> = listOf (
    "nx", "ax", "bx", "cx", "dx", "ex", "fx", "dr", "ip", "sr", "sp", "fr"
)

private val nameCode : Map<RegisterName, RegisterCode> =
    legalRegisterNames.withIndex().map {
        RegisterName.newRegisterName(it.value) to
            RegisterCode.newRegisterCode(ShamByte(it.index.toByte()))
    }.toMap()


private val codeName: Map<RegisterCode, RegisterName> =
    nameCode.map { it.value to it.key }.toMap()

fun registerName(code: RegisterCode): RegisterName? = codeName[code]

fun registerCode(name: RegisterName): RegisterCode? = nameCode[name]


data class BranchName private constructor (val value: String) {
    companion object {
        fun newBranchName(name: String): BranchName {
            val fixedName = name.toLowerCase()
            return when (fixedName) {
                in legalBranchNames -> BranchName(fixedName)
                else -> throw BranchNameException("$name is not a legal branch name.")
            }
        }
    }
}

fun newRegisters(): Registers {
    return codeName.keys.map { it to ShamWord(0)}.toMap()
}

data class BranchCode private constructor (val value: Byte) {
    companion object {
        fun newBranchCode(sb: ShamNybble): BranchCode {
            return BranchCode(sb.value)
        }

        fun newBranchCode(n: Number): BranchCode {
            return BranchCode(ShamNybble.newShamNybble(n).value)
        }
    }
}

private val legalBranchNames = listOf("low", "equal", "high", "key")

private val branchNameCode: Map<BranchName, BranchCode> =
    legalBranchNames.withIndex().
    map { BranchName.newBranchName(it.value) to BranchCode.newBranchCode(it.index)}.toMap()

private val branchCodeName: Map<BranchCode, BranchName> =
    branchNameCode.map { it.value to it.key }.toMap()

fun branchCode(name: BranchName): BranchCode? = branchNameCode[name]
fun branchName(code: BranchCode): BranchName? = branchCodeName[code]




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
