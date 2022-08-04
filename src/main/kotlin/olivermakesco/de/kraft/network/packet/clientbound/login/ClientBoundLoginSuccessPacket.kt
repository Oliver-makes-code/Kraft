package olivermakesco.de.kraft.network.packet.clientbound.login

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.NetworkState
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.util.logger

class ClientBoundLoginSuccessPacket(buffer: PacketBuffer) : ClientBoundPacket {
    val uuid = buffer.readUuid()
    val name = buffer.readString()

    override fun handle(client: KraftClient) {
        logger.info(name)
        client.networkManager.networkState = NetworkState.PLAY
    }
}
