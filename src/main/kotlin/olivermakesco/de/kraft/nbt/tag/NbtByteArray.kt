package olivermakesco.de.kraft.nbt.tag

import olivermakesco.de.kraft.nbt.NbtBuffer
import olivermakesco.de.kraft.nbt.NbtTag
import java.util.*

class NbtByteArray() : NbtTag<LinkedList<Byte>> {
    override val id: Byte = 7
    override var data: LinkedList<Byte> = LinkedList<Byte>()

    constructor(init: ByteArray) : this() {
        data += init.toList()
    }

    override fun serialize(buffer: NbtBuffer) {
        buffer += id
        buffer += data.size
        buffer += data
    }
}