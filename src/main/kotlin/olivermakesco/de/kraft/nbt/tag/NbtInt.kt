package olivermakesco.de.kraft.nbt.tag

import olivermakesco.de.kraft.nbt.NbtBuffer
import olivermakesco.de.kraft.nbt.NbtTag

class NbtInt(override var data: Int) : NbtTag<Int> {
    override val id: Byte = 3

    override fun serialize(buffer: NbtBuffer) {
        buffer += id
        buffer += data
    }
}