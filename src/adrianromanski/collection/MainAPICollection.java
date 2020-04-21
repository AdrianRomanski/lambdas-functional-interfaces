package adrianromanski.collection;

import adrianromanski.comparator.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MainAPICollection {
    public static void main(String[] args) {
        Person p1 = new Person("Alice", "Cooper", 23);
        Person p2 = new Person("Brain", "Stark", 56);
        Person p3 = new Person("Chelsea", "London", 46);
        Person p4 = new Person("David", "Copperfield", 28);
        Person p5 = new Person("Erica", "Jonas", 37);
        Person p6 = new Person("Francisco", "Forte", 18);

        List<Person> people = new ArrayList<>(Arrays.asList(p1,p2,p3,p4,p5,p6));

        people.removeIf(person -> person.getAge() < 30);

        people.replaceAll(person -> new Person(person.getFirstName().toUpperCase(), person.getLastName().toLowerCase(), person.getAge()));

        people.sort(Comparator.comparing(Person::getAge).reversed());

        people.forEach(System.out::println);
    }
}
