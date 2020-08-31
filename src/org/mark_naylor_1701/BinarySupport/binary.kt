// author: Mark W. Naylor
// file:  binary.kt
// date:  2020-Aug-23

/////////////////////////////////////////////////////////////////////////////////
// Kotlin doen't support bit operations on Bytes or Shorts. This
// module bridges the gap.
/////////////////////////////////////////////////////////////////////////////////

package org.mark_naylor_1701.BinarySupport

// Private constructor w/ companion object factory. Ensures the value
// is in the range 0x0 to 0xF.
data class Nybble private constructor(val value: Byte) {
    companion object {
        fun ctor(value: Byte) = Nybble((value and loNybbleMask.toByte()))
    }
}

typealias NybblePair = Pair<Nybble, Nybble>
typealias BytePair = Pair<Byte, Byte>
typealias ShortPair = Pair<Short, Short>

private val hiNybbleMask = 0xF0.toByte()
private val loNybbleMask = 0x0F.toByte()
private val hiByteMask = 0xFF00.toShort()
private val loByteMask = 0x00FF.toShort()

//TODO:
// * Add halving and constructor functions for the Int and Long data types.
// * Move the module into a separate project.
// ** Symlink to the now location.
//          - or -
// ** Create and include as Maven artifact.

// Byte functions
infix fun Byte.and(that: Byte): Byte {
    return (toInt() and that.toInt()).toByte()
}

infix fun Byte.or(that: Byte): Byte {
    return (toInt() or that.toInt()).toByte()
}

infix fun Byte.shl(bitCount: Int): Byte {
    return (toInt() shl bitCount).toByte()
}

infix fun Byte.shr(bitCount: Int): Byte {
    return (toInt() shr bitCount).toByte()
}

fun halves(b: Byte): NybblePair {
    val hi = (b and hiNybbleMask) shr 4
    val lo = b and loNybbleMask
    return Nybble.factory(hi) to Nybble.factory(lo)
}

fun byte(hi: Nybble, lo: Nybble): Byte {
    return ((hi.value shl 4) or lo.value).toByte()
}

// Short functions.

infix fun Short.and(that: Short): Short {
    return (toInt() and that.toInt()).toShort()
}

infix fun Short.or(that: Short): Short {
    return (toInt() or that.toInt()).toShort()
}

infix fun Short.shl(bitCount: Int): Short {
    return (toInt() shl bitCount).toShort()
}

infix fun Short.shr(bitCount: Int): Short {
    return (toInt() shr bitCount).toShort()
}

fun short(hi: Byte, lo: Byte): Short {
    return ((hi shl 8) or lo).toShort()
}

fun halves(s: Short): BytePair {
    val hi = ((s and hiByteMask) shr 8).toByte()
    val lo = (s and loByteMask).toByte()
    return hi to lo
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
