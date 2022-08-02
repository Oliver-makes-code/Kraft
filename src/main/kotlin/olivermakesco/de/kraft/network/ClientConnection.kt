package olivermakesco.de.kraft.network

import java.net.Socket

class ClientConnection(val addr: String, val port: Int) {
    val socket = Socket(addr, port)

    fun sendPacket(packet: Packet) {
        socket.getOutputStream().write(packet.toByteArray())
    }
}
