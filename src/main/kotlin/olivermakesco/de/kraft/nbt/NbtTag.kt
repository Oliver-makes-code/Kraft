package olivermakesco.de.kraft.nbt

interface NbtTag<T> {
    val id: Byte
    var data: T
    fun serialize(buffer: NbtBuffer)
}
