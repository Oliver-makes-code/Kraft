package olivermakesco.de.kraft.network.packet

import olivermakesco.de.kraft.network.packet.serverbound.ServerBoundPacket
import olivermakesco.de.kraft.network.types.VarInt
import olivermakesco.de.kraft.registry.Identifier
import java.nio.ByteBuffer
import java.util.LinkedList
import java.util.UUID

class PacketBuffer(): LinkedList<Byte>() {
    constructor(init: ByteArray) : this() {
        this += init.toList()
    }

    operator fun plusAssign(bool: Boolean) {
        this += if (bool) 0x01 else 0x00
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
        val intBuffer = ByteBuffer.allocate(Int.SIZE_BYTES)
        intBuffer.putInt(int)
        this += intBuffer.array()
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

    operator fun plusAssign(packet: ServerBoundPacket) {
        packet.write(this)
    }

    fun pushVarInt(int: Int) {
        pushVarInt(VarInt((int)))
    }

    fun pushVarInt(varInt: VarInt) {
        for (byte in varInt.toByteArray().reversed()) {
            this.push(byte)
        }
    }

    fun readBoolean(): Boolean {
        return this.pop().toInt() == 0x01
    }

    fun readByte(): Byte {
        return this.pop()
    }

    fun readInt(): Int {
        val intBuffer = ByteBuffer.allocate(Int.SIZE_BYTES)

        for (i in 0 until Int.SIZE_BYTES) {
            intBuffer.put(this.pop())
        }

        intBuffer.flip()
        return intBuffer.int
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
        val length = readVarInt().value
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
        val tempBuffer = ByteArray(length)

        for (i in 0 until length) {
            tempBuffer[i] = this.pop()
        }

        return tempBuffer
    }
}
