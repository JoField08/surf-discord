package dev.slne.discord.guild.permission

enum class CommandPermission(val permission: String) {
    NO_INTEREST("NO_INTEREST"),
    WHITELIST("WHITELIST"),
    WHITELISTED("WHITELISTED"),
    WHITELIST_QUERY("WHITELIST_QUERY"),
    WHITELIST_ROLE("WHITELIST_ROLE"),
    WHITELIST_UNBLOCK("WHITELIST_UNBLOCK"),
    WHITELIST_CHANGE("WHITELIST_CHANGE"),
    TICKET_ADD_USER("TICKET_ADD_USER"),
    TICKET_REMOVE_USER("TICKET_REMOVE_USER"),
    TICKET_CLOSE("TICKET_CLOSE"),
    TICKET_BUTTONS("TICKET_BUTTONS"),
    TICKET_REPLY_DEADLINE("TICKET_REPLY_DEADLINE"),
    DONT_ASK_TO_ASK("DONT_ASK_TO_ASK"),
    HOW_TO_JOIN("HOW_TO_JOIN"),
    FAQ("FAQ"),
    MISSING_INFORMATION("MISSING_INFORMATION"),
    REQUEST_ROLLBACK("REQUEST_ROLLBACK"),
}
