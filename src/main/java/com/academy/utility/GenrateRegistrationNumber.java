package com.academy.utility;

import org.springframework.stereotype.Component;

@Component
public class GenrateRegistrationNumber {
	private static int registrationSequence = 1;

	public static String generateRegistrationNumber(String name) {

	    String namePart = name
	            .replaceAll("[^a-zA-Z]", "")
	            .toUpperCase();

	    // First 4 characters
	    if (namePart.length() > 4) {
	        namePart = namePart.substring(0, 4);
	    }

	    // If name has less than 4 characters
	    while (namePart.length() < 4) {
	        namePart += "X";
	    }

	    // Generate 5 digit number
	    String number = String.format(
	            "%05d",
	            registrationSequence++
	    );

	    return "MAASHARDA-" + namePart + "-" + number;
	}
}
