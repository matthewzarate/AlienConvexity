package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectDoor extends SuperObject {
    public ObjectDoor() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/16x16_Door.png"));
        }
        catch (IOException e) {e.printStackTrace();}
        collision = true; //making it a solid


    }
}
