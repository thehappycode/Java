package weatherstation.services;

public interface Observer {
  void update(float temp, float humidity, float pressure);
}
