package server;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import weather.TempGenerator;
import weather.TempGeneratorHelper;

public class Main {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPoa.the_POAManager().activate();

            WeatherServant servant = new WeatherServant();
            servant.setOrb(orb);

            org.omg.CORBA.Object ref = rootPoa.servant_to_reference(servant);
            TempGenerator href = TempGeneratorHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent[] path = ncRef.to_name("ABC");
            ncRef.rebind(path, href);

            while (true) {
                orb.run();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
