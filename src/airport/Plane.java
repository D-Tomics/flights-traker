package airport;

import utils.Observable;

public class Plane extends Observable {

    private final String name;
    private final int capacity;

    private Airport dockedAirport;
    private Airport scheduledAirport;

    public Plane(String name, int capacity, Airport dockedAirport) {
        this.name = name;
        this.capacity = capacity;
        this.dockedAirport = dockedAirport;
        this.addObserver(dockedAirport);
        this.notifyObservers();
    }

    public void fly() {
        if(scheduledAirport == null || dockedAirport == null) return;
        Airport temp = dockedAirport;
        dockedAirport = null;
        this.notifyObservers();
        this.deleteObserver(temp);
    }

    public void land() {
        if(scheduledAirport == null || dockedAirport != null) return;
        dockedAirport = scheduledAirport;
        scheduledAirport = null;
        this.addObserver(dockedAirport);
        this.notifyObservers();
    }

    public void schedule(Airport airport) {
        scheduledAirport = airport;

        this.addObserver(scheduledAirport);
        this.notifyObservers();
        this.deleteObserver(scheduledAirport);
    }

    public int getCapacity() { return capacity; }
    public String getName() { return name; }
    public String getLocation() { return dockedAirport == null ? "flying" : dockedAirport.toString(); }

    protected Airport getDockedAirport() { return dockedAirport; }
    protected Airport getScheduledAirport() { return scheduledAirport; }

    public String toString() {
        return getName();
    }

}
