package morganStanley_Day4;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import morganStanley_Day3.Employee;

public class StreamEx {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<Employee> employees = Arrays.asList(
				new Employee(101, "Anuska1", "Sinha1", LocalDate.of(2023, 5, 12), 2500, "IT"),
				new Employee(102, "Anuska2", "Sinha2", LocalDate.of(1985, 3, 20), 1800, "HR"),
				new Employee(103, "Anuska3", "Sinha3", LocalDate.of(1992, 8, 15), 3000, "Finance"),
				new Employee(104, "Anuska4", "Sinha4", LocalDate.of(2023, 1, 10), 2200, "IT"),
				new Employee(105, "Rohan", "Sinha", LocalDate.of(2000, 1, 10), 1200, "IT"));

		Consumer<String> printFirstName = System.out::println;
		Consumer<Employee> printEmp = System.out::println;
		BiPredicate<Employee, String> empWithReqDeptUsingBP = (emp, dept) -> emp.getDept().equalsIgnoreCase(dept);

		// Q1. First Name of employees which joined in year 2023
		System.out.println("First Name of employees which joined in year 2023");
		employees.stream().filter(e -> e.getDateOfBirth().getYear() == 2023).map(Employee::getFirstName)
				.forEach(printFirstName);
		System.out.println("----------------------------------------");

		// Q2. Count, Min, Max, Sum, Average Salary in a Department
		System.out.println("Count, Min, Max, Sum, Average Salary in a Department");
		System.out.print("Enter Dept:");
		String dept = sc.next();
		DoubleSummaryStatistics dSS = employees.stream().filter(e -> empWithReqDeptUsingBP.test(e, dept))
				.mapToDouble(Employee::getSalary).summaryStatistics();
		System.out.println("No. of Employees in " + dept + ":" + dSS.getCount());
		System.out.println("Maximum Salary of Employees in " + dept + ":" + dSS.getMax());
		System.out.println("Minimum of Salary in " + dept + ":" + dSS.getMin());
		System.out.println("Sum of Salary in " + dept + ":" + dSS.getSum());
		System.out.println("Average of Salary in " + dept + ":" + dSS.getAverage());
		System.out.println("----------------------------------------");

		// Q3. Count, Min, Max, Sum, Average Salary in groupwise Department
		System.out.println("Count, Min, Max, Sum, Average Salary in groupwise Department");
		employees.stream()
				.collect(Collectors.groupingBy(Employee::getDept, Collectors.summarizingDouble(Employee::getSalary)))
				.forEach((department, stats) -> {
					System.out.println("Department: " + department);
					System.out.println("Count: " + stats.getCount());
					System.out.println("Min: " + stats.getMin());
					System.out.println("Max: " + stats.getMax());
					System.out.println("Sum: " + stats.getSum());
					System.out.println("Average: " + stats.getAverage());
					System.out.println();
				});
		System.out.println("----------------------------------------");

		// Q4. Sorted list of employees by firstName in all departments except HR
		// department
		System.out.println("Sorted list of employees by firstName in all departments except HR department");
		employees.stream().filter(e -> !e.getDept().equals("HR")).sorted(Comparator.comparing(Employee::getFirstName))
				.forEach(printEmp);
		System.out.println("----------------------------------------");

		// Q5. Increment salary of employees in particular department by 10%
		System.out.println("Increment salary of employees in particular department by 10%");
		System.out.print("Enter Dept:");
		String dept1 = sc.next();
		employees.stream().filter(e -> empWithReqDeptUsingBP.test(e, dept1))
				.forEach(e -> e.setSalary(e.getSalary() * 1.1));
		employees.forEach(printEmp);
		System.out.println("----------------------------------------");

		// Q6. Print 50 odd numbers after 100
		System.out.println("Print 50 odd numbers after 100");
		Stream.iterate(101, i -> i + 2).limit(50).forEach(System.out::println);
		System.out.println("----------------------------------------");

		// Q6. Comma seperated list of First names of employees order by dateOfbirth
		System.out.println("Comma seperated list of First names of employees order by dateOfbirth");
		String empList = employees.stream().sorted(Comparator.comparing(Employee::getDateOfBirth))
				.map(Employee::getFirstName).collect(Collectors.joining(", "));
		System.out.println(empList);
		System.out.println("----------------------------------------");

		// Q7. Highest paid employee in each department
		System.out.println("Highest paid employee in each department.");
		employees.stream().collect(
				Collectors.groupingBy(Employee::getDept, Collectors.maxBy(Comparator.comparing(Employee::getSalary))))
				.forEach((depart, emps) -> {
					System.out.println("Department: " + depart);
					System.out.print("Employees: [");
					System.out.print(emps.get());
					System.out.println("]");
					System.out.println();
				});
		System.out.println("----------------------------------------");

		// Q8. Second highest salary in company
		System.out.println("Second highest salary in company");
		Double secondHighestSal = employees.stream().map(Employee::getSalary).sorted(Comparator.reverseOrder()).skip(1)
				.distinct().findFirst().get();
		System.out.println(secondHighestSal);
		System.out.println("----------------------------------------");

		// Q9. Employees with first name starting “A”, sorted by salary desc
		System.out.println("Employees with first name starting “A”, sorted by salary desc");
		employees.stream().filter(e -> e.getFirstName().startsWith("A"))
				.sorted(Comparator.comparing(Employee::getSalary).reversed()).forEach(printEmp);
		System.out.println("----------------------------------------");

		// Q10. Group employees by dept & count
		System.out.println("Group employees by dept & count");
		employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.counting())).forEach((d, c) -> {
			System.out.println("Department: " + d);
			System.out.println("Count: " + c);
			System.out.println();
		});
		System.out.println("----------------------------------------");

		// Q11. Youngest and oldest employee
		System.out.println("Youngest and oldest employee");
		System.out.println("Youngest employee:");
		System.out.println(employees.stream().min(Comparator.comparing(Employee::getDateOfBirth)).get());
		System.out.println();
		System.out.println("Oldest employee:");
		System.out.println(employees.stream().max(Comparator.comparing(Employee::getDateOfBirth)).get());
		System.out.println("----------------------------------------");

		// Q12. Each department has list of employee first names.
		System.out.println("Each department has list of employee first names");
		employees.stream().collect(Collectors.groupingByConcurrent(Employee::getDept,
				Collectors.mapping(Employee::getFirstName, Collectors.toList()))).forEach((d1, firstNames) -> {
					System.out.println("Department: " + d1);
					System.out.println("Employees: " + firstNames);
					System.out.println();
				});
		System.out.println("----------------------------------------");

		sc.close();
	}

}
