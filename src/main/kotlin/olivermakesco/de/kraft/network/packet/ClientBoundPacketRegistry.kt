package olivermakesco.de.kraft.network.packet

import olivermakesco.de.kraft.network.NetworkState
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.clientbound.login.*
import olivermakesco.de.kraft.network.packet.clientbound.status.ClientBoundPongPacket
import olivermakesco.de.kraft.network.packet.clientbound.status.ClientBoundStatusResponsePacket
import olivermakesco.de.kraft.registry.IntegerRegistry

class ClientBoundPacketRegistry: IntegerRegistry<PacketBuffer.(it: NetworkState) -> ClientBoundPacket>() {
    companion object {
        val INSTANCE = ClientBoundPacketRegistry()

        init {
            INSTANCE[0] = {
                when (it) {
                    NetworkState.HANDSHAKING -> {
                        error("Received id 0 in handshaking stage")
                    }
                    NetworkState.STATUS -> {
                        ClientBoundStatusResponsePacket(this)
                    }
                    NetworkState.LOGIN -> {
                        ClientBoundLoginDisconnectPacket(this)
                    }
                    NetworkState.PLAY -> {
                        TODO()
                    }
                }
            }
            INSTANCE[1] = {
                when (it) {
                    NetworkState.HANDSHAKING -> {
                        error("Received id 1 in handshaking stage")
                    }
                    NetworkState.STATUS -> {
                        ClientBoundPongPacket(this)
                    }
                    NetworkState.LOGIN -> {
                        ClientBoundEncryptionRequestPacket(this)
                    }
                    NetworkState.PLAY -> {
                        TODO()
                    }
                }
            }
            INSTANCE[2] = {
                when (it) {
                    NetworkState.HANDSHAKING -> {
                        error("Received id 2 in handshaking stage")
                    }
                    NetworkState.STATUS -> {
                        error("Received id 2 in status stage")
                    }
                    NetworkState.LOGIN -> {
                        ClientBoundLoginSuccessPacket(this)
                    }
                    NetworkState.PLAY -> {
                        TODO()
                    }
                }
            }
            INSTANCE[3] = {
                when (it) {
                    NetworkState.HANDSHAKING -> {
                        error("Received id 3 in handshaking stage")
                    }
                    NetworkState.STATUS -> {
                        error("Received id 3 in handshaking stage")
                    }
                    NetworkState.LOGIN -> {
                        ClientBoundSetCompressionPacket(this)
                    }
                    NetworkState.PLAY -> {
                        TODO()
                    }
                }
            }
            INSTANCE[4] = {
                when (it) {
                    NetworkState.HANDSHAKING -> {
                        error("Received id 4 in handshaking stage")
                    }
                    NetworkState.STATUS -> {
                        error("Received id 4 in handshaking stage")
                    }
                    NetworkState.LOGIN -> {
                        ClientBoundLoginPluginRequestPacket(this)
                    }
                    NetworkState.PLAY -> {
                        TODO()
                    }
                }
            }
        }
    }
}
