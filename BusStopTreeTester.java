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

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * This class implement all the testers
 */
public class BusStopTreeTester {

  /**
   * Tests that compareTo returns the correct value when comparing a bus with a different arrival.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToDifferentArrivalTime() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"06:00", "08:00", "10:00", "12:00", "14:00"};
    String[] stopTimes2 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // create two same routes with different arrival time
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == 1;
    boolean correctComparison2 = bus2.compareTo(bus1) == -1;

    // test passes if both comparisons return the correct value
    return correctComparison1 && correctComparison2;
  }

  /**
   * For two buses with the same arrival time but different routes, test that compareTo returns the
   * correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeDifferentRoute() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"06:00", "08:00", "10:00", "12:00", "14:00"};
    // create two different routes with same arrival time
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("B", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == -1;
    boolean correctComparison2 = bus2.compareTo(bus1) == 1;

    // test passes if both comparisons return the correct value
    return correctComparison1 && correctComparison2;
  }

  /**
   * For two buses with the same arrival time and route name, but different directions, test that
   * compareTo returns the correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeSameRouteDifferentDirection() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"06:00", "08:00", "10:00", "12:00", "14:00"};
    // create two same route with same arrival time but different direction
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == -1;
    boolean correctComparison2 = bus2.compareTo(bus1) == 1;

    // test passes if both comparisons return the correct value
    return correctComparison1 && correctComparison2;
  }

  /**
   * Tests that compareTo returns the correct value (0) when comparing a bus with the same arrival
   * time, route name, and direction.
   * 
   * @return true if the test passes, false otherwise.
   */
  private static boolean testBusCompareToSameBus() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == 0; // should return 0
    boolean correctComparison2 = bus2.compareTo(bus1) == 0; // should return 0

