package com.cacoveanu.makeitso.javsa8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by scacoveanu on 25/9/2015.
 */
public class Tutorial {

    private class Person {
        String firstName;
        String lastName;

        public Person(String firstName) {
            this.firstName = firstName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }

    @Test
    public void testForEach() {
        List<Person> persons = Arrays.asList(new Person("Joe"), new Person("Jim"), new Person("Albert"));
        persons.forEach(p -> p.setLastName("X"));

        System.out.println(persons);

        Stream<Person> stream = persons.stream();
        //Stream<Person> stream = persons.parallelStream();

        List<Person> filtered = persons.stream()
                .filter(p -> p.getFirstName().startsWith("Jo"))
                .collect(Collectors.toList());

        System.out.println(filtered);

        System.out.println(persons.stream()
                .filter(p -> p.getFirstName().startsWith("A")).map(
                        new Function<Person, String>() {
                            @Override
                            public String apply(Person person) {
                                return person.getFirstName();
                            }
                        }
                ).collect(Collectors.toSet()));

        Set fset = persons.stream()
                .filter(p -> p.getFirstName().startsWith("J"))
                .map(Person::getFirstName)
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println(fset);

        Set fset2 = persons.stream()
                .parallel()
                .filter(p -> p.getFirstName().startsWith("J"))
                .sequential()
                .map(Person::getFirstName)
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println(fset2);
    }
}
