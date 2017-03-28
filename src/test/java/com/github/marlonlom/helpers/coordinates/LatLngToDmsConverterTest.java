/*******************************************************************************
 * Copyright 2017 Mjlopezm
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

package com.github.marlonlom.helpers.coordinates;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.github.marlonlom.helpers.coordinates.LatsLngs.Exception;

/**
 * The test Clase for LatLng To Dms Conversion.
 * 
 * @author Mjlopezm
 *
 */
@RunWith(JUnit4.class)
public class LatLngToDmsConverterTest {

  /**
   * Should fail converting latitude 93 negative.
   */
  @Test
  public final void shouldFailConvertingLatitude93negative() {
    try {
      final Double value = -93d;
      String converted = LatsLngs.with(value).asLatitude().toDms();
      Assert.fail("Latitude converted to DMS: " + converted);
    } catch (Exception e) {
      final String expectedMsg = "Latitude must not be greater then 90 degrees";
      Assert.assertEquals(expectedMsg, e.getMessage());
    }
  }

  /**
   * Should fail converting longitude 193.
   */
  @Test
  public final void shouldFailConvertingLongitude193() {
    try {
      final Double value = -193d;
      String converted = LatsLngs.with(value).asLongitude().toDms();
      Assert.fail("Longitude converted to DMS: " + converted);
    } catch (Exception e) {
      final String expectedMsg = "Longitude must not be greater then 180 degrees";
      Assert.assertEquals(expectedMsg, e.getMessage());
    }
  }

  /**
   * Should fail converting longitude as latitude.
   *
   * @throws Exception the exception
   */
  @Test
  public final void shouldFailConvertingLongitudeAsLatitude() throws Exception {
    final Double value = 42.136490d;
    final String expectedDms = "42°8'11\" E";
    final String convertedDms = LatsLngs.with(value).asLatitude().toDms();
    Assert.assertNotEquals(expectedDms, convertedDms);
  }

  /**
   * Should failusing wrong coordinates.
   */
  @Test
  public final void shouldFailusingWrongCoordinates() {
    final Double[] coordinates = {3d};
    try {
      final String convertedDms = LatsLngs.with(coordinates).toDms();
      Assert.fail("I converted dms using the list with one coordinate value??" + convertedDms);
    } catch (final LatsLngs.Exception exception) {
      Assert.assertEquals(1, exception.getCoordinatesCount());
    }
  }

  /**
   * Should success converting longitude 180 latitude 90.
   *
   * @throws Exception the exception
   */
  @Test
  public final void shouldSuccessConvertingLongitude180Latitude90() throws Exception {
    final Double[] coordinates = {180d, 90d};
    final String expectedDms = "90°0'0\" N, 180°0'0\" E";
    final String convertedDms = LatsLngs.with(coordinates).toDms();
    Assert.assertEquals(expectedDms, convertedDms);
  }

  /**
   * Should success converting longitude 180 negative latitude 90.
   *
   * @throws Exception the exception
   */
  @Test
  public final void shouldSuccessConvertingLongitude180NegativeLatitude90() throws Exception {
    final Double[] coordinates = {-180d, 90d};
    final String expectedDms = "90°0'0\" N, 180°0'0\" W";
    final String convertedDms = LatsLngs.with(coordinates).toDms();
    Assert.assertEquals(expectedDms, convertedDms);
  }

  /**
   * Should success converting sidney coordinates.
   *
   * @throws Exception the exception
   */
  @Test
  public final void shouldSuccessConvertingSidneyCoordinates() throws Exception {
    final Double[] coordinates = {151.209900d, -33.865143d};
    final String expectedDms = "33°51'54\" S, 151°12'35\" E";
    final String convertedDmd = LatsLngs.with(coordinates).toDms();
    Assert.assertEquals(expectedDms, convertedDmd);
  }

  /**
   * Should success converting single latitude.
   *
   * @throws Exception the exception
   */
  @Test
  public final void shouldSuccessConvertingSingleLatitude() throws Exception {
    final Double value = -34.206841d;
    final String expected = "34°12'24\" S";
    final String converted = LatsLngs.with(value).asLatitude().toDms();
    Assert.assertEquals(expected, converted);
  }

  /**
   * Should success converting single longitude.
   *
   * @throws Exception the exception
   */
  @Test
  public final void shouldSuccessConvertingSingleLongitude() throws Exception {
    final Double value = -65.961975d;
    final String expected = "65°57'43\" W";
    final String converted = LatsLngs.with(value).asLongitude().toDms();
    Assert.assertEquals(expected, converted);
  }

  /**
   * Should success converting single zero value.
   *
   * @throws Exception the exception
   */
  @Test
  public final void shouldSuccessConvertingSingleZeroValue() throws Exception {
    final Double value = 0.0d;
    final String expectedDms = "0°0'0\"";
    final String convertedDms = LatsLngs.with(value).asLatitude().toDms();
    Assert.assertEquals(expectedDms, convertedDms);
  }

  /**
   * Should success converting zero coordinates.
   *
   * @throws Exception the exception
   */
  @Test
  public final void shouldSuccessConvertingZeroCoordinates() throws Exception {
    final Double[] coordinates = {0d, 0d};
    final String expectedDms = "0°0'0\", 0°0'0\"";
    final String convertedDms = LatsLngs.with(coordinates).toDms();
    Assert.assertEquals(expectedDms, convertedDms);
  }

}
