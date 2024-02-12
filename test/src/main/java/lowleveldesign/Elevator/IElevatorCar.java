package lowleveldesign.Elevator;

/**
 * @author harish.kumar-mbp
 * createdOn 08/02/24
 */
public interface IElevatorCar {

    
    public void move(int floor);
    public void pressButton();
}
interface Floor{

    void pressButton(Button button);
}
interface Button {
    void pressButton();
}

interface InternalButton {
    void pressButton();
}

interface ExternalButton {
    void pressButton();
}
