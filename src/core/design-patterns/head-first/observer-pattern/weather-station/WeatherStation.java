// TODO: Compiler:  javac -d release *.java services/*.java
// TODO: Run:       java -cp release weatherstation.WeatherStation

package weatherstation;

import weatherstation.services.WeatherData;
import weatherstation.services.CurrentConditionsDisplay;
import weatherstation.services.StatisticsDisplay;

public class WeatherStation{
  
  public static void main(String[] args){
    System.out.println("-> Welcome to WeatherStation program");
    System.out.println();

    WeatherData weatherData = new WeatherData();

    // TODO: CurrentConditionsDisplay
    CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);

    // TODO: StatisticsDisplay
    StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
    
    weatherData.setMeasurements(80, 65, 30.4f);
    weatherData.setMeasurements(82, 70, 29.2f);
    weatherData.setMeasurements(78, 90, 29.2f);
  }
}
