import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/*  

    Project has 3 classes. Guest class represents our guest which held values like how much cake eaten, borek eaten or
    drink taken from our table. Class has a constructor that only sets name. guest.class has 3 method of eating and
    1 method for take under control condition of every individual eats or drinks everything at least once. Table.class
    has 6 values to hold, 3 of them primitive int represents initial food on the table, other 3 variables are set as
    AtomicInteger to avoid from problematic thread resource sharing, AtomicInteger has own lock() methods inside so
    while a thread using a AtomicInteger another one cannot. Table has methods that returns information about anything
    left on the case or on the table. It also has methods that increment or decrement values of both food on case or
    food on the table, these methods stayed Synchronized on development stage to be thread safe, it does not mean
    anything now because it is not used by more than one thread. Table.class also has methods to fill spots on the
    tables, of course this method using by waiter thread. Our homeSweetHome.class is where whole operation going,
    we have 2 threads; waiter and party. Waiter unceasingly checks if there is any need to fill, if it is then fills
    that very spots only. When there is no food left, waiter thread makes a summary and prints it. Party thread decides
    our guest behaviour, to ensure there is a first round that every guest takes every pieces at once, we know that is
    not the ideal thing to do but such as 70 pieces on table, it would be more accurate for the performance but if that
    was way bigger operation it should only have one stage that simultaneously checks the situation. Main part of party
    thread randomly picks a guest and that guest randomly picks a food and goes away.
    ---Conclusion---
        As we work with threads, we had realized that among the many methods it is more useful and but either
        inconsistent or not easy to develop. Threads needs to be controlled well using same resource with another thread,
        also when it is the only thread there could be consistency problems. We tried to use Thread.sleep() method to
        make it safer, we used AtomicInteger as well because of take advantage of it's thread safe opportunity, we found
        that type on StackOverFlow. There should be one more class which for the waiter but when we implemented it as
        Runnable and use it on another class it causes some trouble so we moved it to our main class. As we mentioned,
        in order to thread scheduling problems, some times Sleep() function is not enough so our summary results may
        have changed.
 */

