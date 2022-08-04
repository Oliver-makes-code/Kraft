package olivermakesco.de.kraft.nbt.tag

import olivermakesco.de.kraft.nbt.NbtBuffer
import olivermakesco.de.kraft.nbt.NbtTag
import java.nio.ByteBuffer

class NbtShort(override var data: Short) : NbtTag<Short> {
    override val id: Byte = 2

    override fun serialize(buffer: NbtBuffer) {
        buffer += id
        buffer += data
    }
}