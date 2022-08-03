package olivermakesco.de.kraft.registry

data class Identifier(
    val namespace: String,
    val path: String
)

fun vanillaIdentifier(id: String) = Identifier("minecraft", id)
fun kraftIdentifier(id: String) = Identifier("kraft", id)
