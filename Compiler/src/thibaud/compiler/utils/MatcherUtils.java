package thibaud.compiler.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherUtils {
	private static String keyAll = "(^\\w+)\\(((\\d+\\.?\\d*, )+)(.+)?\\)";
	private static Pattern pattern = Pattern.compile(keyAll);

	public static Matcher getMatcher(String text) {
		return pattern.matcher(text);
	}

	public static String getType(String text) {
		Matcher matcher = getMatcher(text);
		if (matcher.find()) {
			return matcher.group(1);
		}
		LogUtil.log("No match returned null(getType)", 1);
		return null;
	}

	public static Float[] getArgs(String text) {
		Matcher matcher = getMatcher(text);
		String[] str;
		Float[] args = new Float[4];
		if (matcher.find()) {
			str = matcher.group(2).split(",");
			for (int i = 0; i < str.length-1; i++) {
				args[i] = Float.valueOf(str[i]);
			}
			return args;
		}
		LogUtil.log("No match returned null (getArgs)", 1);
		return null;
	}

	public static String getShape(String text) {
		Matcher matcher = getMatcher(text);
		if (matcher.find()) {
			return matcher.group(4);
		}
		LogUtil.log("No match returned null", 1);
		return null;
	}
}
