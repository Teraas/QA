package lowleveldesign;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author harish.kumar-mbp
 * createdOn 23/01/24
 */
public class ParkingSystem {

    public void addFloor(int carCount, int busCount, int motorCount){
        Floor floor = new Floor(carCount, busCount, motorCount);
        Floors.onboardFloor(floor);
    }
    public void showParkingMetric(){
        System.out.println(Floors.getFloorMetrics());

    }
}

class Floor{
    int floorId;
    int level;
    int maxCarSpots;
    int maxBusSpots;
    int maxMotorSpots;
    List<ParkSpot> cars;
    List<ParkSpot> buses;
    List<ParkSpot> motors;



    public Floor(int maxCarSpots, int maxBusSpots, int maxMotorSpots) {
        this.maxCarSpots = maxCarSpots;
        this.maxBusSpots = maxBusSpots;
        this.maxMotorSpots = maxMotorSpots;
    }
}

class Floors {
    static int totalFloors;
    static List<Floor> floors;
    static Map<Integer, Floor> floorMap;

    public static void onboardFloor(Floor floor){
        floors.add(floor);
        floorMap.put(++totalFloors, floor);

    }
    public static Map getFloorMetrics(){
        Map<Integer, Map<ParkingSize, Integer>> floorsMetrics = new HashMap<>();
        int count = 1;
        for(Floor f : floors){
            Map<ParkingSize, Integer> floorMetrics = new HashMap<>();
            floorMetrics.put(ParkingSize.Bus,f.buses.size());
            floorMetrics.put(ParkingSize.Car,f.cars.size());
            floorMetrics.put(ParkingSize.Motor,f.motors.size());
            floorsMetrics.put(count, floorMetrics);
            count++;
        }
        return floorsMetrics;

    }
}
class ParkSpot{
    int id;
    int physicalLocId;
    boolean isFilled;
    ParkingSize type;
}
enum ParkingSize{
    Car, Bus, Motor
}
