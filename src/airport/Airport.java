package airport;

import utils.Observable;
import utils.Observer;

import java.util.ArrayList;
import java.util.List;

public class Airport implements Observer {

    private final String name;
    private final List<Plane> dockedPlanes;

    public Airport(String name) {
        this.name = name;
        this.dockedPlanes = new ArrayList<>();
    }

    public Plane scheduleFlight(Airport to) {
        Plane plane = getAvailablePlane();
        scheduleFlight(to,plane);
        return plane;
    }

    public void scheduleFlight(Airport to, Plane plane) {
        if(plane == null) {
            System.err.println("COULD'NT SCHEDULE FLIGHT :: currently no planes are docked on "+this);
            return;
        } else if(!dockedPlanes.contains(plane)) {
            System.err.println("COULD'NT SCHEDULE FLIGHT :: "+plane + " is not docked on "+this);
            return;
        } else if(plane.getScheduledAirport() != null && plane.getScheduledAirport() != to) {
            System.err.println("COULD'NT SCHEDULE FLIGHT :: "+plane+" is already scheduled to "+plane.getScheduledAirport());
            return;
        }
        if(to == this) {
            System.err.println("cant schedule a flight to the same airport");
            return;
        } else if(to == null) {
            System.err.println("destination airport unknown");
            return;
        }
        plane.schedule(to);
    }

    public List<Plane> getPlanesScheduledTo(Airport airport) {
        List<Plane> scheduledPlanes = new ArrayList<>();
        for(Plane plane : dockedPlanes)
            if(plane.getScheduledAirport() == airport)
                scheduledPlanes.add(plane);
        return scheduledPlanes;
    }

    public String toString() {
        return name;
    }

    @Override
    public void update(Observable observable) {
        Plane plane = (Plane)observable;
        if(plane.getDockedAirport() == this) {
            if(!dockedPlanes.contains(plane)) {
                dockedPlanes.add(plane);

                //landing procedures
                System.out.println(plane+ " is docked at "+this);
            }
        } else if(plane.getDockedAirport() == null) {
            dockedPlanes.remove(plane);

            //take off procedures
            System.out.println(plane + " left "+this);
        }

        if(plane.getScheduledAirport() == this) {
            //scheduled procedures
            System.out.println(plane + " is scheduled for "+this+ " from "+plane.getDockedAirport());
        }
    }

    private Plane getAvailablePlane() {
        for(Plane plane : dockedPlanes) {
            if(plane.getScheduledAirport() == null)
                return plane;
        }
        return null;
    }
}
