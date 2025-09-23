package morganStanley_Day2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class OptionalEx {

	public static void main(String[] args) {

		/*
		 * Q1. First non-empty Optional and return its value, otherwise return "Not
		 * Found".
		 */
		System.out.println("Ex1: First non-empty Optional");
		List<Optional<String>> ex1 = Arrays.asList(Optional.empty(), Optional.of("Java"), Optional.of("Spring"),
				Optional.empty());
		List<Optional<String>> ex2 = Arrays.asList(Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty());

		String notEmptyEx1 = ex1.stream().filter(Optional::isPresent).map(Optional::get).findFirst()
				.orElseGet(() -> "Not Found");
		System.out.println("First Non Empty String: " + notEmptyEx1);

		String notEmptyEx2 = ex2.stream().filter(Optional::isPresent).map(Optional::get).findFirst()
				.orElseGet(() -> "Not Found");
		System.out.println("First Non Empty String: " + notEmptyEx2);
		System.out.println("--------------------------------------------");

		/*
		 * Q2. First longest non-empty Optional and return its value, otherwise return
		 * "Not Found".
		 */
		System.out.println("Ex2: First longest non-empty Optional");
		List<Optional<String>> ex3 = Arrays.asList(Optional.of("Java"), Optional.empty(), Optional.of("SpringBoot"),
				Optional.of("AI"), Optional.empty());

		String longestNotEmptyEx1 = ex3.stream().filter(Optional::isPresent).map(Optional::get)
				.max(Comparator.comparingInt(String::length)).orElseGet(() -> "Not Found");
		System.out.println("Longest Non Empty String: " + longestNotEmptyEx1);
		System.out.println("--------------------------------------------");

		// Q3. Check whether List contains null values or non null values
		System.out.println("Ex3: Check for null or not null values");
		List<Optional<String>> ex4 = Arrays.asList(Optional.of("Java"), Optional.empty(), Optional.of("SpringBoot"),
				Optional.of("AI"), Optional.empty());
		List<Optional<String>> ex5 = Arrays.asList(Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty());
		List<Optional<String>> ex6 = Arrays.asList(Optional.ofNullable(null), Optional.ofNullable(null),
				Optional.ofNullable(null), Optional.ofNullable(null), Optional.ofNullable(null));

		String checkNullEx1 = ex4.stream().anyMatch(Optional::isPresent) ? "Not Null Values Found"
				: "Null Values Found";
		System.out.println("Found or Not Found?: " + checkNullEx1);

		String checkNullEx2 = ex5.stream().anyMatch(Optional::isPresent) ? "Not Null Values Found"
				: "Null Values Found";
		System.out.println("Found or Not Found?: " + checkNullEx2);

		String checkNullEx3 = ex6.stream().anyMatch(Optional::isPresent) ? "Not Null Values Found"
				: "Null Values Found";
		System.out.println("Found or Not Found?: " + checkNullEx3);
		System.out.println("--------------------------------------------");

		//Q4. Last non-empty Optional value else return Not Found
		System.out.println("Ex4: Last non-empty Optional");
		List<Optional<String>> ex7 = Arrays.asList(Optional.empty(), Optional.of("Java"), Optional.of("Spring"),
				Optional.empty());

		String lastNotEmptyEx1 = ex7.stream().filter(Optional::isPresent).map(Optional::get)
				.reduce((first, second) -> second).orElseGet(() -> "Not Found");
		System.out.println("First Non Empty String: " + lastNotEmptyEx1);
		System.out.println("--------------------------------------------");
		
		//Q5. nth Not-Null Optional value
		System.out.println("Ex5: Nth non-empty Optional value");
		List<Optional<String>> ex8 = Arrays.asList(Optional.of("Java"), Optional.empty(), Optional.of("SpringBoot"),
				Optional.of("AI"), Optional.empty());
		
		String nNotNullValues1 = ex8.stream().filter(Optional::isPresent).map(Optional::get).skip(3).findFirst().orElseGet(() -> "Not Found");
		System.out.println("4th Not null String Values: " + nNotNullValues1);

		String nNotNullValues2 = ex8.stream().filter(Optional::isPresent).map(Optional::get).skip(2).findFirst().orElseGet(() -> "Not Found");
		System.out.println("3rd Not null String Values: " + nNotNullValues2);
		System.out.println("--------------------------------------------");
		
		//Q6. last nth Not-Null Optional value
		System.out.println("Ex6: Last Nth non-empty Optional value");
		List<Optional<String>> ex9 = Arrays.asList(Optional.of("Java"), Optional.empty(), Optional.of("SpringBoot"),
				Optional.of("AI"), Optional.empty());
		Collections.reverse(ex9);
		
		String lastNNotNullValues1 = ex9.stream().filter(Optional::isPresent).map(Optional::get).skip(3).findFirst().orElseGet(() -> "Not Found");
		System.out.println("Last 4th Not null String Values: " + lastNNotNullValues1);

		String lastNNotNullValues2 = ex9.stream().filter(Optional::isPresent).map(Optional::get).skip(2).findFirst().orElseGet(() -> "Not Found");
		System.out.println("Last 3rd Not null String Values: " + lastNNotNullValues2);
	}

}
