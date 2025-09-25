package morganStanley_Day4;

import java.time.LocalDate;

public class Employees {
	
	private int id;
	private String firstName;
	private String lastname;
	private LocalDate dateOfBirth;
	private double salary;
	private String dept;
	
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

}
