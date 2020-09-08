// author: Mark W. Naylor
// file:  memory.kt
// date:  2020-Sep-08

package org.mark_naylor_1701.sham.cpu

import org.mark_naylor_1701.sham.Types.*


// Final memory size, reduced during development/testing.
// private val memorySize = 32 * 1024

// Temporary memory size, 64 bytes should be sufficient
private val memorySize = 64

typealias Memory = List<ShamByte>

operator fun Memory.get(index: ShamWord): ShamByte = get(index.value.toInt())

fun Memory.take(index: ShamWord): Memory = take(index.value.toInt())

fun Memory.drop(index: ShamWord): Memory = drop(index.value.toInt())

fun newMemory(): Memory {
    val zero = ShamByte(0)

    return (1..memorySize).map { zero }
}

fun readByte(mem: Memory, address: ShamWord): ShamByte {
    //val index = address.value.toInt()
    return mem[address]
}

fun readWord(mem: Memory, address: ShamWord): ShamWord {
    val hiByte = readByte(mem, address)
    val loByte = readByte(mem, address + ShamWord(1))
    return shamWord(hiByte, loByte)
}

fun write(mem: Memory, address: ShamWord, byte: ShamByte): Memory {
    val pre = mem.take(address)
    val post = mem.drop(address.inc())

    return pre + byte + post
}

fun write(mem: Memory, address: ShamWord, word: ShamWord): Memory {
    val pre = mem.take(address)
    val post = mem.drop(address.inc().inc())
    val (hi, lo) = halves(word)

    return pre + hi + lo + post
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
