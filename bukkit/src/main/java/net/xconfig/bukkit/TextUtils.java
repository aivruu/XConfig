package net.xconfig.bukkit;

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
 * @version 1.1.7
 * @since 1.0.5
 */
public class TextUtils {
	private static final Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}}");
	private static final StringBuilder BUILDER = new StringBuilder();
	private static final int VERSION = Integer.parseInt(Bukkit.getBukkitVersion()
		 .split("-")[0]
		 .split("\\.")[1]);
	
	private TextUtils() {}
	
	/**
	 * Checks if the sequence given contains the next sequence specified without import if are Case Sensitive.
	 *
	 * @param target The string to check.
	 * @param search The string to search into the string target specified.
	 * @return True if the sequence was found, else return false.
	 */
	public static boolean containsIgnoreCase(String target, String search) {
		if ((target == null) || search == null) return false;
		
		final int length = search.length();
		final int max = target.length() - length;
		
		for (int i = 0 ; i < max ; i++) {
			if (target.regionMatches(true, i, search, 0, length)) return true;
		}

		return false;
	}
	
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
	 * @param text The text to colorize.
	 * @return The text colorized.
	 */
	public static String colorize(String text) {
		if (VERSION < 16) return ChatColor.translateAlternateColorCodes('&', text);
		
		final String[] parts = text.split(String.format("((?<=%1$s)|(?=%1$s))", "&"));
		Matcher matcher = HEX_PATTERN.matcher(text);
		
		if (!text.contains("&#")) {
			while (matcher.find()) {
				text = text.replace(
					 text.substring(matcher.start(), matcher.end()),
					 String.valueOf(ChatColor.of(text.substring(matcher.start(), matcher.end())))
				);
				matcher = HEX_PATTERN.matcher(text);
			}
			
			BUILDER.append(text);
			return BUILDER.toString();
		}

		for (int i = 0 ; i < parts.length ; i++) {
			if (parts[i].equals("&")) {
				i++;
				if (parts[i].charAt(0) == '#') BUILDER.append(ChatColor.of(parts[i].substring(0, 7)));
				else BUILDER.append(ChatColor.translateAlternateColorCodes('&', "&" + parts[i]));
			} else {
				while (matcher.find()) {
					parts[i] = parts[i].replace(
						 parts[i].substring(matcher.start(), matcher.end()),
						 String.valueOf(ChatColor.of(parts[i].substring(matcher.start(), matcher.end())))
					);
					matcher = HEX_PATTERN.matcher(text);
				}
				
				BUILDER.append(parts[i]);
			}
		}
		
		return BUILDER.toString();
	}
}
