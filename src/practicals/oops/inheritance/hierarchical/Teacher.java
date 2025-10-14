package practicals.oops.inheritance.hierarchical;

public class Teacher extends Person{
	String subject;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void displayTeacher() {
        displayName();
        System.out.println("Teaches: " + subject);
    }
}
