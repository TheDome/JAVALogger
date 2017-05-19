/*
 * Copyright (c) 2017 TheDome
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * This software is open source, so it is forbidden, to sell this software or any parts of it.
 */

package me.thedome.javalogger.core;

import me.thedome.javalogger.LogLevel;

import java.util.Arrays;

/**
 * me.thedome.logging.core
 * Created on 02/2017.
 */
public class Logger {

	//Logger instance
	private static Logger instance = new Logger();

	// The current level of log
	private LogLevel logLevel = LogLevel.INFO;


	private Logger() {

	}

	public static Logger getInstance() {
		return instance;
	}

	/**
	 * Prepare a message ( Replace placeholders with the Strings of the objects) Placeholders are {}
	 *
	 * @param message The message to prepare
	 * @param objects The Objects to insert
	 */
	public static String prepare(String message, Object... objects) {
		if (objects.length == 0) return message;

		// The formatted string
		String formatted = "";

		// The number of object to insert into the string
		int objectNo = 0;

		for (int i = 0; i < message.length(); i++) {
			char c = message.charAt(i);

			if (c == '{' && message.charAt(++i) == '}') {
				formatted += convertToString(objects[objectNo++]);
				continue;
			}

			formatted += c;

		}


		return formatted;
	}

	/**
	 * Convert an Object into a string. This als includes tests of instances. Else, the <code>toString</code> method will be called.
	 *
	 * @param o The Object to convert
	 *
	 * @return A String that represents the object
	 */
	private static String convertToString(Object o) {
		String objectRep;

		// If my object is the instance of an array ...
		if (o instanceof String[]) {
			objectRep = Arrays.toString((String[]) o);
		} else if (o instanceof int[]) {
			objectRep = Arrays.toString((int[]) o);
		} else if (o instanceof byte[]) {
			objectRep = Arrays.toString((byte[]) o);
		} else {
			// Else use its ToString method
			objectRep = o.toString();
		}

		return objectRep;
	}

	/**
	 * Set the level of logging
	 *
	 * @param level the Level of logging to be applied to the console
	 */
	public void setLevel(LogLevel level) {
		if (level.level >= 0) {
			logLevel = level;
		}
	}

	/**
	 * Debug a string at a specific level of warning
	 *
	 * @param level   The level for the message
	 * @param message the message itself
	 */
	public void debug(LogLevel level, String message) {
		if (logLevel.level >= level.level) {
			print("[ " + level + " ] " + message);
		}
	}

	/**
	 * Print a message
	 *
	 * @param s the message to print
	 */
	public void print(String s) {
		System.out.printf("%s\n", s);
	}

	/**
	 * Print an Object
	 *
	 * @param o the object to print should be put in here
	 */
	public void print(Object o) {
		print(o.toString());
	}

	/**
	 * Log a String at <link>LogLevel.INFO</link><br></br>
	 * The message can be passed with placeholders. Theses placeholders will be replaces by the objects passed to the warning message. The code for a placeholder is: <code>{}</code>
	 *
	 * @param message The message to print
	 * @param objects Objects to insert into the string
	 */
	public void INFO(String message, Object... objects) {
		message = Logger.prepare(message, objects);
		print(message);
	}

	/**
	 * Warn the user about an error. <br></br>
	 * The message can be passed with placeholders. Theses placeholders will be replaces by the objects passed to the warning message. The code for a placeholder is: <code>{}</code>
	 *
	 * @param message The message to print
	 * @param tmp     Is the warning temporary or not? eg. no internet connection is only a temporaray issue
	 * @param objects The objects to insert into the String
	 */
	public void WARN(String message, boolean tmp, Object... objects) {
		message = Logger.prepare(message, objects);

		if (tmp) {
			debug(LogLevel.TMP_WARN, message);
		} else {
			debug(LogLevel.WARN, message);
		}
	}

	/**
	 * Log a String at <link>LogLevel.TRACE</link> - Use this for trace logs. Theses logs are for debuggers at the end of an critical error.<br></br>
	 * The message can be passed with placeholders. Theses placeholders will be replaces by the objects passed to the warning message. The code for a placeholder is: <code>{}</code>
	 *
	 * @param message The message to print
	 * @param objects Objects to insert into the string
	 */
	public void TRACE(String message, Object... objects) {
		message = Logger.prepare(message, objects);
		debug(LogLevel.TRACE, message);
	}

	/**
	 * Log a String at <link>LogLevel.DEBUG</link> - this is turned off by default. Log there all messages to understand the application, but don't spam<br></br>
	 * The message can be passed with placeholders. Theses placeholders will be replaces by the objects passed to the warning message. The code for a placeholder is: <code>{}</code>
	 *
	 * @param message The message to print
	 * @param objects Objects to insert into the string
	 */
	public void DEBUG(String message, Object... objects) {
		message = Logger.prepare(message, objects);
		debug(LogLevel.DEBUG, message);
	}

	/**
	 * Log a String at <link>LogLevel.ERROR</link> - the not mutable channel. Use this for critical system errors!<br></br>
	 * The message can be passed with placeholders. Theses placeholders will be replaces by the objects passed to the warning message. The code for a placeholder is: <code>{}</code>
	 *
	 * @param message The message to print
	 * @param objects Objects to insert into the string
	 */
	public void ERROR(String message, Object... objects) {
		message = Logger.prepare(message, objects);
		debug(LogLevel.ERROR, message);
	}


}
