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
 * An enum to specify a Color for some strings
 */
public enum LogColor {

	RESET("0"), RED("31"), GREEN("32"), YELLOW("33"), BLUE("34"), MAGENTA("35"), CYAN("36"), WHITE("37");

	String code;

	LogColor(String code){
		this.code = "\033[" + code + "m";
	}

	public String toString(){
		return code;
	}
}
