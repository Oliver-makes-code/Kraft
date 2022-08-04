package olivermakesco.de.kraft.nbt.tag

import olivermakesco.de.kraft.nbt.NbtBuffer
import olivermakesco.de.kraft.nbt.NbtTag
import java.nio.ByteBuffer

class NbtFloat(override var data: Float) : NbtTag<Float> {
    override val id: Byte = 5

    override fun serialize(buffer: NbtBuffer) {
        buffer += id
        buffer += data
    }

    operator fun NbtBuffer.plusAssign(number: Float) {
        val buffer = ByteBuffer.allocate(Float.SIZE_BYTES)
        buffer.putFloat(number)
        this += buffer.array().toList()
    }
}