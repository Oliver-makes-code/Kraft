package olivermakesco.de.kraft.network.types

class VarInt(var value: Int) {
    fun toByteArray(): ByteArray {
        val array = ArrayList<Byte>()

        while (value and SEGMENT_BITS.inv() != 0) {
            array.add(((value and SEGMENT_BITS) or CONTINUE_BIT).toByte())

            value = value shr 7
        }

        array.add(value.toByte())

        return array.toByteArray()
    }

    companion object {
        private const val SEGMENT_BITS = 0x7F
        private const val CONTINUE_BIT = 0x80

        fun fromByteArray(byteArray: ByteArray): VarInt {
            var value = 0
            var position = 0

            for (b in byteArray) {
                value = value or ((b.toInt() and SEGMENT_BITS) shl position)

                if (b.toInt() and CONTINUE_BIT == 0) break

                position += 7

                if (position >= 32) throw RuntimeException("VarInt is too big")
            }

            return VarInt(value)
        }
    }
}
