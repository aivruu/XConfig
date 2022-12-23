package net.xconfig.bukkit.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Utility class to colorize strings at Bukkit (Spigot/Paper) platforms.
 *
 * @author InitSync
 * @version 1.1.3
 * @since 1.0.5
 */
public final class TextUtils {
	private static final int VERSION = Integer.parseInt(Bukkit.getBukkitVersion()
		.split("-")[0]
		.split("\\.")[1]);
	private static final Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}}");
	private static final StringBuilder BUILDER = new StringBuilder();
	
	private TextUtils() {}
	
	/**
	 * Colorize the list content.
	 *
	 * @param textList A List of text.
	 * @return The list colorized.
	 */
	public static List<String> colorize(List<String> textList) {
		return textList.stream()
			.map(TextUtils::colorize)
			.collect(Collectors.toList());
	}
	
	/**
	 * Colorize the string passed as parameter, if the server version number is lower than 16 (-1.16),
	 * will colorize with the normal colors, overwise, will apply the HEX colors.
	 * <p>
	 * [!] Original code taken out from SternalBoard repository:
	 * https://github.com/ShieldCommunity/SternalBoard/blob/main/plugin/src/main/java/com/xism4/
	 * sternalboard/utils/PlaceholderUtils.java
	 *
	 * @param text Text to colorize.
	 * @return Text colorized.
	 */
	public static String colorize(String text) {
		if (VERSION < 16) return ChatColor.translateAlternateColorCodes('&', text);
		
		String[] parts = text.split(String.format("((?<=%1$s)|(?=%1$s))", "&"));
		Matcher matcher = HEX_PATTERN.matcher(text);
		
		if (text.contains("&#")) {
			int length = parts.length;
			for (int i = 0 ; i < length ; i++) {
				if (parts[i].equals("&")) {
					i++;
					if (parts[i].charAt(0) == '#') BUILDER.append(ChatColor.of(parts[i].substring(0, 7)));
					else BUILDER.append(ChatColor.translateAlternateColorCodes('&', "&" + parts[i]));
				} else {
					while (matcher.find()) {
						String color = parts[i].substring(matcher.start(), matcher.end());
						parts[i] = parts[i].replace(color, ChatColor.of(color) + "");
						matcher = HEX_PATTERN.matcher(text);
					}
					BUILDER.append(parts[i]);
				}
			}
		} else {
			while (matcher.find()) {
				String color = text.substring(matcher.start(), matcher.end());
				text = text.replace(color, ChatColor.of(color) + "");
				matcher = HEX_PATTERN.matcher(text);
			}
			BUILDER.append(text);
		}
		
		return BUILDER.toString();
	}
}
