package client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import weather.TempGenerator;
import weather.TempGeneratorHelper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            TempGenerator generator = TempGeneratorHelper.narrow(ncRef.resolve_str("ABC"));

            Scanner scanner = new Scanner(System.in);
            String s;

            while (true) {
                System.out.println("Enter day of week, enter to exit");
                s = scanner.nextLine();

                if (s.isEmpty()) {
                    break;
                }
                double generate = generator.generate(s);
                System.out.printf("The weather for %s is %.2f", s, generate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
