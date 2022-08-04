package olivermakesco.de.kraft.network.types

import olivermakesco.de.kraft.network.packet.PacketBuffer

data class VarLong(var value: Long) {
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
        private const val SEGMENT_BITS = 0b01111111L
        private const val CONTINUE_BIT = 0b10000000L

        fun fromPacketBuffer(packetBuffer: PacketBuffer): VarLong {
            var value = 0L
            var position = 0

            while (true) {
                val byte = packetBuffer.pop()
                value = value or ((byte.toLong() and SEGMENT_BITS) shl position)

                if (byte.toLong() and CONTINUE_BIT == 0L) break

                position += 7

                if (position >= 64) throw RuntimeException("VarLong is too big")
            }

            return VarLong(value)
        }
    }
}
