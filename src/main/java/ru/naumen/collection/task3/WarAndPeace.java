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

    // Итоговая сложность O(n), где n - количество слов в тексте
    public static void main(String[] args) {
        // используем HashMap, чтобы ставить в соответствие слову количество его вхождений в текст
        Map<String, Integer> wordsCount = new HashMap<>();
        WordParser parser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        /* будет совершено порядка n вставок (или обновлений значений) в мапу, где n - количество слов в тексте
         (при этом вставка или получение значения в HashMap - константные операции) */
        parser.forEachWord(word -> wordsCount.put(word, wordsCount.getOrDefault(word, 0) + 1));
        // создаем массив списков, где индексы - частоты слов, а элементы - список слов с данной частотой
        List<String>[] freq = new ArrayList[wordsCount.size() + 1];
        /* заполняем данный массив, будет совершено порядка n вставок в списки, где n - количество ключей (слов)
         в мапе; списки используем, чтобы можно было обращаться по индексу элемента */
        for (String word : wordsCount.keySet()) {
            int wordFreq = wordsCount.get(word);
            if (freq[wordFreq] == null) {
                freq[wordFreq] = new ArrayList<>();
            }
            freq[wordFreq].add(word);
        }
        int limit = 10;
        int printedWords = 0;
        System.out.printf("ТОП %d наиболее используемых слов:\n", limit);
        for (int i = freq.length - 1; i >= 0; i--) {
            if (freq[i] != null) {
                for (String word : freq[i]) {
                    if (printedWords >= limit) {
                        break;
                    }
                    System.out.println(word);
                    printedWords++;
                }
            }
        }
        printedWords = 0;
        System.out.printf("ТОП %d наименее используемых слов:\n", limit);
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != null) {
                for (String word : freq[i]) {
                    if (printedWords >= limit) {
                        break;
                    }
                    System.out.println(word);
                    printedWords++;
                }
            }
        }
    }

}

