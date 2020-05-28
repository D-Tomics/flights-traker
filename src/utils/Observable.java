package utils;

import java.util.ArrayList;

public class Observable {

    private final ArrayList<Observer> observers;
    public Observable() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        if(observer == null) return;
        synchronized (observers) { observers.add(observer); }
    }

    public void deleteObserver(Observer observer) {
        if(observer == null) return;
        synchronized (observers) { observers.remove(observer); }
    }

    public synchronized void notifyObservers() {
        for(Observer observer : observers)
            observer.update(this);
    }

}
