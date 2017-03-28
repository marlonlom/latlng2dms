/*******************************************************************************
 * Copyright 2017 Mjlopezm
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
/*
 * 
 */

package com.github.marlonlom.helpers.coordinates;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The fluent builder utility Class LatsLngs.
 *
 * @author Mjlopezm
 *
 */
public final class LatsLngs {

  /**
   * The Class Builder.
   *
   * @author Mjlopezm
   */
  public static final class Builder implements IWithValues, IWithSingleValue {

    /**
     * The coordinates.
     */
    private List<Double> coordinates;

    /**
     * The single value.
     */
    private Double singleValue;

    /**
     * The single value as latitude.
     */
    private int singleValueAs;

    /**
     * The single value enabled.
     */
    private boolean singleValueEnabled;

    /**
     * Instantiates a new builder.
     */
    private Builder() {
      super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.github.marlonlom.helpers.coordinates.LatsLngs.IWithSingleValue# asLatitude()
     */
    @Override
    public IWithValues asLatitude() {
      this.singleValueAs = Constants.LATITUDE_INDEX;
      return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.github.marlonlom.helpers.coordinates.LatsLngs.IWithSingleValue# asLongitude()
     */
    @Override
    public IWithValues asLongitude() {
      this.singleValueAs = Constants.LONGITUDE_INDEX;
      return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.github.marlonlom.helpers.coordinates.LatsLngs.IWithValues# toDms()
     */
    @Override
    public String toDms() throws Exception {
      final StringBuilder builder = new StringBuilder();
      if (this.singleValueEnabled) {
        final String dmsSingle = LatsLngs.processSingleValue(this.singleValue, this.singleValueAs);
        builder.append(dmsSingle);
      } else {
        builder.append(processCoordinates(this.coordinates));
      }
      return builder.toString().trim();
    }

    /**
     * Prepare conversion using single coordinate value.
     *
     * @param value coordinate value
     * @return the builder implementation with single value
     */
    private IWithSingleValue with(Double value) {
      this.singleValue = value;
      this.singleValueEnabled = true;
      return this;
    }

    /**
     * Prepare conversion using coordinates.
     *
     * @param coordinates coordinates values (longitude, latitude)
     * @return the builder implementation with coordinates
     */
    private IWithValues with(final Double[] coordinates) {
      this.coordinates = Arrays.asList(coordinates);
      this.singleValueEnabled = false;
      return this;
    }

  }

  /**
   * The Class Exception.
   *
   * @author Mjlopezm
   */
  public static final class Exception extends java.lang.Exception {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 549354576840147795L;

    /**
     * The coordinates count.
     */
    private final int coordinatesCount;

    /**
     * Instantiates a new exception.
     *
     * @param message the message
     */
    public Exception(final String message) {
      super(message);
      this.coordinatesCount = Constants.ONE_NEGATIVE;
    }

    /**
     * Instantiates a new exception.
     *
     * @param message the message
     * @param coordinatesCount the coordinates count
     */
    public Exception(final String message, final int coordinatesCount) {
      super(message);
      this.coordinatesCount = coordinatesCount;
    }

    /**
     * Gets the coordinates count.
     *
     * @return the coordinates count
     */
    public int getCoordinatesCount() {
      return coordinatesCount;
    }

  }

  /**
   * The Interface IWithSingleValue.
   */
  public interface IWithSingleValue {

    /**
     * As latitude.
     *
     * @return the i with values
     */
    IWithValues asLatitude();

    /**
     * As longitude.
     *
     * @return the i with values
     */
    IWithValues asLongitude();

  }

  /**
   * The Interface IWithValues.
   */
  public interface IWithValues {

    /**
     * To dms.
     *
     * @return dms text
     * @throws Exception the exception
     */
    String toDms() throws Exception;
  }

  /**
   * The Constant MESSAGES.
   */
  private static final ResourceBundle MESSAGES =
      ResourceBundle.getBundle("com.github.marlonlom.helpers.coordinates.messages");

  /**
   * Gets the latitude direction.
   *
   * @param latitude a latitude coordinate value
   * @return the latitude direction
   * @throws Exception if an error happen
   */
  private static String getLatitudeDirection(double latitude) throws Exception {
    if (Math.abs(latitude) > 90d) {
      throw new LatsLngs.Exception(MESSAGES.getString("errors.latitude.greater"));
    }
    return latitude > Constants.ZERO ? Constants.DIRECTIONS[Constants.ZERO]
        : latitude < Constants.ZERO ? Constants.DIRECTIONS[Constants.ONE] : Constants.EMPTY;
  }

  /**
   * Gets the longitude direction.
   *
   * @param longitude a longitude coordinate value
   * @return the longitude direction
   * @throws Exception if an error happen
   */
  private static String getLongitudeDirection(double longitude) throws Exception {
    if (Math.abs(longitude) > 180d) {
      throw new LatsLngs.Exception(MESSAGES.getString("errors.longitude.greater"));
    }
    return longitude > Constants.ZERO ? Constants.DIRECTIONS[Constants.TWO]
        : longitude < Constants.ZERO ? Constants.DIRECTIONS[Constants.THREE] : Constants.EMPTY;
  }

  /**
   * Process coordinates as decimal values, in terms of latitude and longitude , respectively.
   *
   * @param coordinates coordinates data (longitude at index 0,laitude at index 1)
   * @return coordinates represented as DMS format
   * @throws Exception if an error happen
   */
  private static String processCoordinates(final List<Double> coordinates)
      throws LatsLngs.Exception {
    StringBuilder dmsResult = new StringBuilder(Constants.EMPTY);
    if (coordinates.size() == Constants.TWO) {
      final String dmsFormat = "%s %s";

      final Double latitudeCoordinate = coordinates.get(Constants.LATITUDE_INDEX);
      final String latitudeDms = DecimalToDms.format(latitudeCoordinate);
      final String latitudeDirection = getLatitudeDirection(latitudeCoordinate);
      dmsResult.append(String.format(dmsFormat, latitudeDms, latitudeDirection).trim());

      dmsResult.append(",").append(Constants.SPACE);

      final Double longitudeCooordinate = coordinates.get(Constants.LONGITUDE_INDEX);
      final String longitudeDms = DecimalToDms.format(longitudeCooordinate);
      final String longitudeDirection = getLongitudeDirection(longitudeCooordinate);
      dmsResult.append(String.format(dmsFormat, longitudeDms, longitudeDirection).trim());
    } else {
      throw new LatsLngs.Exception(MESSAGES.getString("errors.coordinates.count.invalid"),
          coordinates.size());
    }
    return dmsResult.toString();
  }

  /**
   * Process single coordinate value.
   *
   * @param coordinate the single value
   * @param isEnabled the single value enabled
   * @param asLatitude the single value as latitude
   * @return the string
   * @throws Exception if an error happen
   */
  private static String processSingleValue(final Double coordinate, final Integer conversionMode)
      throws Exception {
    final StringBuilder dmsResult = new StringBuilder(Constants.EMPTY);
    dmsResult.append(DecimalToDms.format(coordinate)).append(Constants.SPACE);
    String dmsDirection = conversionMode.equals(Constants.LATITUDE_INDEX)
        ? getLatitudeDirection(coordinate) : Constants.EMPTY;
    dmsDirection = (dmsDirection.equalsIgnoreCase(Constants.EMPTY)
        && conversionMode.equals(Constants.LONGITUDE_INDEX)) ? getLongitudeDirection(coordinate)
            : dmsDirection;
    dmsResult.append(dmsDirection).trimToSize();
    return dmsResult.toString();
  }

  /**
   * With single coordinate value.
   *
   * @param value coordinate value
   * @return builder implementation
   */
  public static Builder with(final Double value) {
    return (Builder) new Builder().with(value);
  }

  /**
   * With cooordinates.
   *
   * @param coordinates coordinates (longitude, latitude)
   * @return builder implementation
   */
  public static Builder with(final Double[] coordinates) {
    return (Builder) new Builder().with(coordinates);
  }
}
