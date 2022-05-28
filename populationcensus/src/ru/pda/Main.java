package ru.pda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.pda.Education.HIGHER;
import static ru.pda.Sex.MAN;
import static ru.pda.Sex.WOMAN;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> minors = persons.stream();
        System.out.println("Количество несовершеннолетних: " + minors.filter(p -> p.getAge() < 18)
                .limit(5).count());

        Stream<Person> conscripts = persons.stream();
        System.out.println("Список призывников");
        for (String man : conscripts
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27 && person.getSex().equals(MAN))
                .limit(5)
                .map(Person::getFamily).collect(Collectors.toList())) {
            System.out.println(man);
        }

        Stream<Person> workableList = persons.stream();
        System.out.println("Список работоспособных людей: ");
        for (String workable : workableList
                //.filter(p -> p.getAge() >= 18)
                .filter(p -> p.getAge() >= 18)
                .filter(p -> p.getAge() <=60 && p.getSex().equals(WOMAN) || p.getAge() <= 65 && p.getSex().equals(MAN))
                .filter(p -> p.getEducation().equals(HIGHER))
                .map(Person::getFamily)
                .limit(5)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList())) {
            System.out.println(workable);
        }


    }
}
