package morganStanley_Day3;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class FunctionalInterfaceEx {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<Employee> employees = Arrays.asList(
				new Employee(101, "Anuska1", "Sinha1", LocalDate.of(1990, 5, 12), 2500, "IT"),
				new Employee(102, "Anuska2", "Sinha2", LocalDate.of(1985, 3, 20), 1800, "HR"),
				new Employee(103, "Anuska3", "Sinha3", LocalDate.of(1992, 8, 15), 3000, "Finance"),
				new Employee(104, "Anuska4", "Sinha4", LocalDate.of(1995, 1, 10), 2200, "IT"));

		Predicate<Employee> empWithReqSal = emp -> emp.getSalary() > 2000;
		Predicate<Employee> empWithReqDept = emp -> emp.getDept().equalsIgnoreCase("IT");
		Predicate<Employee> bonusAndSalMoreThan3000 = emp -> emp.getSalary() > 3000;

		Consumer<Employee> printEmp = System.out::println;
		Consumer<User> printUser = System.out::println;

		BiPredicate<Employee, Double> empWithReqSalUsingBP = (emp, sal) -> emp.getSalary() > sal;
		BiPredicate<Employee, String> empWithReqDeptUsingBP = (emp, dept) -> emp.getDept().equalsIgnoreCase(dept);
		BiPredicate<Employee, Integer> empWithReqIdUsingBP = (emp, id) -> emp.getId() == id;

		Supplier<String> passwordSupplier = () -> {
			String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			Random random = new Random();
			return random.ints(16, 0, chars.length()).mapToObj(i -> String.valueOf(chars.charAt(i)))
					.collect(Collectors.joining());
		};

		Supplier<Integer> generateId = () -> {
			Employee emp = employees.stream().reduce((e1, e2) -> e2).get();
			return new AtomicInteger(emp.getId()).incrementAndGet();
		};

		Function<Employee, User> empToUser = emp -> {
			String userName = emp.getFirstName() + emp.getLastname() + emp.getDateOfBirth().getYear() + emp.getId();
			return new User(emp.getId(), userName, passwordSupplier.get());
		};

		Function<Employee, User> newEmpToUser = emp -> {
			String userName = emp.getFirstName() + emp.getLastname() + emp.getDateOfBirth().getYear()
					+ generateId.get();
			return new User(generateId.get(), userName, passwordSupplier.get());
		};

		BiFunction<Employee, Double, Employee> increaseEmp = (emp, sal) -> {
			emp.setSalary(emp.getSalary() + (emp.getSalary() * sal / 100));
			return emp;
		};

		Function<Double, UnaryOperator<Employee>> salIncrement = per -> emp -> {
			emp.setSalary(emp.getSalary() + (emp.getSalary() * per / 100));
			return emp;
		};

		BinaryOperator<Employee> maxSalaryEmployee = (e1, e2) -> e1.getSalary() >= e2.getSalary() ? e1 : e2;

		Supplier<Map<String, Double>> generateBonus = () -> {
			Map<String, Double> bonusMap = new HashMap<>();
			bonusMap.put("IT", 1000.0);
			bonusMap.put("HR", 800.0);
			bonusMap.put("Finance", 1200.0);
			return bonusMap;
		};

		Function<Employee, Employee> addBonusToSalary = emp -> {
			emp.setSalary(generateBonus.get().get(emp.getDept()) + emp.getSalary());
			return emp;
		};

		// Q1. Employees whose salary is more than 2000
		System.out.println("Employees whose salary is more than 2000");
		employees.stream().filter(empWithReqSal).forEach(printEmp);
		System.out.println("--------------------------------------------");

		// Q2. Employees whose salary is more than 2000 and Dept is IT
		System.out.println("Employees whose salary is more than 2000 and Dept is IT");
		employees.stream().filter(empWithReqSal.and(empWithReqDept)).forEach(printEmp);
		System.out.println("--------------------------------------------");

		// Q3. Employees whose salary is more than 2000 using BiPredicate
		System.out.println("Employees whose salary is more than 2000 using BiPredicate");
		System.out.print("Enter Salary:");
		String sal = sc.nextLine();
		employees.stream().filter(e -> empWithReqSalUsingBP.test(e, Double.valueOf(sal))).forEach(printEmp);
		System.out.println("--------------------------------------------");

		// Q4. Employees whose salary is more than 2000 and Dept is IT using BiPredicate
		System.out.println("Employees whose salary is more than 2000 using BiPredicate");
		System.out.print("Enter Salary:");
		String sal1 = sc.nextLine();
		System.out.print("Enter Dept:");
		String dept = sc.nextLine();
		employees.stream().filter(e -> empWithReqSalUsingBP.test(e, Double.valueOf(sal1)))
				.filter(e -> empWithReqDeptUsingBP.test(e, dept)).forEach(printEmp);
		System.out.println("--------------------------------------------");

		// Q5. Employee to User
		System.out.println("Generating User from Employee");
		employees.stream().map(empToUser).forEach(printUser);
		System.out.println("--------------------------------------------");

		// Q6. Employee Input, generate id and convert to user
		System.out.println("Employee Input, generate id and convert to user");
		System.out.println("Enter Employee Details");
		System.out.print("Enter FirstName:");
		String firstName = sc.nextLine();
		System.out.print("Enter LastName:");
		String lastName = sc.nextLine();
		System.out.print("Enter Date of Birth:");
		int birthDate = sc.nextInt();
		System.out.print("Enter Month of Birth:");
		int birthMonth = sc.nextInt();
		System.out.print("Enter Year of Birth:");
		int birthYear = sc.nextInt();
		LocalDate dateOfBirth = LocalDate.of(birthYear, birthMonth, birthDate);
		System.out.print("Enter Salary:");
		Double salary = sc.nextDouble();
		System.out.print("Enter Dept:");
		String department = sc.next();
		Employee emp = new Employee(firstName, lastName, dateOfBirth, salary, department);
		List<Employee> newEmp = Arrays.asList(emp);
		newEmp.stream().map(newEmpToUser).forEach(printUser);
		System.out.println("--------------------------------------------");

		// Q7. Employee and Salary increment percentage Input and print updated Employee
		System.out.println("Employee and Salary increment percentage Input and print updated Employee");
		System.out.println("Enter Employee Id");
		int id = sc.nextInt();
		System.out.println("Enter Percenatge of Salary Increment");
		Double incSal = sc.nextDouble();
		employees.stream().filter(e -> empWithReqIdUsingBP.test(e, id)).findFirst().ifPresent(empl -> {
			Employee e = increaseEmp.apply(empl, incSal);
			System.out.println(e);
		});
		System.out.println("--------------------------------------------");

		// Q8. Unary Operator to perform increment of salary
		System.out.println("Unary Operator to perform increment of salary");
		System.out.println("Enter Employee Id");
		int empid = sc.nextInt();
		System.out.println("Enter Percenatge of Salary Increment");
		Double incSalary = sc.nextDouble();
		UnaryOperator<Employee> empHike = salIncrement.apply(incSalary);
		employees.stream().filter(e -> empWithReqIdUsingBP.test(e, empid)).map(empHike).forEach(printEmp);
		System.out.println("--------------------------------------------");

		// Q9. Find employee with highest salary
		System.out.println("Find employee with highest salary");
		Employee maxSalaryEmp = employees.stream().reduce(maxSalaryEmployee).get();
		System.out.println(maxSalaryEmp);
		System.out.println("--------------------------------------------");

		// Q10. Generate bonus according to dept and then add to the salary, then check
		// whose salary id more than 3000
		System.out.println(
				"Generate bonus according to dept and then add to the salary, then check whose salary id more than 3000");
		employees.stream().map(addBonusToSalary).filter(bonusAndSalMoreThan3000).forEach(printEmp);
		System.out.println("--------------------------------------------");

		// Q11. Show salary with more than and less than 2000
		System.out.println("Show employees with more than and less than 2000");
		Map<Boolean, List<Employee>> empSalaryDivision = employees.stream()
				.collect(Collectors.partitioningBy(empWithReqSal));
		System.out.println("Show employees with more than 2000");
		empSalaryDivision.get(true).forEach(printEmp);
		System.out.println("Show employees with less than 2000");
		empSalaryDivision.get(false).forEach(printEmp);

		sc.close();
	}

}
