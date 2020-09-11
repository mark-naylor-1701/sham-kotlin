// -*- fill-column: 80 -*-
// author: Mark W. Naylor
// file: alu.kt
// date: 2020-Sep-08

//                     Arithmetic Logic Unit — ALU

///////////////////////////////////////////////////////////////////////////////
//            Functions that implement the SHAM instruction set.             //
///////////////////////////////////////////////////////////////////////////////



package org.mark_naylor_1701.sham.cpu

// For now, just stubs that are Unit functions. ////////////////////////////////

// One byte commands.

fun nop() {
    // Does nothing. A somtimes useful operation, although at first glance it
    // seems useless to do nothing.
    println("nop")
}

fun random() {
    // Places a pseudo-random non-negative integer between 0 and (2^15 -1),
    // inclusive, into register AX.
    println("random")
}

// Function differs from opcode, because "return" is a Kotlin reserved word.
fun sham_return() {
    // Restore the IP, the DR, and the FR registers — casuing the next command
    // after a previous CALL or INTERRUPT to be executed.
    println("return")
}

fun terminate() {
    // Exits the program and turns off SHAM. Will display a message \"program
    // exited at relative location…\"
    println("terminate")
}

fun trace_on() {
    // Starts the trace operation (a hardware funtion in SHAM) that will display
    // the command next fetched and will also display the contents of all
    // registers before the fetched commond is executed.
    println("trace_on")
}

fun trace_off() {
    // Terminates tracing.
    println("trace_off")
}

fun enable() {
    // Permits the clock to increment and to allow testing of its value against
    // the word in location zero; when equal an inerrupt will occur to location
    // 12.
    println("enable")
}

fun disable() {
    // Prevents any further clock modification or testing; no interrupt will
    // occur until a future ENABLE is executed. The internal clock is reset to
    // zero.
    println("disable")
}

// Two byte commands
// (using only reg1 or reg1,value)

fun negate() {
    // Will reverse the sign of the contents of register 1. It is the same as
    // multiplying by minus one.
    println("negate")
}

fun increment() {
    // Will add value to the contents of register 1.
    println("increment")
}

fun decrement() {
    // Will subtract value from the contens of register 1.
    println("decrement")
}

fun push() {
    // Will push the contents of register 1 onto the current stack. The contest
    // of the SP register is decremented twice and the contets of registe1 is
    // stored at location (SR) + (SP), The contets of register are unchanged.
    println("push")
}

fun pop() {
    // Will pop the current stack into register 1. The contents of word location
    // (SR) + (SP) is placed in register 1 and hte contents of SP is incremeted
    // twice.
    println("pop")
}

// (Using both reg1 and reg2)

fun move() {
    // Will copy the contets of register into register. The value in both
    // registers are now the same. There is no memory MOVE.
    println("move")
}

fun add() {
    // Will algebraically add the contents of register 2 to the contents of
    // register 1.
    println("add")
}

fun subtract() {
    // Will algebraically subtract the contents of register 2 from the contents
    // of register 1.
    println("subtract")
}

fun multiply() {
    // Will multiply the contents of register 2 times the contents of register
    // 1, placing the result in register 1.
    println("multiply")
}

fun divide() {
    // Will divide the contents of register 1 by the cotents of register 2,
    // placing the result in register 1. No reminder is provided.
    println("divide")
}

fun compare() {
    // Will compare the contens of register 1 against those of register 2.
    // If (reg1) < (reg2), then FR register is set to -1.
    // If (reg1) = (reg2), then FR register is set to 0.
    // If (reg1) > (reg2), then FR register is set to 1.
    println("compare")
}

// Four byte
// (These, and only these, commands may be designated as indirect.)

fun sham_in(direct: Boolean = true) {
    // This command will use the value of the r1 part to determine it action:

    // If r1 part = 1 then a keybourd value is read into memory. The next
    // command is not executed until the user has pressed some key on the
    // console. The ASCII valsue of that key will be stored as a byte at the
    // memory location of the data address.

    // If r1 part = 2, then a sector of 256 bytes is inputted from SHAM.DSK (the
    // secondary memory of SHAM) into the memory location of data address. The
    // sector number (using zero as the first sector) is specified by the
    // contents of register 2. The data address cannot be indexed for thes
    // operation because of this special usage of r2. The word at loctaino 2 in
    // RAM is used as a semaphore while reading takes place: it is set to one
    // until the reading of the sector is finished, then it is cleared ot zero.

    // If r1 part = 3 then a single character is read into memory from the
    // permanent file SHAM.TXT into the memory location of data address. The FR
    // (flag register) is set to -1 if end of file contition was encountered on
    // SHAM.TXT, else FR is zero. The first occurrence starts at the beginning
    // of SHAM.TXT; subsequent inputs are the characters that follow in
    // sequence.

    println("in")
}

