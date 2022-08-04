package olivermakesco.de.kraft.nbt.tag

import olivermakesco.de.kraft.nbt.NbtBuffer
import olivermakesco.de.kraft.nbt.NbtTag
import java.nio.ByteBuffer

class NbtLong(override var data: Long) : NbtTag<Long> {
    override val id: Byte = 4

    override fun serialize(buffer: NbtBuffer) {
        buffer += id
        buffer += data
    }
}