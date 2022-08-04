package olivermakesco.de.kraft.network

enum class NetworkState(val id: Int) {
    HANDSHAKING(-1),
    PLAY(0),
    STATUS(1),
    LOGIN(2);
}
