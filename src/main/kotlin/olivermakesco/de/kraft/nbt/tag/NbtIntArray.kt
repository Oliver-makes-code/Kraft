package olivermakesco.de.kraft.nbt.tag

import olivermakesco.de.kraft.nbt.NbtBuffer
import olivermakesco.de.kraft.nbt.NbtTag
import java.util.*

class NbtIntArray() : NbtTag<LinkedList<Int>> {
    override val id: Byte = 11
    override var data: LinkedList<Int> = LinkedList<Int>()

    constructor(init: IntArray) : this() {
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