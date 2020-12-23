package lesson5;

public class Person {
    String name,position,email,phoneNumber;
    int salary,age;

    public Person(String name, String position, String email, String phoneNumber, int salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public Person(String name, String email, String phoneNumber, int age) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void printInfo() {
        System.out.println(name + ", возраст " + age);
        System.out.println(position + " - " + salary + "р.");
        System.out.println("тел. " + phoneNumber + ", email - " + email);
    }
}
