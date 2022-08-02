package olivermakesco.de.kraft.network.types

import olivermakesco.de.kraft.network.PacketBuffer

class VarInt(var value: Int) {
    fun toByteArray(): ByteArray {
        val bytes = ArrayList<Byte>()

        while (value and SEGMENT_BITS.inv() != 0) {
            bytes.add(((value and SEGMENT_BITS) or CONTINUE_BIT).toByte())

            value = value shr 7
        }

        bytes.add(value.toByte())

        return bytes.toByteArray()
    }

    companion object {
        private const val SEGMENT_BITS = 0x7F
        private const val CONTINUE_BIT = 0x80

        fun fromPacketBuffer(packetBuffer: PacketBuffer): VarInt {
            var value = 0
            var position = 0

             do {
                val byte = packetBuffer.pop()
                value = value or ((byte.toInt() and SEGMENT_BITS) shl position)

                position += 7

                if (position >= 32) throw RuntimeException("VarInt is too big")
            } while (packetBuffer.peek().toInt() and CONTINUE_BIT != 0)

            return VarInt(value)
        }
    }
}
