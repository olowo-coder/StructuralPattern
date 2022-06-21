package bridge.shapeOne;

import bridge.shapeOne.BlueCircle;
import bridge.shapeOne.Circle;
import bridge.shapeOne.RedSquare;
import bridge.shapeOne.Square;

public class BridgeDemo {
    public static void main(String[] args) {

        Circle circle = new BlueCircle();
        Square square = new RedSquare();

        circle.applyColor();
        square.applyColor();
    }
}
