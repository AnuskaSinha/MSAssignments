package morganStanley_Day3;

@FunctionalInterface
interface UserNameGenerator {

	String userNameGenerate(String firstName, String lastName, int yearOfBirth, int id);

}
