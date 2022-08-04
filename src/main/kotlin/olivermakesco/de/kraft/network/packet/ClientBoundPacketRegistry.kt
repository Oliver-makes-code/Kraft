package olivermakesco.de.kraft.network.packet

import olivermakesco.de.kraft.network.NetworkState
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.clientbound.login.*
import olivermakesco.de.kraft.network.packet.clientbound.status.PongPacket
import olivermakesco.de.kraft.network.packet.clientbound.status.StatusResponsePacket
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
                        StatusResponsePacket(this)
                    }
                    NetworkState.LOGIN -> {
                        LoginDisconnectPacket(this)
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
                        PongPacket(this)
                    }
                    NetworkState.LOGIN -> {
                        EncryptionRequestPacket(this)
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
                        LoginSuccessPacket(this)
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
                        SetCompressionPacket(this)
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
                        LoginPluginRequestPacket(this)
                    }
                    NetworkState.PLAY -> {
                        TODO()
                    }
                }
            }
        }
    }
}
