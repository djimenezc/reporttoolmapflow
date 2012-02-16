package com.mapflow.geo.logging.types;

/**
 * Types of features we draw on the map
 * 
 * @author djimenez
 */
public enum FeaturesCategoryType {

  /**
   * (e.g Police stations, Fire stations)
   */
  POIs("POI"),
  /**
   * (AXA specific sites)
   */
  Comah("COMAH"),
  /**
   * Policies/Quotes (Common to all clients)
   */
  Policies("POLICY"),
  /**
   * (not live with any clients currently, but logging should be implemented for future use) a
   */
  Claims("CLAIM"),
  /**
   * (final e.g AXA Surveys)
   */
  CustomLayers("CUSTOM");

  private String value;

  private FeaturesCategoryType(final String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

}
