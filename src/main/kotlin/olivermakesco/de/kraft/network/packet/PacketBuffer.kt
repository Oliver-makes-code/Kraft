package olivermakesco.de.kraft.network.packet

import olivermakesco.de.kraft.network.types.VarInt
import olivermakesco.de.kraft.registry.Identifier
import java.nio.ByteBuffer
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
        val shortBuffer = ByteBuffer.allocate(Short.SIZE_BYTES)
        shortBuffer.putShort(short)
        this += shortBuffer.array()
    }

    operator fun plusAssign(int: Int) {
        this += VarInt(int)
    }

    operator fun plusAssign(long: Long) {
        val longBuffer = ByteBuffer.allocate(Long.SIZE_BYTES)
        longBuffer.putLong(long)
        this += longBuffer.array()
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

    fun readLong(): Long {
        val longBuffer = ByteBuffer.allocate(Long.SIZE_BYTES)

        for (i in 0 until Long.SIZE_BYTES) {
            longBuffer.put(this.pop())
        }

        longBuffer.flip()
        return longBuffer.long
    }

    fun readString(): String {
        val length = readInt()
        val stringBuffer = ByteArray(length)

        for (i in 0 until length) {
            stringBuffer[i] = this.pop()
        }

        return String(stringBuffer)
    }

    fun readIdentifier(): Identifier {
        return Identifier.fromString(readString())
    }

    fun readVarInt(): VarInt {
        return VarInt.fromPacketBuffer(this)
    }

    fun readUuid(): UUID {
        return UUID(readLong(), readLong())
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
