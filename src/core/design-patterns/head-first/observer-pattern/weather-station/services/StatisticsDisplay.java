package weatherstation.services;

import weatherstation.services.Subject;
import weatherstation.services.Observer;
import weatherstation.services.DisplayElement;

public class StatisticsDisplay implements Observer, DisplayElement {
  private float temperature;
  private float humidity;
  private float pressure;
  private Subject weatherData;

  public StatisticsDisplay(Subject weatherData){
    this.weatherData = weatherData;
    weatherData.registerObserver(this);
  }

  public void update(
      float temperature,
      float humidity,
      float pressure
  ){
    this.temperature = temperature;
    this.humidity = humidity;
    this.pressure = pressure;
    display();
  }
  public void display(){
    System.out.println("-> Statistic: { " +
      "temperature: " + temperature +
      ", humidity: " + humidity +
      ", pressure: " + pressure +
    "}");
  }

}
