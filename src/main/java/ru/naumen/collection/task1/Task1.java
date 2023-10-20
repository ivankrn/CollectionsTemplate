package ru.naumen.collection.task1;

import java.util.*;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно написать утилитный метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 * <p>
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task1 {

    public static void main(String[] args) {
        Collection<User> firstCol = List.of(new User("1", "1@mail.com", new byte[]{0, 0, 0}));
        Collection<User> secondCol = List.of(new User("1", "1@mail.com", new byte[]{0, 0, 0}));
        List<User> duplicates = findDuplicates(firstCol, secondCol);
        duplicates.forEach(System.out::println);
    }

    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        /* Используем HashSet, так как он позволяет проверять наличие элемента за константное время
        Будет выполнено порядка n вставок в множество, где n - количество элементов в коллекции collB
        (при этом операция вставки тоже константная) */
        Set<User> collBSet = new HashSet<>(collB);
        List<User> duplicates = new ArrayList<>();
        // Будет совершено порядка m операций, где m - количество элементов в коллекции collA
        for (User user : collA) {
            if (collBSet.contains(user)) {
                duplicates.add(user);
            }
        }
        return duplicates;
        // Итоговая сложность O(n), где n - количество элементов в первой и второй коллекциях
    }
}
