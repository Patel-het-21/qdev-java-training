package practicals.oops.inheritance.hybrid;

//person class
public class Person {
	
	//variables
	protected String name;
	protected int age;
	
	//pera constructor
	public Person(String name,int age) {
		this.name = name;
		this.age = age;
	}
	
	//print details of person
	public void showPersonDetails() {
		System.out.println("Name : " + name);
		System.out.println("Age : " + age);
	}
}
