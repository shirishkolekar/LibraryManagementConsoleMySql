package com.sudha;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Utilities {

	public static String getInput() {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.flush();
		try {
			return stdin.readLine();
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}

	public enum LoginStatus {
		// List of named integer constant because their value is constant.(0,1,2)
		UserNotFound, PasswordIncorrect, Success;
	}
}