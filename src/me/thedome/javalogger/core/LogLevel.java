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

/**
 * me.thedome.logging.core
 * Created on 02/2017.
 */
public enum LogLevel {

	/**
	 * The different level of warnings. Info is displayed by default. But it can be disabled.
	 * Panics can't be disabled, because that is the  last message before crash
	 */
	ERROR(-2), WARN(-1), TMP_WARN(-1), INFO(0), DEBUG(1), TRACE(1);

	public final int level;

	LogLevel(int level){
		this.level = level;
	}

	@Override
	public String toString() {
		// The String to return
		String response;
		/*
		 * - Print warnings in yellow
		 * - Print Panics in RED
		 * - Print Debug in magenta
		 */
		switch (level){
			// WARNINGS
			case -1:
				response = LogColor.YELLOW + super.toString() +  LogColor.RESET;
				break;
			// PANICS - Hopefully not
			case -2:
				response = LogColor.RED + super.toString() +  LogColor.RESET;
				break;
			// DEBUG
			case 1:
				response = LogColor.MAGENTA + super.toString() +  LogColor.RESET;
				break;
			default:
				response = super.toString();
		}

		// Return the Response
		return response;
	}
}
