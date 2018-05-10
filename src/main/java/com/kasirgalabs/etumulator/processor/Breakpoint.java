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
