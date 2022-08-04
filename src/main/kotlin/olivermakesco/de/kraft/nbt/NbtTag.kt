package olivermakesco.de.kraft.nbt

interface NbtTag<T> {
    val id: Byte
    val data: T
    fun serialize(buffer: NbtBuffer)
}
