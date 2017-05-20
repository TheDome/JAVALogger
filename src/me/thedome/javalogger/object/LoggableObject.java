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

package me.thedome.javalogger.object;

import me.thedome.javalogger.core.LogLevel;
import me.thedome.javalogger.core.Logger;

import java.io.Serializable;

/**
 * Spotify Downloader/me.thedome.spotifydownload.objects
 * Created on 02/2017.
 * <br/>
 * A basic object, that contains some essential functions. This is, that you must not give these functions any class.
 */
public abstract class LoggableObject implements Serializable {


	private static final long serialVersionUID = -4452844348804951615L;
	private static final Logger log = Logger.getInstance();

	/**
	 * Log a String at the specifies LogLevel.<br></br>
	 * The message can be passed with placeholders. Theses placeholders will be replaces by the objects passed to the warning message. The code for a placeholder is: <code>{}</code>
	 *
	 * @param message The message to print
	 * @param level   the level to send the message to
	 * @param objects Objects to insert into the string
	 */
	protected static void logAtLevel(String message, LogLevel level, Object... objects) {
		message = Logger.prepare(message, objects);
		log.debug(level, message);
	}

	/**
	 * Log a String at <link>LogLevel.INFO</link><br></br>
	 * The message can be passed with placeholders. Theses placeholders will be replaces by the objects passed to the warning message. The code for a placeholder is: <code>{}</code>
	 *
	 * @param message The message to print
	 * @param objects Objects to insert into the string
	 */
	protected void INFO(String message, Object... objects) {
		message = Logger.prepare(message, objects);
		log.INFO(message);
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
			log.WARN(message, true);
		} else {
			log.WARN(message, false);
		}
	}

	/**
	 * Log a String at <link>LogLevel.TRACE</link> - Use this for trace logs. Theses logs are for debuggers at the end of an critical error.<br></br>
	 * The message can be passed with placeholders. Theses placeholders will be replaces by the objects passed to the warning message. The code for a placeholder is: <code>{}</code>
	 *
	 * @param message The message to print
	 * @param objects Objects to insert into the string
	 */
	protected void TRACE(String message, Object... objects) {
		message = Logger.prepare(message, objects);
		log.TRACE(message);
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
		log.DEBUG(message);
	}

	/**
	 * Log a String at <link>LogLevel.ERROR</link> - the not mutable channel. Use this for critical system errors!<br></br>
	 * The message can be passed with placeholders. Theses placeholders will be replaces by the objects passed to the warning message. The code for a placeholder is: <code>{}</code>
	 *
	 * @param message The message to print
	 * @param objects Objects to insert into the string
	 */
	protected void ERROR(String message, Object... objects) {
		message = Logger.prepare(message, objects);
		log.ERROR(message);
	}

}
