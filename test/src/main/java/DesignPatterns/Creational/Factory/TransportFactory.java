package DesignPatterns.Creational.Factory;

public class TransportFactory {

    public Transport createTransport(String type) throws Exception {

        switch(type){
            case "Road":
                return new TruckTransport();
            case "Sea":
                return new ShipTransport();
            default:
                throw new Exception("Illigal transport");
        }
    }
}
