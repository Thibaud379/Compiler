package thibaud.compiler.utils;

public class LogUtil {

	private static String[] levels = { "DEBUG", "INFO", "WARNING", "ERROR", "FATAL" };

	public static void log(String msg, int level) {
		System.out.println("[" + levels[level] + "] " + msg);

	}
}
