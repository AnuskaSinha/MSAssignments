package morganStanley_Day3;

import java.time.LocalDate;
import java.util.Comparator;

public class Employee implements Comparator<Employee>{

	private int id;
	private String firstName;
	private String lastname;
	private LocalDate dateOfBirth;
	private double salary;
	private String dept;

	public Employee(int id, String firstName, String lastname, LocalDate dateOfBirth, double salary, String dept) {
		this.id = id;
		this.firstName = firstName;
		this.lastname = lastname;
		this.dateOfBirth = dateOfBirth;
		this.salary = salary;
		this.dept = dept;
	}
	
	public Employee(String firstName, String lastname, LocalDate dateOfBirth, double salary, String dept) {
		this.firstName = firstName;
		this.lastname = lastname;
		this.dateOfBirth = dateOfBirth;
		this.salary = salary;
		this.dept = dept;
	}

	public Employee() {
	}

	@Override
	public String toString() {
		return "id=" + id + ", firstName=" + firstName + ", lastname=" + lastname + ", dateOfBirth="
				+ dateOfBirth + ", salary=" + salary + ", dept=" + dept;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public  String getLastname() {
		return lastname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public double getSalary() {
		return salary;
	}

	public String getDept() {
		return dept;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public int compare(Employee e1, Employee e2) {
		return e1.getDateOfBirth().getMonthValue() - e2.getDateOfBirth().getMonthValue();
	}

}
