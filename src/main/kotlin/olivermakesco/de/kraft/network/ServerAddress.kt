package olivermakesco.de.kraft.network

data class ServerAddress(val host: String, val port: Int) {
    override fun toString(): String {
        return "$host:$port"
    }

    companion object {
        fun fromString(string: String): ServerAddress {
            val split = string.split(":")
            return when (split.size) {
                1 -> ServerAddress(split[0], 25565)
                2 -> ServerAddress(split[0], split[1].toInt())
                else -> throw IllegalArgumentException("Invalid server address string: $string")
            }
        }
    }
}
