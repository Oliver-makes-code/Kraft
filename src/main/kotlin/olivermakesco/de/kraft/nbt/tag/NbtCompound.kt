package olivermakesco.de.kraft.nbt.tag

import olivermakesco.de.kraft.nbt.NbtBuffer
import olivermakesco.de.kraft.nbt.NbtTag

class NbtCompound : NbtTag<HashMap<NbtString, NbtTag<*>>> {
    override val id: Byte = 10
    override var data = HashMap<NbtString, NbtTag<*>>()

    override fun serialize(buffer: NbtBuffer) {
        buffer += id
        buffer += data.size

        for (tag in data) {
            buffer += tag.key
            buffer += tag.value
        }

        buffer += NbtEnd()
    }
}
