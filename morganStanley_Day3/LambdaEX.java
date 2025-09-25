package morganStanley_Day3;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LambdaEX {

	public static void main(String[] args) {

		List<Employee> employees = Arrays.asList(
				new Employee(101, "Anuska1", "Sinha1", LocalDate.of(1990, 5, 12), 2500, "IT"),
				new Employee(102, "Anuska2", "Sinha2", LocalDate.of(1985, 3, 20), 1800, "HR"),
				new Employee(103, "Anuska3", "Sinha3", LocalDate.of(1992, 8, 15), 3000, "Finance"),
				new Employee(104, "Anuska4", "Sinha4", LocalDate.of(1995, 1, 10), 2200, "IT"));

		List<User> users = Arrays.asList(new User(101, "Anuska1Sinha11990101", "abc123xyz"),
				new User(102, "Anuska2Sinha21985102", "def456uvw"), new User(103, "Anuska3Sinha31992103", "ghi789rst"),
				new User(104, "Anuska4Sinha41995104", "jkl012opq"));

		Consumer<Employee> printEmp = System.out::println;
		Consumer<User> printUser = System.out::println;

		Supplier<String> passwordSupplier = () -> {
			String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			Random random = new Random();
			return random.ints(16, 0, chars.length()).mapToObj(i -> String.valueOf(chars.charAt(i)))
					.collect(Collectors.joining());
		};
		
		Supplier<Map<String, Double>> generateBonus = () -> {
			Map<String, Double> bonusMap = new HashMap<>();
			bonusMap.put("IT", 1000.0);
			bonusMap.put("HR", 800.0);
			bonusMap.put("Finance", 1200.0);
			return bonusMap;
		};

		UserNameGenerator userNameGenerator = (firstName, lastName, yearBirth, empId) -> firstName + lastName
				+ yearBirth + empId;
		
		BonusCalculator bonusCalculator = emp ->{
			emp.setSalary(generateBonus.get().get(emp.getDept()) + emp.getSalary());
			return emp;
		};

		// Q1. Sort the employees on basis of DateOfBirth Month wise using Comparator
		System.out.println("Sort the employees on basis of DateOfBirth Month wise");
		employees.stream().sorted(new Employee()).forEach(printEmp);
		System.out.println("--------------------------------------------");

		// Q2. Sort the employees on basis of DateOfBirth Month wise using Lambda
		/*
		 * System.out.println("Sort the employees on basis of DateOfBirth Month wise");
		 * employees.sort((e1,e2)->{
		 * Integer.compare(e1.getDateOfBirth().getMonthValue(),
		 * e2.getDateOfBirth().getMonthValue()) });
		 * System.out.println("--------------------------------------------");
		 */

		// Q3. Create 2 threads using lambda to print users and employees parallely
		System.out.println("Create 2 threads using lambda to print users and employees parallely");
		Thread empThread = new Thread(() -> {
			employees.forEach(printEmp);
		});
		Thread userThread = new Thread(() -> {
			users.forEach(printUser);
		});
		System.out.println("Employees:");
		empThread.start();
		System.out.println("Users:");
		userThread.start();
		System.out.println("--------------------------------------------");

		// Q4. UserNameGenerator using custom Functional Interface
		System.out.println("UserNameGenerator using custom Functional Interface");
		employees.stream()
				.map(emp -> new User(emp.getId(),
						userNameGenerator.userNameGenerate(emp.getFirstName(), emp.getLastname(),
								emp.getDateOfBirth().getYear(), emp.getId()),
						passwordSupplier.get()))
				.forEach(printUser);
		System.out.println("--------------------------------------------");
		
		// Q5. BonusGenerator using custom Functional Interface
		System.out.println("BonusGenerator using custom Functional Interface");
		employees.stream().map(emp->bonusCalculator.calculateBonus(emp)).forEach(printEmp);
		System.out.println("--------------------------------------------");

	}

}
