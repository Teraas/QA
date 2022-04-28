package DesignPatterns.Creational.Singleton;

/**
 * Its creational pattern which allows only 1 instance of the class.
 * Should make default constructor private to prenet usage of "new" to create objects.
 * Create a static method that creates an object by invoking private constrcutor and saves the object to a static reference.
 */
public class Instance {
    private static Instance instance;

    public static Instance getInstance(){
        if(instance == null)
            instance = new Instance();
        return instance;
    }
}
