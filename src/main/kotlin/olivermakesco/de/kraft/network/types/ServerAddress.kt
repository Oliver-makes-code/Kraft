package olivermakesco.de.kraft.network.types

data class ServerAddress(val host: String, val port: Int) {
    constructor(address: String) : this({
        val split = address.split(":")
        split[0] to if (split.size > 1) split[1].toInt() else 25565
    }.invoke())

    constructor(address: Pair<String, Int>) : this(address.first, address.second)
}
