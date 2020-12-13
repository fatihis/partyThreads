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
