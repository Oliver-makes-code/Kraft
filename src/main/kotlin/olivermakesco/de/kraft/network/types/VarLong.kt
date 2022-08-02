package olivermakesco.de.kraft.network.types

class VarLong(var value: Long) {
    fun toArray(): ByteArray {
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

        fun fromArray(byteArray: ByteArray): VarLong {
            var value = 0L
            var position = 0

            for (b in byteArray) {
                value = value or (b.toLong() and SEGMENT_BITS) shl position

                if (b.toLong() and CONTINUE_BIT == 0L) break

                position += 7

                if (position >= 32) throw RuntimeException("VarInt is too big")
            }

            return VarLong(value)
        }
    }
}
