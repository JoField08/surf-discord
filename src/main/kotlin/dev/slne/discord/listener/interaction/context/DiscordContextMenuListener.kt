package dev.slne.discord.listener.interaction.context

import dev.minn.jda.ktx.events.listener
import dev.slne.discord.discord.interaction.command.DiscordCommandProcessor
import jakarta.annotation.PostConstruct
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent
import org.springframework.stereotype.Component

@Component
class DiscordContextMenuListener(
    private val jda: JDA,
    private val processor: DiscordCommandProcessor
) {

    @PostConstruct
    fun registerListener() {
        jda.listener<UserContextInteractionEvent> { event ->
            processor.getContextMenuCommand(event.name)?.second?.execute(event)
                ?: error("Context Menu Command ${event.name} not found")
        }

        jda.listener<MessageContextInteractionEvent> { event ->
            processor.getContextMenuCommand(event.name)?.second?.execute(event)
                ?: error("Context Menu Command ${event.name} not found")
        }
    }
}