fun sham_out(direct: Boolean = true) {
    // Ths command will use the value of the r2 part to determine its action:

    // If the r1 part = 0 then a single caracter is outputtedfrom the memory
    // location of data address to the Display screen.

    // If r1 part = 1 then a single caracter is outputted from the memory
    // location of data address to the Input-Output screen.

    // If r1 part = 2 then a sector of 256 bytes is writter to SHAM.DSK (the
    // secondary memory of SHAM) from the memory locaiton of the data address.
    // The sector number (usin zero as the first sector) is specified by the
    // contets of register 2. The data address connot be indexed for this
    // operation.

    // If r1 part = 3 then a single cahacter is outputted from the memory
    // location of data adress to the next sequential position on the SHAM.TXT
    // file. The first occurrence erases any existing text and starts at the
    // beginning of SHAM.TXT; sebsequent outputs ar in physical sequece. (FR) is
    // negative if the write is unsuccessful.

    println("out")
}

fun branch(direct: Boolean = true) {
    // Will cause the next instruction to be the one specified at the data
    // address location if the conditions specified by the r1 part of the
    // command are met. Otherwise the next instruction in sequenc will be
    // executed. The r1 part is normally indicated by one of the reserved words
    // LOW, EQUAL, HIGH, or KEY.

    println("branch")
}

fun call(direct: Boolean = true) {
    // Will cause the contents of FR, DR, and IP to be pushed onto the current
    // stack (in that order) and control to be transferred to a new DR, IP
    // combination. The value of IP is the data address (plus possible register
    // 2 indexing) without anyi addition of DR. The value of the new DR is the
    // contents of reg 1, if any, else the same as before.

    println("call")
}

fun fetch_byte(direct: Boolean = true) {
    // Will replace the contents of register 1 yb the byte stored at the memory
    // given by the data address. The leftmost 8 bits will be zero.

    println("fetch_byte")
}

fun fetch_word(direct: Boolean = true) {
    // Wil replace the contents of register 1 by the word stored at the memroy
    // given by the data address.

    println("fetch_word")
}

fun store_byte(direct: Boolean = true) {
    // Will copy into the memory location given by the data address the
    // rightmost 8 bits of register 1.

    println("store_byte")
}

fun store_word(direct: Boolean = true) {
    // Will copy the full contets of register 1 into the word lacation in memory
    // specified by the data address.

    println("store_word")
}

fun stream(direct: Boolean = true) {
    // This command will use the value of the r1 part to determine its action:

    // If the r1 part = 0 then the command will execute an initial program load
    // (IPL). This is a boot opeation where the contens of SHAM.ROM are loade
    // into memory, starting at location zero. The contents of DR and IP are
    // both set to zero; the contents of SR are set to the memory location at
    // the end of hte loaded program and SP is set to 600. The contents of
    // memory are set to the current maximum memory location (normally 32767.)

    // If the r1 part = 1 then the command will find the size of the file stored
    // on the file SHAM.STM, setting AX to that value.

    // If the r1 part = 2 then the command will read the rest of SHAM.STM (using
    // the contents of AX to specify how many bytes there are) into the memory
    // location specified by the data address portion of the command.

    println("stream")
}

fun testandset(direct: Boolean = true) {
    // Will set the flag register (FR) to the word value located at the location
    // specified by the data address (this value normallly is a zero or a one)
    // and will set the value of that word to one.

    println("testandset")
}

fun interrupt(direct: Boolean = true) {
    // Will cause the contens of FR, DR, and IP to be pushed onto the current
    // stack and the new IP and DR to be fetched from the word locatons
    // specified by the data address (for the IP) and at that of location + 2
    // (for the DR). FR is ste to zero. The data ddress must be absolute.

    println("interrupt")
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
