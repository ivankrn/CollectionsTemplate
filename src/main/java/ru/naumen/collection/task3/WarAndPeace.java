package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Итоговая сложность O(nlog(n)), где n - количество уникальных слов (ключей мапы) в тексте
    public static void main(String[] args) {
        // используем HashMap, чтобы ставить в соответствие слову количество его вхождений в текст
        Map<String, Integer> wordsCount = new HashMap<>();
        WordParser parser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        /* будет совершено порядка n вставок (или обновлений значений) в мапу, где n - количество слов в тексте
         (при этом вставка или получение значения в HashMap - константные операции) */
        parser.forEachWord(word -> wordsCount.put(word, wordsCount.getOrDefault(word, 0) + 1));
        /* сортируем записи в мапе по значению (по частоте слова), сложность O(nlog(n)), т.к. метод sorted использует
         для сортировки объектов TimSort (сложность O(nlog(n))) и получение значения записи в HashMap константная операция,
         после чего собираем ключи (слова) отсортированных записей мапы в список слов, чтобы можно было обратиться по
         позиции элемента и напечатать соответствующие элементы */
        List<String> wordsSortedByFreq = wordsCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .toList();
        int limit = 10;
        System.out.printf("ТОП %d наиболее используемых слов:\n", limit);
        for (int i = 0; i < limit; i++) {
            System.out.println(wordsSortedByFreq.get(wordsSortedByFreq.size() - i - 1));
        }
        System.out.printf("ТОП %d наименее используемых слов:\n", limit);
        for (int i = 0; i < limit; i++) {
            System.out.println(wordsSortedByFreq.get(i));
        }
    }

}

