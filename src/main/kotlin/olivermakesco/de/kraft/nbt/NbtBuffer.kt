package olivermakesco.de.kraft.nbt

import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.util.*
import java.util.zip.GZIPOutputStream

class NbtBuffer(): LinkedList<Byte>() {
    constructor(init: ByteArray) : this() {
        this += init.toList()
    }

    operator fun plusAssign(int: Int) {
        val intBuffer = ByteBuffer.allocate(Int.SIZE_BYTES)
        intBuffer.putInt(int)
        this += intBuffer.array().toList()
    }

    operator fun <T : NbtTag<*>> plusAssign(tag: T) {
        tag.serialize(this)
    }

    fun gzip(): ByteArray {
        val data = this.toByteArray()
        val byteStream = ByteArrayOutputStream(data.size)
        val gzipStream = GZIPOutputStream(byteStream)
        gzipStream.write(data)
        gzipStream.close()
        byteStream.close()
        return byteStream.toByteArray()
    }

    companion object {
        fun fromGzippedArray(array: ByteArray): NbtBuffer {
            TODO()
        }
    }
}