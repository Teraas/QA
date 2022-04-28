package DesignPatterns.Creational.AbstractFactory;

public interface AbstractFactory<T> {
    T create(String animalType) ;
}