    // test passes if both comparisons return 0
    return correctComparison1 && correctComparison2;
  }

  /**
   * Tests that isValidBST returns true for an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTEmpty() {
    // construct a empty tree
    BusStopTree tree = new BusStopTree(1);
    return BusStopTree.isValidBST(tree.getRoot());
  }

  /**
   * Tests that isValidBST returns false for an invalid BST.
   * 
   * Should use a tree with depth > 2. Make sure to include a case where the left subtree contains a
   * node that is greater than the right subtree. (See the example in the spec for more details.)
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTInvalid() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    // create different stop times
    String[] stopTimes1 = {"04:00", "06:00", "08:00", "10:00", "12:00"};
    String[] stopTimes2 = {"05:00", "08:00", "09:00", "11:00", "14:00"};
    String[] stopTimes3 = {"07:00", "11:00", "12:00", "05:00", "07:00"};
    String[] stopTimes4 = {"11:00", "05:00", "07:00", "09:00", "12:00"};
    String[] stopTimes5 = {"11:00", "05:00", "07:00", "09:00", "12:00"};
    // create new routes
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes5);
    // create new buses
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    Bus bus5 = new Bus(BusStop.getStop(2), route5);
    // construct a invalid BST
    Node<Bus> tree = new Node<>(bus1, new Node<>(bus4, null, new Node<>(bus3, null, null)),
        new Node<>(bus2, new Node<>(bus5, null, null), null));
    // return true if isValidBST is false
    return !BusStopTree.isValidBST(tree);
  }

  /**
   * Tests that isValidBST returns true for a valid BST.
   * 
   * Should use a tree with depth > 2.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTValid() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    // create different stop times
    String[] stopTimes1 = {"04:00", "06:00", "08:00", "10:00", "12:00"};
    String[] stopTimes2 = {"05:00", "08:00", "09:00", "11:00", "14:00"};
    String[] stopTimes3 = {"07:00", "11:00", "12:00", "05:00", "07:00"};
    String[] stopTimes4 = {"11:00", "05:00", "07:00", "09:00", "12:00"};
    String[] stopTimes5 = {"11:00", "05:00", "07:00", "09:00", "12:00"};
    // create new routes
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes5);
    // create new buses
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    Bus bus5 = new Bus(BusStop.getStop(2), route5);
    // construct a valid BST
    Node<Bus> tree = new Node<>(bus1, new Node<>(bus4, new Node<>(bus5, null, null), null),
        new Node<>(bus2, null, new Node<>(bus3, null, null)));
    // return true if isValidBST is true
    return BusStopTree.isValidBST(tree);
  }

  /**
   * Tests that addBus correctly adds a bus to an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusEmpty() {
    // construct a new bus
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"04:00", "06:00", "08:00", "10:00", "12:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus = new Bus(BusStop.getStop(1), route1);
    // construct an empty tree
    BusStopTree tree = new BusStopTree(1);
    // add bus to empty tree
    return tree.addBus(bus);
  }

  /**
   * Tests that addBus correctly adds a bus to a non-empty BST.
   * 
   * Each time you add a bus, make sure that 1) addBus() returns true, 2) the BST is still valid, 3)
   * the BST size has been incremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBus() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    // create different stop times
    String[] stopTimes1 = {"04:00", "06:00", "08:00", "10:00", "12:00"};
    String[] stopTimes2 = {"05:00", "08:00", "09:00", "11:00", "14:00"};
    String[] stopTimes3 = {"07:00", "11:00", "12:00", "05:00", "07:00"};
    // create new routes
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    // create new buses
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);

    // create a new BST
    BusStopTree tree = new BusStopTree(2);
    // add bus1
    boolean check1 = false;
    if (tree.addBus(bus1) && BusStopTree.isValidBST(tree.getRoot()) && tree.size() == 1) {
      check1 = true;
    }
    // add bus2
    boolean check2 = false;
    if (tree.addBus(bus2) && BusStopTree.isValidBST(tree.getRoot()) && tree.size() == 2) {
      check2 = true;
    }
    // add bus3
    boolean check3 = false;
    if (tree.addBus(bus3) && BusStopTree.isValidBST(tree.getRoot()) && tree.size() == 3) {
      check3 = true;
    }
    return check1 && check2 && check3;
  }

  /**
   * Tests that addBus returns false when adding a duplicate bus. The BST should not be modified
   * (same size).
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusDuplicate() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    // create different stop times
    String[] stopTimes1 = {"04:00", "06:00", "08:00", "10:00", "12:00"};
    String[] stopTimes2 = {"05:00", "08:00", "09:00", "11:00", "14:00"};
    String[] stopTimes3 = {"07:00", "11:00", "12:00", "05:00", "07:00"};
    // create new routes
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    // create new buses
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);

    // create a new BST
    BusStopTree tree = new BusStopTree(2);
    // add bus1
    tree.addBus(bus1);
    // add bus2
    tree.addBus(bus2);

    // add a duplicate bus2
    return !tree.addBus(bus2) || BusStopTree.isValidBST(tree.getRoot()) || tree.size() == 2;
  }

  /**
   * Tests that contains returns true when the BST contains the Bus, and false otherwise.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContains() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    // create different stop times
    String[] stopTimes1 = {"04:00", "06:00", "08:00", "10:00", "12:00"};
    String[] stopTimes2 = {"05:00", "08:00", "09:00", "11:00", "14:00"};
    String[] stopTimes3 = {"07:00", "11:00", "12:00", "05:00", "07:00"};
    // create new routes
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    // create new buses
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);

    // create a new BST
    BusStopTree tree = new BusStopTree(2);
    // add buses
    tree.addBus(bus1);
    tree.addBus(bus2);

    return !tree.contains(bus3) && tree.contains(bus2);
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is the node passed in as the root node parameter.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRoot() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    // create different stop times
    String[] stopTimes1 = {"04:00", "12:00", "08:00", "10:00", "12:00"};
    String[] stopTimes2 = {"05:00", "08:00", "09:00", "11:00", "14:00"};
    String[] stopTimes3 = {"07:00", "13:00", "12:00", "05:00", "07:00"};
    // create new routes
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    // create new buses
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);

    // create a new BST
    BusStopTree tree = new BusStopTree(2);
    // add buses
    tree.addBus(bus1);
    tree.addBus(bus2);
    tree.addBus(bus3);

    // create a current time
    LocalTime currentTime = LocalTime.of(9, 30);
    // call get next bus to get the next bus after
    Bus next = tree.getNextBus(currentTime);
    // check if the result equal to root
    return next.compareTo(bus1) == 0;
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the left subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterLeftSubtree() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    // create different stop times
    String[] stopTimes1 = {"04:00", "12:00", "08:00", "10:00", "12:00"};
    String[] stopTimes2 = {"05:00", "08:00", "09:00", "11:00", "14:00"};
    String[] stopTimes3 = {"07:00", "13:00", "12:00", "05:00", "07:00"};
    // create new routes
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    // create new buses
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);

    // create a new BST
    BusStopTree tree = new BusStopTree(1);
    // add buses
    tree.addBus(bus2);
    tree.addBus(bus1);
    tree.addBus(bus3);
    // create a current time
    LocalTime currentTime = LocalTime.of(3, 30);
    // call get next bus to get the next bus after
    Bus next = tree.getNextBus(currentTime);
    // check if the result equal to root
    return next.compareTo(bus1) == 0;
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the right subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRightSubtree() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    // create different stop times
    String[] stopTimes1 = {"04:00", "12:00", "08:00", "10:00", "12:00"};
    String[] stopTimes2 = {"05:00", "08:00", "09:00", "11:00", "14:00"};
    String[] stopTimes3 = {"07:00", "13:00", "12:00", "05:00", "07:00"};
    // create new routes
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    // create new buses
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);

    // create a new BST
    BusStopTree tree = new BusStopTree(1);
    // add buses
    tree.addBus(bus2);
    tree.addBus(bus1);
    tree.addBus(bus3);
    // create a current time
    LocalTime currentTime = LocalTime.of(6, 30);
    // call get next bus to get the next bus after
    Bus next = tree.getNextBus(currentTime);
    // check if the result equal to root
    return next.compareTo(bus3) == 0;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a LEAF NODE. Make sure that 1) removeBus
   * returns the removed Bus, 2) the BST is still valid, 3) the BST size has been decremented.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusLeaf() {
    // TODO: OPTIONAL (but highly encouraged)
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with ONE child. Make sure
   * that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has been
   * decremented.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeOneChild() {
    // TODO: OPTIONAL (but highly encouraged)
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with TWO children. Make
   * sure that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has
   * been decremented.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeTwoChildren() {
    // TODO: OPTIONAL (but highly encouraged)
    return false;
  }

  /**
   * Tests that removeBus returns false when removing a Bus that is not in the BST. The BST should
   * not be modified.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeNotInBST() {
    // TODO: OPTIONAL (but highly encouraged)
    return false;
  }

  /**
   * Tests the creation of an BusFilteredIterator where NONE of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToEmpty() {
    int[] stopIds1 = {1, 2, 3};
    // create different stop times
    String[] stopTimes1 = {"04:00", "10:00", "08:00", "10:00", "12:00"};
    String[] stopTimes2 = {"05:00", "08:00", "09:00", "11:00", "14:00"};
    String[] stopTimes3 = {"07:00", "12:00", "12:00", "05:00", "07:00"};
    // create new routes
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    // create new buses
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    // create a ArrayList
    ArrayList<Bus> list = new ArrayList<Bus>();
    // add buses to the list
    list.add(bus1);
    list.add(bus2);
    list.add(bus3);
    // create new iterator
    BusFilteredIterator filter = new BusFilteredIterator(list.iterator(), BusStop.getStop(1));

    // Check whether all the buses go the desired destination
    int count = 0;
    while (filter.hasNext()) {
      Bus next = filter.next();
      if (next.compareTo(bus1) == 0 && next.compareTo(bus2) == 0 && next.compareTo(bus3) == 0) {
        return false;
      }
      // increment count
      count++;
    }
    return count == 0;
  }

  /**
   * Tests the creation of an BusFilteredIterator where SOME of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */

  public static boolean testGetNextBusesToSome() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    // create different stop times
    String[] stopTimes1 = {"04:00", "07:00", "08:00", "10:00", "12:00"};
    String[] stopTimes2 = {"05:00", "08:00", "09:00", "11:00", "14:00"};
    String[] stopTimes3 = {"07:00", "12:00", "12:00", "14:00", "16:00"};
    // create new routes
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    // create new buses
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    // create a ArrayList
    ArrayList<Bus> list = new ArrayList<Bus>();
    // add buses to the list
    list.add(bus1);
    list.add(bus2);
    list.add(bus3);
    // create new iterator
    BusFilteredIterator filter = new BusFilteredIterator(list.iterator(), BusStop.getStop(1));

    // Check whether all the buses go the desired destination
    int count = 0;
    while (filter.hasNext()) {
      Bus next = filter.next();
      if (next.compareTo(bus1) == 0 && next.compareTo(bus3) == 0) {
        return false;
      }
      if (count == 0 && next.compareTo(bus2) != 0) {
        return false;
      }
      // increment count
      count++;
    }
    return count == 1;
  }

  /**
   * Tests the creation of an BusFilteredIterator where ALL of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToAll() {
    int[] stopIds1 = {1, 2, 3};
    // create different stop times
    String[] stopTimes1 = {"04:00", "08:00", "08:00", "10:00", "12:00"};
    String[] stopTimes2 = {"05:00", "10:00", "09:00", "11:00", "14:00"};
    String[] stopTimes3 = {"07:00", "12:00", "12:00", "05:00", "07:00"};
    // create new routes
    BusRoute route1 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    // create new buses
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    // create a ArrayList
    ArrayList<Bus> list = new ArrayList<Bus>();
    // add buses to the list
    list.add(bus1);
    list.add(bus2);
    list.add(bus3);
    // create new iterator
    BusFilteredIterator filter = new BusFilteredIterator(list.iterator(), BusStop.getStop(2));

    // Check whether all the buses go the desired destination
    int count = 0;
    while (filter.hasNext()) {
      Bus next = filter.next();
      if (count == 0 && next.compareTo(bus1) != 0) {
        return false;
      }
      if (count == 1 && next.compareTo(bus2) != 0) {
        return false;
      }
      if (count == 2 && next.compareTo(bus3) != 0) {
        return false;
      }
      // increment count
      count++;
    }
    return count == list.size();
  }

  public static void main(String[] args) {
    // Populate BusStop with dummy data. This only has to be done once.
    BusStop.createDummyStops();

    System.out
        .println("testBusCompareToDifferentArrivalTime: " + testBusCompareToDifferentArrivalTime());
    System.out.println("testBusCompareToSameArrivalTimeDifferentRoute: "
        + testBusCompareToSameArrivalTimeDifferentRoute());
    System.out.println("testBusCompareToSameArrivalTimeSameRouteDifferentDirection: "
        + testBusCompareToSameArrivalTimeSameRouteDifferentDirection());
    System.out.println("testBusCompareToSameBus: " + testBusCompareToSameBus());
    System.out.println("testIsValidBSTEmpty: " + testIsValidBSTEmpty());
    System.out.println("testIsValidBSTInvalid: " + testIsValidBSTInvalid());
    System.out.println("testIsValidBSTValid: " + testIsValidBSTValid());
    System.out.println("testAddBusEmpty: " + testAddBusEmpty());
    System.out.println("testAddBus: " + testAddBus());
    System.out.println("testAddBusDuplicate: " + testAddBusDuplicate());
    System.out.println("testRemoveBusLeaf: " + testRemoveBusLeaf());
    System.out.println("testRemoveBusNodeOneChild: " + testRemoveBusNodeOneChild());
    System.out.println("testRemoveBusNodeTwoChildren: " + testRemoveBusNodeTwoChildren());
    System.out.println("testRemoveBusNodeNotInBST: " + testRemoveBusNodeNotInBST());
    System.out.println("testContains: " + testContains());
    System.out.println("testGetFirstNodeAfterRoot: " + testGetFirstNodeAfterRoot());
    System.out.println("testGetFirstNodeAfterLeftSubtree: " + testGetFirstNodeAfterLeftSubtree());
    System.out.println("testGetFirstNodeAfterRightSubtree: " + testGetFirstNodeAfterRightSubtree());
    System.out.println("testGetNextBusesToEmpty: " + testGetNextBusesToEmpty());
    System.out.println("testGetNextBusesToSome: " + testGetNextBusesToSome());
    System.out.println("testGetNextBusesToAll: " + testGetNextBusesToAll());
  }

}
