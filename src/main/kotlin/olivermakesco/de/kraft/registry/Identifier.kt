package olivermakesco.de.kraft.registry

data class Identifier(val namespace: String, val path: String) {
    companion object {
        fun fromString(string: String): Identifier {
            val split = string.split(":")
            return when (split.size) {
                1 -> Identifier("minecraft", split[0])
                2 -> Identifier(split[0], split[1])
                else -> throw IllegalArgumentException("Invalid identifier string: $string")
            }
        }
    }
}

fun vanillaIdentifier(id: String) = Identifier("minecraft", id)
fun kraftIdentifier(id: String) = Identifier("kraft", id)
