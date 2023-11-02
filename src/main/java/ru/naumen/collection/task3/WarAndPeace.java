package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.*;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    // Итоговая сложность O(n), где n - количество уникальных слов (ключей мапы) в тексте
    public static void main(String[] args) {
        /* используем LinkedHashMap, чтобы ставить в соответствие слову количество его вхождений в текст и быстро
        итерироваться по ней */
        Map<String, Integer> wordsCount = new LinkedHashMap<>();
        WordParser parser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        /* будет совершено порядка n вставок (или обновлений значений) в мапу, где n - количество слов в тексте
        (при этом вставка или получение значения в LinkedHashMap - константные операции) */
        parser.forEachWord(word -> wordsCount.put(word, wordsCount.getOrDefault(word, 0) + 1));
        int limit = 10;
        /* создаем две очереди с приоритетами, одну под хранение наиболее используемых слов, другую под хранение наименее
        используемых */
        PriorityQueue<Map.Entry<String, Integer>> mostFrequentlyUsed =
                new PriorityQueue<>(limit + 1, Comparator.comparingInt(Map.Entry::getValue));
        PriorityQueue<Map.Entry<String, Integer>> leastFrequentlyUsed =
                new PriorityQueue<>(limit + 1, (w1, w2) -> w2.getValue() - w1.getValue());
        /* далее для каждого элемента из wordsCount добавляем его в обе очереди с приоритетами, при превышении лимита
        по размеру удаляем наименьший по приоритету элемент из очереди
        Будет совершено порядка nlog(m) операций (где m - лимит для PriorityQueue), т.к. на каждой итерации по wordsCount
        (n итераций) мы совершаем две операции вставки в PriorityQueue и при превышении лимита также две операции
        извлечения наименьшего по приоритету элемента
        Так как операции вставки и извлечения из PriorityQueue имеют сложность O(log(n)), то итоговая сложность O(n),
        поскольку в задаче размер PriorityQueue будет ограничен 11 и log(11) - константа */
        for (Map.Entry<String, Integer> wordCount : wordsCount.entrySet()) {
            mostFrequentlyUsed.add(wordCount);
            if (mostFrequentlyUsed.size() > limit) {
                mostFrequentlyUsed.poll();
            }
            leastFrequentlyUsed.add(wordCount);
            if (leastFrequentlyUsed.size() > limit) {
                leastFrequentlyUsed.poll();
            }
        }
        System.out.printf("ТОП %d наиболее используемых слов:\n", limit);
        while (!mostFrequentlyUsed.isEmpty()) {
            System.out.println(mostFrequentlyUsed.poll().getKey());
        }
        System.out.printf("ТОП %d наименее используемых слов:\n", limit);
        while (!leastFrequentlyUsed.isEmpty()) {
            System.out.println(leastFrequentlyUsed.poll().getKey());
        }
    }

}

