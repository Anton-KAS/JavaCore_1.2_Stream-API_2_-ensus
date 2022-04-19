import ru.netology.Education;
import ru.netology.Person;
import ru.netology.Sex;

import java.util.*;
import java.util.stream.Collectors;

import static ru.netology.Sex.MAN;

public class Main {

    public static void main(String[] args) {
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
        // Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        long underageCount = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();

        // Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> conscript = persons.stream()
                .filter(x -> x.getSex() == MAN)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        // Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образование в выборке
        // (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).
        List<Person> canWork = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getSex() == MAN? x.getAge() < 65: x.getAge() < 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
