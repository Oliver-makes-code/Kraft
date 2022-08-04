package olivermakesco.de.kraft.nbt.tag

import olivermakesco.de.kraft.nbt.NbtBuffer
import olivermakesco.de.kraft.nbt.NbtTag

class NbtEnd : NbtTag<Unit> {
    override val id: Byte = 0
    override var data = Unit

    override fun serialize(buffer: NbtBuffer) {
        buffer += id
    }
}
