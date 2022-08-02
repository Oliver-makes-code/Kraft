package olivermakesco.de.kraft.network.packet

import olivermakesco.de.kraft.network.types.VarInt
import java.util.LinkedList
import java.util.UUID

class PacketBuffer(): LinkedList<Byte>() {
    constructor(init: ByteArray) : this() {
        this.addAll(init.toList())
    }

    operator fun plusAssign(bool: Boolean) {
        this.add(if (bool) 0x01 else 0x00)
    }

    operator fun plusAssign(byte: Byte) {
        this.add(byte)
    }

    operator fun plusAssign(short: Short) {
        this += (short.toInt() shr 8).toByte()
        this += short.toByte()
    }

    operator fun plusAssign(int: Int) {
        this += VarInt(int)
    }

    operator fun plusAssign(string: String) {
        this += VarInt(string.length)
        this += string.toByteArray()
    }

    operator fun plusAssign(varInt: VarInt) {
        this += varInt.toByteArray()
    }

    operator fun plusAssign(byteArray: ByteArray) {
        this.addAll(byteArray.toList())
    }

    operator fun plusAssign(packetBuffer: PacketBuffer) {
        this.addAll(packetBuffer)
    }

    fun readBool(): Boolean {
        return this.pop().toInt() == 0x01
    }

    fun readInt(): Int {
        return readVarInt().value
    }

    fun readString(): String {
        val length = readInt()
        val stringBuffer = ByteArray(length)

        for (i in 0 until length) {
            stringBuffer[i] = this.pop()
        }

        return String(stringBuffer)
    }

    fun readVarInt(): VarInt {
        return VarInt.fromPacketBuffer(this)
    }

    fun readUuid(): UUID {
        val uuidBuffer = ByteArray(16)

        for (i in 0..15) {
            uuidBuffer[i] = this.pop()
        }

        var msb = 0L
        var lsb = 0L

        for (i in 0..7) {
            msb = msb shl 8 or (uuidBuffer[i].toInt() and 255).toLong()
        }

        for (i in 9..15) {
            lsb = lsb shl 8 or (uuidBuffer[i].toInt() and 255).toLong()
        }


        return UUID(msb, lsb)
    }

    fun readByteArray(): ByteArray {
        return readByteArray(this.size)
    }

    fun readByteArray(length: Int): ByteArray {
        val tempBuffer = ByteArray(16)

        for (i in 0 until length) {
            tempBuffer[i] = this.pop()
        }

        return tempBuffer
    }
}
