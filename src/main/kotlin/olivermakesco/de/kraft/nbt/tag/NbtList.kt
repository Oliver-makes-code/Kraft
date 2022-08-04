package olivermakesco.de.kraft.nbt.tag

import olivermakesco.de.kraft.nbt.NbtBuffer
import olivermakesco.de.kraft.nbt.NbtTag
import java.util.*

class NbtList<T : NbtTag<T>>(val parentId: Byte) : NbtTag<LinkedList<T>> {
    override val id: Byte = 9
    override var data: LinkedList<T> = LinkedList()

    constructor(init: List<T>, parentId: Byte): this(parentId) {
        data += init
    }

    override fun serialize(buffer: NbtBuffer) {
        buffer += id
        buffer += parentId
        buffer += data.size
        for (tag in data) {
            buffer += tag
        }
    }

}