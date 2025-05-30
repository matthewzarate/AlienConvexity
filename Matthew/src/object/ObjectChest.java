package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectChest extends SuperObject {
    public ObjectChest() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Chest_1.png"));
        }
        catch (IOException e) {e.printStackTrace();}
    }
}
