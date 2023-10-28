import java.util.*;
import java.util.stream.Collectors;

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
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберете номер функции: ");
            System.out.println("1.Количество несовершеннолетних !");
            System.out.println("2.Фамилии призывников !");
            System.out.println("3.Список мужчин с высшим образованием !");
            System.out.println("4.Список женщин с высшим образованием !");
            System.out.println("Введите end для выхода из программы !");
            String choiceOsUser = scanner.nextLine();
            if (choiceOsUser.equals("end")) {
                System.out.println("До свидания !");
                break;
            }
            switch (choiceOsUser) {
                case "1":
                    long count = persons.stream()
                            .filter(x -> x.getAge() < 18)
                            .count();
                    System.out.println(count + " количество несовершеннолетних людей");
                    System.out.println();
                    break;
                case "2":
                    persons.stream()
                            .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                            .filter(x -> x.getSex() == Sex.MAN)
                            .map(x -> x.getFamily()).collect(Collectors.toList())
                            .forEach(x -> System.out.println("Призывник " + x));
                    System.out.println();
                    break;
                case "3":
                    persons.stream()
                            .filter(x -> x.getSex() == Sex.MAN && x.getAge() >= 18 && x.getAge() <= 65)
                            .filter(x -> x.getEducation() == Education.HIGHER)
                            .sorted(Comparator.comparing(Person::getFamily))
                            .collect(Collectors.toList())
                            .forEach(System.out::println);
                    System.out.println();
                    break;
                case "4":
                    persons.stream()
                            .filter(x -> x.getSex() == Sex.WOMAN && x.getAge() >= 18 && x.getAge() <= 60)
                            .filter(x -> x.getEducation() == Education.HIGHER)
                            .sorted(Comparator.comparing(Person::getFamily))
                            .collect(Collectors.toList())
                            .forEach(System.out::println);
                    System.out.println();
                    break;

                default:
                    System.out.println("Введен неверный номер функции !");
                    System.out.println();
            }
        }
    }
}