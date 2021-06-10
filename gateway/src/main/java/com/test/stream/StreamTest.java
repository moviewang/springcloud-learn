package com.test.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: movie
 * @Date: 2021/6/8 11:51
 */
public class StreamTest {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> stream2 = Stream.iterate(0, x -> x + 3).limit(4);
        stream2.forEach(System.out::println);
        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream3.forEach(System.out::println);

        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900, "female", "New York"));

        List<Person> pp = personList.stream()
                                    .filter(p -> p.getSalary() > 8000)
                                    .collect(Collectors.toList());

        Optional<Person> maxSalary = pp.stream()
                                       .max(Comparator.comparingInt(Person::getSalary));
        if (maxSalary.isPresent()) {
            System.out.println(maxSalary.get());
        }

        Optional<Integer> reduce = personList.stream()
                                             .map(p -> p.getSalary())
                                             .reduce(Integer::sum);
        DoubleSummaryStatistics stat = personList.stream()
                                                 .collect(Collectors.summarizingDouble(Person::getSalary));
        List<Integer> list = Arrays.asList(4, 6, 1, 3, 8);
        List<Integer> reverseOrder = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        reverseOrder.forEach(System.out::println);

    }
}
