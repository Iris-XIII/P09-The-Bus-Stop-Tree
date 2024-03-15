///////////////////////////////////////////////////////////////////////////////
// Title: Bus Stop Tree
// Course: CS 300 Fall 2023
//
// Author: Iris Xu
// Email: jxu595@wisc.edu
// Lecturer: Mark Mansi

///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//

import java.util.Iterator;

/**
 * This class implement an iterator that only returns buses from another iterator that go to a
 * particular destination.
 */
public class BusFilteredIterator implements Iterator<Bus> {
  // The iterator we are filtering
  private Iterator<Bus> baseIterator;
  // The destination BusStop we are filtering by.
  private BusStop destination;
  // The next Bus to be returned, or null if there aren't any more.
  private Bus next;

  /**
   * Construct a new BusFilteredIterator that filters the given iterator to return only Bus-es that
   * stop at the given destination.
   * 
   * @param iterator    - iterator we are filtering
   * @param destination - destination BusStop we are filtering by
   */
  public BusFilteredIterator(Iterator<Bus> iterator, BusStop destination) {
    baseIterator = iterator;
    this.destination = destination;
    // call advanceToNext to initialize next
    advanceToNext();
  }

  /**
   * Private helper method that advances this iterator.
   */
  private void advanceToNext() {
    // assume next is null
    next = null;

    // find the next valid bus
    while (baseIterator.hasNext()) {
      Bus currentBus = baseIterator.next();

      // set next to current bus if the current bus goes to the destination
      if (currentBus.goesTo(destination)) {
        next = currentBus;
        break;
      }
    }
  }

  /**
   * Returns true if there is another Bus (that goes to the destination) in this iterator, or false
   * otherwise.
   */
  @Override
  public boolean hasNext() {
    return next != null;
  }

  /**
   * Returns the `next` bus and advances the iterator until the next bus it will return.
   */
  @Override
  public Bus next() {
    if (!hasNext()) {
      throw new IllegalStateException("There's no more desired buses, can't call next()");
    }

    // save the next bus as current
    Bus currentBus = next;
    // move to the next valid bus
    advanceToNext();
    return currentBus;
  }

}
