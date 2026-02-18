package org.minerail.spawnPointer.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class TextFormatUtil {
    public static Component format(String input, TagResolver... resolvers) {
        return MiniMessage.miniMessage().deserialize(input, resolvers).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }
    public static Component legacyFormat(String input, TagResolver... resolvers) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(
                LegacyComponentSerializer.legacyAmpersand().serialize(
                        MiniMessage.builder().build().deserialize(input, resolvers))).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }
}
