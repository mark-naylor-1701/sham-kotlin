// author: Mark W. Naylor
// file:  Types.kt
// date:  2020-Aug-27

///////////////////////////////////////////////////////////////////////////////
//              Type safe replacements for primitive/base types.             //
///////////////////////////////////////////////////////////////////////////////

package org.mark_naylor_1701.sham.Types

import org.mark_naylor_1701.BinarySupport.*

data class ShamNybble private constructor (val value: Byte) {
    companion object {
        fun newShamNybble(value: Byte): ShamNybble {
            val n = Nybble.newNybble(value)

            return ShamNybble(n.value)
        }

        fun newShamNybble(value: Number): ShamNybble {
            val n = Nybble.newNybble(value.toByte())

            return ShamNybble(n.value)
        }
    }
}

data class ShamByte(val value: Byte)

data class ShamWord(val value: Short)

data class OpCode(val value: Byte)

data class Mnemonic(val value: String)

typealias ShamNybblePair = Pair<ShamNybble, ShamNybble>
typealias ShamBytePair = Pair<ShamByte, ShamByte>

infix fun ShamByte.and(that: ShamByte): ShamByte {
    return ShamByte((value and that.value).toByte())
}

infix fun ShamByte.or(that: ShamByte): ShamByte {
    return ShamByte((value or that.value).toByte())
}

infix fun ShamByte.shl(bitCount: Int): ShamByte {
    return ShamByte((value shl bitCount).toByte())
}

infix fun ShamByte.shr(bitCount: Int): ShamByte {
    return ShamByte((value shr bitCount).toByte())
}

fun halves(sb: ShamByte): ShamNybblePair {
    val (hi, lo) = halves(sb.value)

    return ShamNybble.newShamNybble(hi.value) to ShamNybble.newShamNybble(lo.value)
}

fun halves(sw: ShamWord): ShamBytePair {
    val (hi, lo) = halves(sw.value)

    return ShamByte(hi) to ShamByte(lo)
}

fun shamByte(hi: ShamNybble, lo: ShamNybble): ShamByte {
    val hiNybble = hi.value shl 4
    val loNybble = lo.value
    val byte = hiNybble or loNybble

    return ShamByte(byte)
}


fun shamWord(hi: ShamByte, lo: ShamByte): ShamWord {
    val hiWord = hi.value.toShort() shl 8
    val loWord = lo.value.toShort()
    val word = hiWord or loWord

    return ShamWord(word)
}


operator fun ShamWord.plus(that: ShamWord): ShamWord {
    val word = value + that.value

    return ShamWord(word.toShort())
}


operator fun ShamWord.minus(that: ShamWord): ShamWord {
    val word = value - that.value

    return ShamWord(word.toShort())
}


operator fun ShamWord.times(that: ShamWord): ShamWord {
    val word = value * that.value

    return ShamWord(word.toShort())
}


operator fun ShamWord.div(that: ShamWord): ShamWord {
    val word = value / that.value

    return ShamWord(word.toShort())
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