public class homeSweetHome {
    public static void main(String[] args) {
        // condition of waiter thread to be stop
        final Boolean[] done = { true };

        // objects of guests
        guest guest1 = new guest("guest 1");
        guest guest2 = new guest("guest 2");
        guest guest3 = new guest("guest 3");
        guest guest4 = new guest("guest 4");
        guest guest5 = new guest("guest 5");
        guest guest6 = new guest("guest 6");
        guest guest7 = new guest("guest 7");
        guest guest8 = new guest("guest 8");
        guest guest9 = new guest("guest 9");
        guest guest10 = new guest("guest 10");
        // Linked list that contains guests
        LinkedList<guest> guestOList = new LinkedList<guest>();
        guestOList.add(guest1);
        guestOList.add(guest2);
        guestOList.add(guest3);
        guestOList.add(guest4);
        guestOList.add(guest5);
        guestOList.add(guest6);
        guestOList.add(guest7);
        guestOList.add(guest8);
        guestOList.add(guest9);
        guestOList.add(guest10);
        table table1 = new table();

        Thread waiter = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Starting...");
                // Checking if there is any food or beverage to be served

                while (table1.anyBorekHasLeftOnCase() || table1.anyCakeHasLeftOnCase()
                        || table1.anyDrinkHasLeftOnCase()) {

                    // System.out.println("Have utilities");
                    // waiter fills spots
                    if (table1.initCakeOnTable == 0 && table1.anyCakeHasLeftOnCase()) {
                        table1.fillTableCakes();
                        System.out.println("|> Added Cake to table");

                    }

                    if (table1.initBorekOnTable == 0 && table1.anyBorekHasLeftOnCase()) {
                        table1.fillTableBorek();
                        System.out.println("|> Added Borek to table");

                    }
                    if (table1.initDrinkOnTable == 0 && table1.anyDrinkHasLeftOnCase()) {
                        table1.fillTableDrink();
                        System.out.println("|> Added Drink to table");

                    }

                }
                // Stops when out of food
                done[0] = false;
                System.out.println("OUT OF FOOD");
                // printing what have eaten
                // total eaten = first 10 on tray + taken from case
                int total = 10;
                System.out.println("-----------SUMMARY---------");
                for (int i = 0; i < guestOList.size(); i++) {
                    System.out.println(guestOList.get(i).name + "|||" + " borek eaten:" + guestOList.get(i).borekEaten
                            + " cake eaten: " + guestOList.get(i).cakeEaten + " drink taken: "
                            + guestOList.get(i).drinkTaken);
                    total = total + guestOList.get(i).borekEaten;
                    total = total + guestOList.get(i).cakeEaten;
                    total = total + guestOList.get(i).drinkTaken;
                    System.out.println("");
                }
                System.out.println("TOTAL TAKEN : " + total);

            }
        });

        // initialize guest behaviour as a thread
        Thread party = new Thread(new Runnable() {
            @Override
            public void run() {

                while (table1.anyBorekHasLeftOnCase() && table1.anyBorekHasLeftOnTable()
                        || table1.anyCakeHasLeftOnCase() && table1.anyCakeHasLeftOnTable()
                        || table1.anyDrinkHasLeftOnCase() && table1.anyDrinkHasLeftOnTable()) {
                    int guestEatenAllCount = 0;
                    // <-- START OF THE FIRST ROUND -->
                    // System.out.println("Have utilities");
                    while (guestEatenAllCount < 30) {

                        Random rand = new Random();
                        int pickGuest = rand.nextInt(10);
                        // Taking first 3 food
                        if (!guestOList.get(pickGuest).haveEatenAll()) {
                            if (guestOList.get(pickGuest).cakeEaten == 0) {
                                guestOList.get(pickGuest).eatCake();
                                guestEatenAllCount++;
                                System.out.println(guestOList.get(pickGuest).name + " eats cake");
                                table1.takeCakeTable();

                            } else if (guestOList.get(pickGuest).borekEaten == 0) {
                                guestOList.get(pickGuest).eatBorek();
                                System.out.println(guestOList.get(pickGuest).name + " eats borek");
                                guestEatenAllCount++;
                                table1.takeBorekTable();
                                table1.takeCakeTable();

                            } else if (guestOList.get(pickGuest).drinkTaken == 0) {
                                guestOList.get(pickGuest).takeDrink();
                                System.out.println(guestOList.get(pickGuest).name + " drinks");
                                guestEatenAllCount++;
                                table1.takeDrinkTable();
                                table1.takeCakeTable();

                            }

                        }
                    }

                    // <-- END OF THE FIRST ROUND -->
                    System.out.println("done first 10 round");
                    // done first 10 rounds now for the rest of it
                    // <-- START OF MAIN PART -->
                    while (done[0]) {
                        // done is the key for table reserves finishes

                        // Guest chooses which food to eat RANDOMLY
                        Random rand = new Random();
                        int pickGuestN = rand.nextInt(10);
                        int pickFood = rand.nextInt(3); // 0 for borek, 1 for cake, 2 for drink
                        if (pickFood == 0 && guestOList.get(pickGuestN).borekEaten <= 5) {
                            // Condition to take a Borek. It has to choose it and must have eaten less than
                            // 5
                            if (table1.anyBorekHasLeftOnTable()) {
                                guestOList.get(pickGuestN).eatBorek();
                                table1.takeBorekTable();
                                System.out.println(guestOList.get(pickGuestN).name + " eats borek");

                                table1.takeCakeTable();
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        } else if (pickFood == 1 && guestOList.get(pickGuestN).cakeEaten <= 2) {
                            // Condition to take a Cake. It has to choose it and must have eaten less than 2
                            if (table1.anyCakeHasLeftOnTable()) {
                                guestOList.get(pickGuestN).eatCake();
                                table1.takeCakeTable();
                                System.out.println(guestOList.get(pickGuestN).name + " eats cake");

                                table1.takeCakeTable();
                                // thread to sleep in order to make an interrupt with waiter thread
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        } else if (pickFood == 2 && guestOList.get(pickGuestN).drinkTaken <= 5) {
                            // Condition to take a Drink. It has to choose it and must have eaten less than
                            // 5
                            if (table1.anyDrinkHasLeftOnTable()) {
                                guestOList.get(pickGuestN).takeDrink();
                                table1.takeDrinkTable();
                                System.out.println(guestOList.get(pickGuestN).name + " drinks ");

                                table1.takeCakeTable();
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }

                        }

                    }
                    // <-- END OF THE MAIN PART -->

                }

            }
        });

        waiter.start();
        party.start();

    }

}
