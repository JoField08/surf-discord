package dev.slne.discord.discord.interaction.context

import net.dv8tion.jda.api.interactions.commands.Command

enum class DiscordContextMenuCommandType(val jdaMap: Command.Type) {
    USER(Command.Type.USER),
    MESSAGE(Command.Type.MESSAGE)
}