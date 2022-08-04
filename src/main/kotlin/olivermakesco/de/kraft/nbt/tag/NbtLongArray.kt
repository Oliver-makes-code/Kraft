package olivermakesco.de.kraft.nbt.tag

import olivermakesco.de.kraft.nbt.NbtBuffer
import olivermakesco.de.kraft.nbt.NbtTag
import java.util.*

class NbtLongArray() : NbtTag<LinkedList<Long>> {
    override val id: Byte = 12
    override var data: LinkedList<Long> = LinkedList()

    constructor(init: LongArray) : this() {
        data += init.toList()
    }

    override fun serialize(buffer: NbtBuffer) {
        buffer += id
        buffer += data.size
        for (number in data) {
            buffer += number
        }
    }
}