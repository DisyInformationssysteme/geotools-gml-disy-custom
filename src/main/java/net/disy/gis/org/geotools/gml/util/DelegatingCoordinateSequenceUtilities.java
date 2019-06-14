//Copyright (c) 2012 by Disy Informationssysteme GmbH
package net.disy.gis.org.geotools.gml.util;

import org.locationtech.jts.geom.CoordinateSequence;

// NOT_PUBLISHED
public class DelegatingCoordinateSequenceUtilities {

  private static final String DEFAULT_IMPLEMENTATION = "de.disy.gis.gml.org.geotools.adapter.CoordinateSequenceUtilitiesAdapter";

  private static ICoordinateSequenceUtilities coordinateSequenceUtilities;

  public static boolean isCounterClockwise(CoordinateSequence coordinateSequence) {
    return getCoordinateSequenceUtilities().isCounterClockwise(coordinateSequence);
  }

  public static CoordinateSequence invert(CoordinateSequence coordinateSequence) {
    return getCoordinateSequenceUtilities().invert(coordinateSequence);
  }

  public static void setCoordinateSequenceUtilities(
      ICoordinateSequenceUtilities coordinateSequenceUtilities) {
    DelegatingCoordinateSequenceUtilities.coordinateSequenceUtilities = coordinateSequenceUtilities;
  }

  private static ICoordinateSequenceUtilities getCoordinateSequenceUtilities() {
    if (coordinateSequenceUtilities == null) {
      try {
        final Class<?> clazz = Class.forName(DEFAULT_IMPLEMENTATION);
        coordinateSequenceUtilities = (ICoordinateSequenceUtilities) clazz.getDeclaredConstructor().newInstance();
      }
      catch (Exception e) {
        throw new RuntimeException(
            "Can't create default implementation  " + DEFAULT_IMPLEMENTATION,
            e);
      }
    }
    return coordinateSequenceUtilities;
  }

}