package dev.slne.discord.discord.interaction.command.context.commands.ticket

import dev.minn.jda.ktx.coroutines.await
import dev.slne.discord.annotation.DiscordContextMenuCommandMeta
import dev.slne.discord.discord.interaction.command.context.DiscordContextMenuCommand
import dev.slne.discord.discord.interaction.command.context.DiscordContextMenuCommandType
import dev.slne.discord.guild.permission.CommandPermission
import dev.slne.discord.message.translatable
import dev.slne.discord.persistence.service.ticket.TicketService
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel
import net.dv8tion.jda.api.events.interaction.command.GenericContextInteractionEvent
import net.dv8tion.jda.api.interactions.InteractionHook
import net.dv8tion.jda.api.interactions.components.buttons.Button

@DiscordContextMenuCommandMeta(
    name = "Show Ticket in Panel",
    type = DiscordContextMenuCommandType.MESSAGE,
    permission = CommandPermission.SHOW_TICKET_ADMIN_PANEL,
)
class OpenTicketAdminPanelContextMenuCommand(
    private val ticketService: TicketService
) : DiscordContextMenuCommand<Message>() {

    override suspend fun internalExecute(
        interaction: GenericContextInteractionEvent<Message>,
        hook: InteractionHook
    ) {
        val channel = interaction.target.channel as? ThreadChannel ?: run {
            hook.editOriginal(translatable("interaction.context.menu.ticket-admin-panel.not-a-thread"))
                .await()
            return
        }

        val ticket = ticketService.getTicketByThreadId(channel.id) ?: run {
            hook.editOriginal(translatable("interaction.context.menu.ticket-admin-panel.not-a-ticket"))
                .await()
            return
        }

        val privateChannel = interaction.user.openPrivateChannel().await()

        try {
            val button = Button.link(
                "https://admin.slne.dev/ticket/${ticket.ticketId}",
                translatable("interaction.context.menu.admin-panel.button")
            )

            privateChannel
                .sendMessage(
                    translatable(
                        "interaction.context.menu.ticket-admin-panel.private-message",
                        channel.asMention
                    )
                )
                .addActionRow(button)
                .await()


            hook.deleteOriginal().await()
        } catch (e: Exception) {
            hook.editOriginal(translatable("interaction.context.menu.admin-panel.no-private-message-allowed"))
                .await()
        }
    }
}
