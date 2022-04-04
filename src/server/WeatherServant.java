package server;

import org.omg.CORBA.ORB;
import weather.TempGeneratorPOA;

import java.util.Random;

public class WeatherServant extends TempGeneratorPOA {
    public static final int MIN = -25;
    public static final int MAX = 50;
    private ORB orb;

    @Override
    public double generate(String s) {
        Random random = new Random();
        return random.nextInt(MAX - MIN) + MIN;
    }

    public void setOrb(ORB orb) {
        this.orb = orb;
    }
}
