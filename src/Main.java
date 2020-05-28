import airport.Airport;
import airport.Plane;

public class Main {

    public static void main(String[] args) {

        Airport airportABC = new Airport("ABC");
        Airport airportXYZ = new Airport("XYZ");

        Plane a0 = new Plane("a0",440, airportABC);
        Plane b0 = new Plane("b0",440, airportABC);
        Plane c0 = new Plane("c0",440, airportABC);
        Plane d0 = new Plane("d0",440, airportABC);
        Plane e0 = new Plane("e0",440, airportABC);

        Plane a1 = new Plane("a1",440, airportXYZ);
        Plane b1 = new Plane("b1",440, airportXYZ);
        Plane c1 = new Plane("c1",440, airportXYZ);
        Plane d1 = new Plane("d1",440, airportXYZ);
        Plane e1 = new Plane("e1",440, airportXYZ);

        airportABC.scheduleFlight(airportXYZ);
        airportABC.scheduleFlight(airportXYZ);
        airportABC.scheduleFlight(airportXYZ);
        airportABC.scheduleFlight(airportXYZ);
        airportABC.scheduleFlight(airportXYZ);

        System.out.println("flights scheduled to XYZ from ABC :: "+airportABC.getPlanesScheduledTo(airportXYZ));

        System.out.println("\nflight "+a0+" is now at "+a0.getLocation()+"\n");
        a0.fly();
        System.out.println("flight "+a0+" is now  "+a0.getLocation()+"\n");
        a0.land();
        System.out.println("flight "+a0+" is now at "+a0.getLocation()+"\n");

        /*b0.fly();
        c0.fly();
        e0.fly();
        b0.land();
        c0.land();
        e0.land();*/

        airportXYZ.scheduleFlight(airportABC,a0);
        airportXYZ.scheduleFlight(airportABC,a1);

        System.out.println("flights scheduled for abc from xyz "+airportXYZ.getPlanesScheduledTo(airportABC));

        a0.fly();
        a1.fly();
        a0.land();
        a1.land();
    }

}
