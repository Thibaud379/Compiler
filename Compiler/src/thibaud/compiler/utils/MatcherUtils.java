package thibaud.compiler.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import thibaud.compiler.Main;

public class MatcherUtils {
	public final static String keyAll = "(^\\w+)\\(((.+, )+)(.+)?\\)";
	public final static String keyVariableDeclaration = "(\\w+) (\\w+) = (.+)";
	private static Pattern pattern;

	public static Matcher getMatcher(String text, String pat) {
		pattern = Pattern.compile(pat);
		return pattern.matcher(text);
	}

	public static String getType(String text) {
		Matcher matcher = getMatcher(text, keyAll);
		if (matcher.find()) {
			return matcher.group(1);
		}
		LogUtil.log("No match; returned null(getType)", 2);
		return null;
	}

	public static Float[] getArgs(String text) {
		Matcher matcher = getMatcher(text, keyAll);
		String[] str;
		Float[] args = new Float[4];
		if (matcher.find()) {
			str = matcher.group(2).split(", ");
			for (int i = 0; i < str.length; i++) {
				try {
					args[i] = Float.valueOf(str[i]);
				}catch(NumberFormatException e){
					LogUtil.log("Variable found: "+ str[i], 0);
					if(str[i].startsWith("#")) {
						args[i] = Float.valueOf(Main.sf.get(str[i].substring(1)));
						
					}
					
				}
				
			}
			return args;
		}
		LogUtil.log("No match; returned null(getArgs)", 2);
		return null;
	}

	public static String getShape(String text) {
		Matcher matcher = getMatcher(text, keyAll);
		if (matcher.find()) {
			
			return matcher.group(4);
		}
		LogUtil.log("No match; returned null(getShape)", 2);
		return null;
	}
	
	public static String getVarType(String text) {
		Matcher matcher = getMatcher(text, keyVariableDeclaration);
		if (matcher.find()) {
			return matcher.group(1);
		}
		LogUtil.log("No match; returned null(getVarType)", 2);
		return null;
	}
	
	public static String getVarName(String text) {
		Matcher matcher = getMatcher(text, keyVariableDeclaration);
		if (matcher.find()) {
			return matcher.group(2);
		}
		LogUtil.log("No match; returned null(getVarName)", 2);
		return null;
	}
	
	public static Float getVarValue(String text) {
		Matcher matcher = getMatcher(text, keyVariableDeclaration);
		if (matcher.find()) {
			return Float.valueOf(matcher.group(3));
		}
		LogUtil.log("No match; returned null(getVarValue)", 2);
		return null;
	}
	
	public static String getVarArgs(String text) {
		Matcher matcher = getMatcher(text, keyVariableDeclaration);
		if (matcher.find()) {
			return matcher.group(3);
		}
		LogUtil.log("No match; returned null(getVarArgs)", 2);
		return null;
	}
	
	
}
