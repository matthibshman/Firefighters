package main.firefighters;

import main.api.CityNode;
import main.api.Firefighter;

public class FirefighterImpl implements Firefighter {

  private CityNode location;
  private int distance;

  public FirefighterImpl(CityNode location) {
    this.location = location;
    this.distance = 0;
  }

  @Override
  public CityNode getLocation() {
    return location;
  }

  @Override
  public int distanceTraveled() {
    return distance;
  }

  // HANDLE INVALID LOCATION???
  public void dispatch(CityNode location) {
    calculateDistance(this.location, location);
    this.location = location;
  }

  private void calculateDistance(CityNode fromLocation, CityNode toLocation) {
    distance += getDistance(fromLocation, toLocation);
  }

  private int getDistance(CityNode fromLocation, CityNode toLocation) {
    return Math.abs(fromLocation.getX() - toLocation.getX()) + Math.abs(fromLocation.getY() - toLocation.getY());
  }
}
