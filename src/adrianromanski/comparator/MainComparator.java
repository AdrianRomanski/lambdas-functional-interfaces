package adrianromanski.comparator;

import java.util.function.Function;

public class MainComparator {

    public static void main(String[] args) {

        Comparator<Person> cmpAge = (p1, p2) -> p2.getAge() - p1.getAge(); // Comparing age
        Comparator<Person> cmpFirstName = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()); // Comparing firstName
        Comparator<Person> cmpLastName = (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()); // Comparing lastName

        Function<Person, Integer> f1 = Person::getAge; // Comparing age
        Function<Person, String> f2 = Person::getLastName; // Comparing lastName
        Function<Person, String> f3 = Person::getFirstName; // Comparing firstName

        Comparator<Person> cmpPersonAge = Comparator.comparing(Person::getAge); // Comparing age
        Comparator<Person> cmpPersonLastName = Comparator.comparing(Person::getLastName); // Comparing lastName

        Comparator<Person> cmp = Comparator.comparing(Person::getLastName) // First it compare lastName
                                            .thenComparing(Person::getFirstName) // Then firstName
                                            .thenComparing(Person::getAge); // Lastly its comparing age
    }
}
