package main.firefighters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import main.api.*;
import main.api.exceptions.NoFireFoundException;

public class FireDispatchImpl implements FireDispatch {

  private final City city;
  private final List<Firefighter> firefighters;


  public FireDispatchImpl(City city) {
    this.city = city;
    this.firefighters = new ArrayList<>();
  }

  @Override
  public void setFirefighters(int numFirefighters) {
    if (numFirefighters < 1) return;

    List<Firefighter> newFirefighters = Collections.nCopies(
      numFirefighters,
      new FirefighterImpl(city.getFireStation().getLocation())
    );

    firefighters.addAll(newFirefighters);
  }

  @Override
  public List<Firefighter> getFirefighters() {
    return firefighters;
  }

  @Override
  public void dispatchFirefighers(CityNode... burningBuildings) {
    if (firefighters.isEmpty()) return;

    Arrays.stream(burningBuildings)
            .map(city::getBuilding)
            .filter(Building::isBurning)
            .forEach(this::putOutFire);
  }

  private void putOutFire(Building building) {
    Firefighter firefighter = getFirefighter();
    firefighter.dispatch(building.getLocation());
    try {
      building.extinguishFire();
    } catch (NoFireFoundException e) {
      e.printStackTrace();
    }
  }

  private Firefighter getFirefighter() {
    return firefighters.get(0);
  }
}
