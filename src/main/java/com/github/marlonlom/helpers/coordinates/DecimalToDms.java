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

package com.github.marlonlom.helpers.coordinates;

/**
 * The Class DecimalToDms.
 *
 * @author Mjlopezm
 */
public final class DecimalToDms {

  /**
   * Formats coordinate value in D°M′S″ format.
   *
   * @param coord coordinate in decimal format
   * @return coordinate in D°M′S″ format
   */
  public static String format(final double coord) {

    double coordinate = coord;

    double mod = coordinate % Constants.ONE;
    int intPart = (int) coordinate;

    final String degrees = String.valueOf(intPart);

    coordinate = mod * Constants.SIXTY;
    mod = coordinate % Constants.ONE;
    intPart = (int) coordinate;
    if (intPart < Constants.ZERO) {
      intPart *= Constants.ONE_NEGATIVE;
    }

    final String minutes = String.valueOf(intPart);

    coordinate = mod * Constants.SIXTY;
    intPart = (int) coordinate;
    if (intPart < Constants.ZERO) {
      intPart *= Constants.ONE_NEGATIVE;
    }

    String seconds = String.valueOf(intPart);
    return String.format(Constants.DMS_FORMAT, Math.abs(Integer.parseInt(degrees)), minutes,
        seconds);
  }

}
