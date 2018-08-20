package com.gr.urlshortener.core.service;

import java.util.Date;

public class UrlShortener {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int BASE = ALPHABET.length();

	public static String getRandomString() {
		long num = new Date().getTime();
		StringBuilder sb = new StringBuilder();
		while (num > 0) {
			sb.append(ALPHABET.charAt((int) (num % BASE)));
			num /= BASE;
		}
		return sb.reverse().toString();
	}
}
