package olivermakesco.de.kraft.network.packet

import olivermakesco.de.kraft.network.NetworkManager
import olivermakesco.de.kraft.network.NetworkState
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket
import olivermakesco.de.kraft.network.packet.clientbound.login.*
import olivermakesco.de.kraft.registry.IntegerRegistry

class ClientBoundPacketRegistry: IntegerRegistry<PacketBuffer.(it: NetworkManager) -> ClientBoundPacket>() {
    companion object {
        val INSTANCE = ClientBoundPacketRegistry()

        init {
            INSTANCE[0] = {
                when (it.networkState) {
                    NetworkState.HANDSHAKING -> {
                        error("Received id 0 in handshaking stage")
                    }
                    NetworkState.STATUS -> {
                        TODO()
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
                when (it.networkState) {
                    NetworkState.HANDSHAKING -> {
                        error("Received id 1 in handshaking stage")
                    }
                    NetworkState.STATUS -> {
                        TODO()
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
                when (it.networkState) {
                    NetworkState.HANDSHAKING -> {
                        error("Received id 1 in handshaking stage")
                    }
                    NetworkState.STATUS -> {
                        TODO()
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
                when (it.networkState) {
                    NetworkState.HANDSHAKING -> {
                        error("Received id 1 in handshaking stage")
                    }
                    NetworkState.STATUS -> {
                        TODO()
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
                when (it.networkState) {
                    NetworkState.HANDSHAKING -> {
                        error("Received id 1 in handshaking stage")
                    }
                    NetworkState.STATUS -> {
                        TODO()
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
