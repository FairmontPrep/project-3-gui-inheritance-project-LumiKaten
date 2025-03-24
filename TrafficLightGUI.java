import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

// Abstract parent class: Traffic Light
abstract class TrafficLight extends JPanel {
    private BufferedImage trafficLightImage;
    private static BufferedImage baseImage;

    static {
        try {
            baseImage = ImageIO.read(new File("traffic_light_base.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TrafficLight() {
        loadImage();
    }

    protected abstract void loadImage();

    protected void setTrafficLightImage(String filePath) {
        try {
            trafficLightImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected BufferedImage getTrafficLightImage() {
        return trafficLightImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (baseImage != null) {
            g.drawImage(baseImage, 0, 0, this);
        }
        if (trafficLightImage != null) {
            g.drawImage(trafficLightImage, 0, 0, this);
        }
        
        // Draw the text message in black
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(getMessage(), 20, 450);
    }

    public abstract String getMessage();
}

// Child class: Red Light
class RedLight extends TrafficLight {
    public RedLight() {
        super();
    }

    @Override
    protected void loadImage() {
        setTrafficLightImage("red_light.png");
    }

    @Override
    public String getMessage() {
        return "The traffic light says: Red - Stop!";
    }
}

// Child class: Yellow Light
class YellowLight extends TrafficLight {
    public YellowLight() {
        super();
    }

    @Override
    protected void loadImage() {
        setTrafficLightImage("yellow_light.png");
    }

    @Override
    public String getMessage() {
        return "The traffic light says: Yellow - Slow down!";
    }
}

// Child class: Purple Light (50% chance replacing Yellow)
class PurpleLight extends TrafficLight {
    public PurpleLight() {
        super();
    }

    @Override
    protected void loadImage() {
        setTrafficLightImage("purple_light.png");
    }

    @Override
    public String getMessage() {
        return "The traffic light says: Purple? Wha- what the?";
    }
}

// Child class: Green Light
class GreenLight extends TrafficLight {
    public GreenLight() {
        super();
    }

    @Override
    protected void loadImage() {
        setTrafficLightImage("green_light.png");
    }

    @Override
    public String getMessage() {
        return "The traffic light says: Green - Go!";
    }
}

// Main class to display GUI
public class TrafficLightGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Traffic Light GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 500);

            Random random = new Random();
            TrafficLight trafficLight;

            int randomLight = random.nextInt(4);
            if (randomLight == 0) {
                trafficLight = new RedLight();
            } else if (randomLight == 1) {
                trafficLight = (random.nextBoolean()) ? new PurpleLight() : new YellowLight();
            } else {
                trafficLight = new GreenLight();
            }

            System.out.println(trafficLight.getMessage());

            frame.add(trafficLight);
            frame.setVisible(true);
        });
    }
}
