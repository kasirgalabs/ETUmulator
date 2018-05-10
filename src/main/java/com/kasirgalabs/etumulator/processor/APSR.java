package com.kasirgalabs.etumulator.processor;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kasirgalabs.etumulator.util.BaseDispatcher;
import com.kasirgalabs.etumulator.util.Dispatcher;
import com.kasirgalabs.etumulator.util.Observable;
import com.kasirgalabs.etumulator.util.Observer;

/**
 * The Application Program Status Register (APSR) holds copies of the Arithmetic Logic Unit (ALU)
 * status flags. They are also known as the condition code flags. They are used to determine whether
 * conditional branches are executed or not. A branch with a condition code is only
 * executed if the condition code flags in the APSR meet the specified condition.
 * <p>
 * This class represents an observable object. After a state change on flags, all of it's observers
 * will be notified through default {@link BaseDispatcher} or the Dispatcher initialized during
 * construction.
 *
 * @author Görkem Mülayim
 * @see Dispatcher
 */
@Singleton
public class APSR implements Observable {
    private boolean negative;
    private boolean zero;
    private boolean carry;
    private boolean overflow;
    private final Dispatcher dispatcher;

    /**
     * Construct an APSR with {@link BaseDispatcher}.
     *
     * @see BaseDispatcher
     */
    public APSR() {
        this.dispatcher = new BaseDispatcher();
    }

    /**
     * Construct an APSR with the given dispatcher as parameter.
     *
     * @param dispatcher The dispatcher which will be used to notify observers.
     *
     * @see Dispatcher
     */
    @Inject
    public APSR(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * Adds an observer to the list of observers for this object.
     *
     * @param observer The observer to be deleted.
     *
     * @see Observer
     */
    @Override
    public void addObserver(Observer observer) {
        dispatcher.addObserver(observer);
    }

    /**
     * @return The negative.
     */
    public boolean isNegative() {
        return negative;
    }

    /**
     * @param negative The negative to set.
     */
    public void setNegative(boolean negative) {
        this.negative = negative;
        dispatcher.notifyObservers(APSR.class);
    }

    /**
     * @return The zero.
     */
    public boolean isZero() {
        return zero;
    }

    /**
     * @param zero The zero to set.
     */
    public void setZero(boolean zero) {
        this.zero = zero;
        dispatcher.notifyObservers(APSR.class);
    }

    /**
     * @return The carry.
     */
    public boolean isCarry() {
        return carry;
    }

    /**
     * @param carry The carry to set.
     */
    public void setCarry(boolean carry) {
        this.carry = carry;
        dispatcher.notifyObservers(APSR.class);
    }

    /**
     * @return The overflow.
     */
    public boolean isOverflow() {
        return overflow;
    }

    /**
     * @param overflow The overflow to set.
     */
    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
        dispatcher.notifyObservers(APSR.class);
    }

    /**
     * Updates negative and zero flag depending on the given value as parameter.
     *
     * @param value The value used to update negative and zero flag.
     *
     * @return The returns the given value.
     */
    public int updateNZ(int value) {
        negative = value < 0;
        zero = value == 0;
        dispatcher.notifyObservers(APSR.class);
        return value;
    }

    /**
     * Sets all the flags to false.
     */
    public void reset() {
        negative = false;
        zero = false;
        carry = false;
        overflow = false;
        dispatcher.notifyObservers(APSR.class);
    }
}
