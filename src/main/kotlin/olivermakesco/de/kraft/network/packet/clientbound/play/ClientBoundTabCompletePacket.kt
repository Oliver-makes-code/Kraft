package olivermakesco.de.kraft.network.packet.clientbound.play

import olivermakesco.de.kraft.client.KraftClient
import olivermakesco.de.kraft.network.packet.PacketBuffer
import olivermakesco.de.kraft.network.packet.clientbound.ClientBoundPacket

class ClientBoundTabCompletePacket(buffer: PacketBuffer) : ClientBoundPacket {
    val id = buffer.readVarInt().value
    val start = buffer.readVarInt().value
    val length = buffer.readVarInt().value
    private val completionCount = buffer.readVarInt().value
    val completions = ArrayList<Completion>(completionCount)

    init {
        for (i in 0 until completionCount) {
            val text = buffer.readString()
            val tooltip = if (buffer.readBoolean()) buffer.readString() else null
            completions.add(Completion(text, tooltip))
        }
    }

    override fun handle(client: KraftClient) {
        TODO("Not yet implemented")
    }

    data class Completion(val text: String, val tooltip: String?) // TODO: Brigadier?
}
