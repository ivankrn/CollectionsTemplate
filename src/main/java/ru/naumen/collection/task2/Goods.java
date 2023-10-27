package ru.naumen.collection.task2;

public abstract class Goods {

    private String name;

    public Goods(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                '}';
    }
}
