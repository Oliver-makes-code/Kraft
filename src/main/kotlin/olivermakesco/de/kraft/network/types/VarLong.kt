package olivermakesco.de.kraft.network.types

import olivermakesco.de.kraft.network.packet.PacketBuffer

class VarLong(var value: Long) {
    fun toByteArray(): ByteArray {
        val bytes = ArrayList<Byte>()

        while (value and SEGMENT_BITS.inv() != 0L) {
            bytes.add(((value and SEGMENT_BITS) or CONTINUE_BIT).toByte())

            value = value shr 7
        }

        bytes.add(value.toByte())

        return bytes.toByteArray()
    }

    companion object {
        private const val SEGMENT_BITS = 0x7FL
        private const val CONTINUE_BIT = 0x80L

        fun fromPacketBuffer(packetBuffer: PacketBuffer): VarLong {
            var value = 0L
            var position = 0

            do {
                val byte = packetBuffer.pop()
                value = value or ((byte.toLong() and SEGMENT_BITS) shl position)

                position += 7

                if (position >= 64) throw RuntimeException("VarInt is too big")
            } while (packetBuffer.peek().toLong() and CONTINUE_BIT != 0L)

            return VarLong(value)
        }
    }
}
