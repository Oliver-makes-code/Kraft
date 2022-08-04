package olivermakesco.de.kraft.nbt

data class NbtString(override var data: String) : NbtTag<String> {
    override val id: Byte = 8

    override fun serialize(buffer: NbtBuffer) {
    }
}
