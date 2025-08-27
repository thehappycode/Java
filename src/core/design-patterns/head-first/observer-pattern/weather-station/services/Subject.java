package weatherstation.services;

import weatherstation.services.Observer;

public interface Subject{
  void registerObserver(Observer observer);
  void removeObserver(Observer observer);
  void notifyObservers();
}
