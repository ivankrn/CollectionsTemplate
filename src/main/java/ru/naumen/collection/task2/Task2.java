package ru.naumen.collection.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Дано:
 * <pre>
 * public class Ticket {
 *     private long id;
 *     private String client;
 *     …
 * }</pre>
 * <p>Разработать программу для бармена в холле огромного концертного зала.
 * Зрители в кассе покупают билет (класс Ticket), на котором указан идентификатор билета (id) и имя зрителя.
 * При этом, есть возможность докупить наборы разных товаров (комбо-обед): нет товаров, напитки, еда и напитки.
 * Доп. услуги оформляются через интернет и привязываются к билету, но хранятся отдельно от билета
 * (нельзя добавить товары в класс Ticket).</p>
 * <p>Бармен сканирует билет и получает объект Ticket. По этому объекту нужно уметь
 * находить необходимые товары по номеру билета. И делать это нужно очень быстро,
 * ведь нужно как можно быстрее всех накормить.</p>
 * <p>
 * См. {@link Ticket}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2 {

    /* используем мапу, чтобы ставить в соответствие билету приобретенные услуги (так как доп. услуги привязаны к
    билету, но хранятся отдельно от него) */
    private final Map<Ticket, Collection<Goods>> ticketToGoods = new HashMap<>();

    /* Итоговая сложность нахождения необходимых товаров по билету O(1), так как получение элемента и его значения из
     HashMap константная операция */
    public static void main(String[] args) {
        Task2 task = new Task2();
        Ticket kolyaTicket = task.buyTicket("Kolya");
        task.buyGoods(kolyaTicket, new Drinks("Water"));
        Ticket olyaTicket = task.buyTicket("Olya");
        Ticket dimaTicket = task.buyTicket("Dima");
        task.buyGoods(dimaTicket, new Food("Cookies"));
        task.buyGoods(dimaTicket, new Drinks("Cola"));
        System.out.println("Товары Коли:");
        task.findGoodsByTicket(kolyaTicket).forEach(System.out::println);
        System.out.println("Товары Оли:");
        task.findGoodsByTicket(olyaTicket).forEach(System.out::println);
        System.out.println("Товары Димы:");
        task.findGoodsByTicket(dimaTicket).forEach(System.out::println);
    }

    // константная операция, т.к. добавление элемента в HashMap тоже константная операция
    public Ticket buyTicket(String clientName) {
        Ticket newTicket = new Ticket(ticketToGoods.size(), clientName);
        ticketToGoods.put(newTicket, new ArrayList<>());
        return newTicket;
    }

    // константная операция, т.к. получение элемента и его значения в HashMap тоже константная операция
    public void buyGoods(Ticket ticket, Goods goods) {
        ticketToGoods.get(ticket).add(goods);
    }

    // константная операция, т.к. получение элемента и его значения в HashMap тоже константная операция
    public Collection<Goods> findGoodsByTicket(Ticket ticket) {
        if (ticketToGoods.containsKey(ticket)) {
            return ticketToGoods.get(ticket);
        }
        return null;
    }
}
