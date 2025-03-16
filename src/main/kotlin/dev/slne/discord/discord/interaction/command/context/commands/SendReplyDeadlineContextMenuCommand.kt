package dev.slne.discord.discord.interaction.command.context.commands

import dev.slne.discord.annotation.DiscordContextMenuCommandMeta
import dev.slne.discord.discord.interaction.command.commands.ticket.sendReplyDeadlineToChannel
import dev.slne.discord.discord.interaction.command.context.DiscordContextMenuCommand
import dev.slne.discord.discord.interaction.command.context.DiscordContextMenuCommandType
import dev.slne.discord.guild.permission.CommandPermission
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.interaction.command.GenericContextInteractionEvent
import net.dv8tion.jda.api.interactions.InteractionHook

@DiscordContextMenuCommandMeta(
    name = "Send Reply Deadline",
    type = DiscordContextMenuCommandType.USER,
    permission = CommandPermission.TICKET_REPLY_DEADLINE,
)
class SendReplyDeadlineContextMenuCommand : DiscordContextMenuCommand<User>() {

    override suspend fun internalExecute(
        interaction: GenericContextInteractionEvent<User>,
        hook: InteractionHook
    ) {
        sendReplyDeadlineToChannel(interaction, interaction.target, hook)
    }
}