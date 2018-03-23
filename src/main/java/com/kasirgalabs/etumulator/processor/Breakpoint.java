/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kasirgalabs.etumulator.processor;


/**
  * A breakpoint is similar to program counter (PC).
  *
  * Enabling a breakpoint on a given position x assures that
  * when program counter occurs to be x, program terminates
  *
  * Disabling the breakpoint assures that entire program runs
*/
public class Breakpoint {
  private int point;
  private static Breakpoint breakpoint;

  /**
    * Construct a Breakpoint with an infinite point
  */
  private Breakpoint() {
    reset();
  }


  /**
    * Singleton structure implemented.
  */
  public static Breakpoint getInstance() {
    if(breakpoint==null) {
      breakpoint = new Breakpoint();
    } return breakpoint;
  }


  /**
    * @param point The point value to set the breakpoint
  */
  public void setPoint(int point) {
    this.point = point;
  }


  /**
    * @return The point where breakpoint is.
  */
  public int getPoint() {
    return point;
  }

  /**
    * Sets the breakpoint to an infinite point
    * to execute the entire program.
  */
  public void reset() {
    point = Integer.MAX_VALUE;
  }
}
