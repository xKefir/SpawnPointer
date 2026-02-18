package org.minerail.spawnPointer.util;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;

public class MessageDeliverUtil {
    public static void sendFormatted(String text, Player p, String formatType, TagResolver... resolvers) {
        switch (formatType) {
            case "LEGACY" -> p.sendMessage(TextFormatUtil.legacyFormat(text, resolvers));
            case "MINIMESSAGE" -> p.sendMessage(TextFormatUtil.format(text, resolvers));
        }
    }
}
