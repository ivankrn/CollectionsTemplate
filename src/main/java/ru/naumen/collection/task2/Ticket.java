package ru.naumen.collection.task2;

import java.util.Objects;

/**
 * Билет
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Ticket {
    private long id;
    private String client;

    public Ticket(long id, String client) {
        this.id = id;
        this.client = client;
    }

    // используем только поле id в методах equals и hashcode так как id позволяет однозначно идентифицировать билет
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
