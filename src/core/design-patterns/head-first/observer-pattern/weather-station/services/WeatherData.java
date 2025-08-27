package weatherstation.services;

import java.util.ArrayList;

import weatherstation.services.Subject;
import weatherstation.services.Observer;

public class WeatherData implements Subject {
  
  private ArrayList<Observer> observers;

  private float temperature;
  private float humidity;
  private float pressure;
  
  public WeatherData(){
    observers = new ArrayList<Observer>();
  }

  public void registerObserver(Observer observer){
    observers.add(observer);
  }

  public void removeObserver(Observer observer){
    var idx = observers.indexOf(observer);
    if(idx >= 0){
      observers.remove(observer);
    }
  }

  public void notifyObservers(){
    for(Observer observer: observers){
      observer.update(temperature, humidity, pressure);
    }
  }

  public void measurementsChanged(){
    notifyObservers();
  }

  public void setMeasurements(
    float temperature,
    float humidity,
    float pressure
  ){
    this.temperature = temperature;
    this.humidity = humidity;
    this.pressure = pressure;
    measurementsChanged();
  }
}
