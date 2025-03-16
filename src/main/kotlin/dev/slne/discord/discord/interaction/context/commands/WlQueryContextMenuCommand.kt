package dev.slne.discord.discord.interaction.context.commands

import dev.minn.jda.ktx.coroutines.await
import dev.slne.discord.annotation.DiscordContextMenuCommandMeta
import dev.slne.discord.discord.interaction.context.DiscordContextMenuCommand
import dev.slne.discord.discord.interaction.context.DiscordContextMenuCommandType
import dev.slne.discord.guild.permission.CommandPermission
import dev.slne.discord.message.MessageManager
import dev.slne.discord.message.translatable
import dev.slne.discord.persistence.service.whitelist.WhitelistService
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.interaction.command.GenericContextInteractionEvent
import net.dv8tion.jda.api.interactions.InteractionHook

@DiscordContextMenuCommandMeta(
    name = "Whitelist Query",
    type = DiscordContextMenuCommandType.USER,
    permission = CommandPermission.WHITELIST_QUERY,
)
class WlQueryContextMenuCommand(
    private val whitelistService: WhitelistService,
    private val messageManager: MessageManager
) : DiscordContextMenuCommand<User>() {

    override suspend fun internalExecute(
        interaction: GenericContextInteractionEvent<User>,
        hook: InteractionHook
    ) {
        hook.editOriginal(translatable("interaction.command.ticket.wlquery.querying")).await()

        val whitelists = whitelistService.findWhitelists(
            null, interaction.target.id, null
        )

        val privateChannel = interaction.user.openPrivateChannel().await()

        try {
            messageManager.printUserWlQuery(
                whitelists,
                interaction.target.name,
                privateChannel,
                hook
            )
        } catch (e: Exception) {
            hook.editOriginal(translatable("interaction.context.menu.admin-panel.no-private-message-allowed"))
                .await()
        }
    }
}