package olivermakesco.de.kraft.nbt.tag

import olivermakesco.de.kraft.nbt.NbtBuffer
import olivermakesco.de.kraft.nbt.NbtTag

data class NbtString(override var data: String) : NbtTag<String> {
    override val id: Byte = 8

    override fun serialize(buffer: NbtBuffer) {
    }
}
