package olivermakesco.de.kraft.nbt

class NbtCompound : NbtTag<HashMap<NbtString, NbtTag<*>>> {
    override val id: Byte = 10
    override var data = HashMap<NbtString, NbtTag<*>>()

    override fun serialize(buffer: NbtBuffer) {
        buffer += id
        buffer += data.size

        for (tag in data) {
            buffer += tag.key
            buffer += tag.value
        }

        buffer += NbtEnd()
    }
}
