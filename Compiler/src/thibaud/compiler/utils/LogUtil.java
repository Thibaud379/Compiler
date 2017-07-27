package thibaud.compiler.utils;

import thibaud.compiler.Main;

public class LogUtil {

	private static String[] levels = { "DEBUG", "INFO", "WARNING", "ERROR", "FATAL" };

	public static void log(String msg, int level) {
		if(level == 0 && Main.args[1].equals("debug") || level != 0) {
			for(String str:msg.split("\n")) {
				System.out.println("[" + levels[level] + "](line "+(Main.index+1)+") "+ str);
			}
			if(level == 4) {
				System.exit(1);
			}
		}


	}
}
