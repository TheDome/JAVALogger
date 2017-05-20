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

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

/**
 * me.thedome.logging.core
 * Created on 02/2017.
 */
public class Logger {

	//Logger instance
	private static final Logger instance = new Logger();
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private boolean displayTimeString = false;

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
	 * Convert an Object into a string. This als includes tests of instances. Else, the <code>toString</code> method
	 * will be called.
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
	 * Set, if the prefix for the time should be visible. The prefix is usually only visible in DEBUG mode. but if you
	 * want to yiew the current time even in the INFO mode, then you can enable it with this.
	 *
	 * @param display A boolean, that represents, whether the prefix of time should be displayed.
	 */
	public void displayTimeString(boolean display) {
		displayTimeString = display;
	}

	/**
	 * Set the time string which prefixes every message. This String should be a Date format. Available options
	 * are:<br></br> G 	Era designator (before christ, after christ) <br></br> y 	Year (e.g. 12 or 2012). Use either yy
	 * or yyyy.<br></br> M 	Month in year. Number of M's determine length of format (e.g. MM, MMM or MMMMM)<br></br> d
	 * 	Day in month. Number of d's determine length of format (e.g. d or dd)<br></br> h 	Hour of day, 1-12 (AM / PM)
	 * (normally hh)<br></br> H 	Hour of day, 0-23 (normally HH)<br></br> m 	Minute in hour, 0-59 (normally mm)<br></br>
	 * s 	Second in minute, 0-59 (normally ss)<br></br> S 	Millisecond in second, 0-999 (normally SSS)<br></br> E 	Day
	 * in week (e.g Monday, Tuesday etc.)<br></br> D 	Day in year (1-366)<br></br> F 	Day of week in month (e.g. 1st
	 * Thursday of December)<br></br> w 	Week in year (1-53)<br></br> W 	Week in month (0-5)<br></br> a 	AM / PM
	 * marker<br></br> k 	Hour in day (1-24, unlike HH's 0-23)<br></br> K 	Hour in day, AM / PM (0-11)<br></br> z 	Time
	 * Zone<br></br> ' 	Escape for text delimiter<br></br> ' 	Single quote<br></br> <br></br><br></br>
	 * <p>
	 * The source of this table is: http://tutorials.jenkov.com/java-internationalization/simpledateformat.html
	 *
	 * @param timeString A string, that represents the format for the logs
	 *
	 * @throws IllegalArgumentException Throws an IllegalArgumentException, when the string isn't conform with the
	 *                                  dateformat, that should be used.
	 */
	public void setTimeString(String timeString) throws IllegalArgumentException {
		format = new SimpleDateFormat(timeString);
	}

	/**
	 * Defines, whether the Loggin utility should show the Time before each log, or not. The Time will be in the time
	 * format, that is set. The default value is 'yyyy-MM-dd HH:mm:ss.SSS'. <br></br> An example would be:
	 * <code>[2017-02-20 12:12:12.567] [ DEBUG ] Init</code>
	 *
	 * @param show Whether the time will be shown before each log.
	 */
	public void showTime(boolean show) {
		this.displayTimeString = show;
	}

	/**
	 * Set the level of logging. <br></br>
	 * <p>
	 * Beware! If you change the level, the option of the time prefix will also be changed!
	 *
	 * @param level the Level of logging to be applied to the console
	 */
	public void setLevel(LogLevel level) {
		if (level.level >= 0) {
			logLevel = level;
			displayTimeString = level.level > LogLevel.DEBUG.level;
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
			if (displayTimeString) {
				// Prefix the time
				print("[" + format.format(Calendar.getInstance().getTime()) + "] [ " + level + " ] \t" + message);
			} else {
				print("[ " + level + " ] " + message);
			}
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
	 * Log a String at <link>LogLevel.INFO</link><br></br> The message can be passed with placeholders. Theses
	 * placeholders will be replaces by the objects passed to the warning message. The code for a placeholder is:
	 * <code>{}</code>
	 *
	 * @param message The message to print
	 * @param objects Objects to insert into the string
	 */
	public void INFO(String message, Object... objects) {
		message = Logger.prepare(message, objects);
		if (displayTimeString) {
			debug(LogLevel.INFO, message);
		} else {
			print(message);
		}
	}

	/**
	 * Warn the user about an error. <br></br> The message can be passed with placeholders. Theses placeholders will be
	 * replaces by the objects passed to the warning message. The code for a placeholder is: <code>{}</code>
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
	 * Log a String at <link>LogLevel.TRACE</link> - Use this for trace logs. Theses logs are for debuggers at the end
	 * of an critical error.<br></br> The message can be passed with placeholders. Theses placeholders will be replaces
	 * by the objects passed to the warning message. The code for a placeholder is: <code>{}</code>
	 *
	 * @param message The message to print
	 * @param objects Objects to insert into the string
	 */
	public void TRACE(String message, Object... objects) {
		message = Logger.prepare(message, objects);
		debug(LogLevel.TRACE, message);
	}

	/**
	 * Log a String at <link>LogLevel.DEBUG</link> - this is turned off by default. Log there all messages to understand
	 * the application, but don't spam<br></br> The message can be passed with placeholders. Theses placeholders will be
	 * replaces by the objects passed to the warning message. The code for a placeholder is: <code>{}</code>
	 *
	 * @param message The message to print
	 * @param objects Objects to insert into the string
	 */
	public void DEBUG(String message, Object... objects) {
		message = Logger.prepare(message, objects);
		debug(LogLevel.DEBUG, message);
	}

	/**
	 * Log a String at <link>LogLevel.ERROR</link> - the not mutable channel. Use this for critical system
	 * errors!<br></br> The message can be passed with placeholders. Theses placeholders will be replaces by the objects
	 * passed to the warning message. The code for a placeholder is: <code>{}</code>
	 *
	 * @param message The message to print
	 * @param objects Objects to insert into the string
	 */
	public void ERROR(String message, Object... objects) {
		message = Logger.prepare(message, objects);
		debug(LogLevel.ERROR, message);
	}


}
