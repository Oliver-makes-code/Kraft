package olivermakesco.de.kraft.nbt.tag

import olivermakesco.de.kraft.nbt.NbtBuffer
import olivermakesco.de.kraft.nbt.NbtTag
import java.nio.ByteBuffer

class NbtDouble(override var data: Double) : NbtTag<Double> {
    override val id: Byte = 5

    override fun serialize(buffer: NbtBuffer) {
        buffer += id
        buffer += data
    }

    operator fun NbtBuffer.plusAssign(number: Double) {
        val buffer = ByteBuffer.allocate(Double.SIZE_BYTES)
        buffer.putDouble(number)
        this += buffer.array().toList()
    }
}