package olivermakesco.de.kraft.network

import olivermakesco.de.kraft.network.types.VarInt

data class Packet(val id: Int, private val data: Data) {
    val length = Integer.SIZE + data.size() * 8

    class Data {
        val data = ArrayList<Byte>()

        operator fun plusAssign(string: String) {
            data.addAll(string.toByteArray().toList())
        }

        operator fun plusAssign(bool: Boolean) {
            data.add(if (bool) 0x01 else 0x00)
        }

        operator fun plusAssign(int: Int) {
            data.addAll(VarInt(int).toByteArray().toList())
        }

        operator fun plusAssign(varInt: VarInt) {
            data.addAll(varInt.toByteArray().toList())
        }

        fun size(): Int {
            return data.size
        }
    }

    fun toByteArray(): ByteArray {
        val arrayList = ArrayList<Byte>()
        arrayList.addAll(VarInt(length).toByteArray().toList())
        arrayList.addAll(VarInt(id).toByteArray().toList())
        arrayList.addAll(data.data)

        return arrayList.toByteArray()
    }
}
