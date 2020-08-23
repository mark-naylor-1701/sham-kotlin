// author: Mark W. Naylor
// file:  bitops.kt
// date:  2020-Aug-23

/////////////////////////////////////////////////////////////////////////////////
// Kotlin doen't support bit operations on Bytes. This module bridges the gap. //
/////////////////////////////////////////////////////////////////////////////////

package org.mark_naylor_1701.sham.bitops

data class Nybble(val value: Byte)
vabit
private val hiNybbleMask = 0xF0
private val loNybbleMask = 0x0F


infix fun Byte.and(that: Byte): Byte {
     return (this.toInt() and that.toInt()).toByte()
}

infix fun Byte.shr(bitCount: Int): Byte {
     return (this.toInt() shr bitCount).toByte()
}

infix fun Byte.shl(bitCount: Int): Byte {
     return (this.toInt() shl bitCount).toByte()
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
