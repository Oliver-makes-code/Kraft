package olivermakesco.de.kraft.nbt

class NbtEnd : NbtTag<Unit> {
    override val id: Byte = 0
    override val data = Unit

    override fun serialize(buffer: NbtBuffer) {
        buffer += id
    }
}
