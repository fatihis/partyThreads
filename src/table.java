import java.util.concurrent.atomic.AtomicInteger;

public class table {
    AtomicInteger initBorekOnCase = new AtomicInteger(25);
    AtomicInteger initDrinkOnCase = new AtomicInteger(25);
    AtomicInteger initCakeOnCase = new AtomicInteger(10);

    int initCakeOnTable = 5;
    int initDrinkOnTable = 5;
    int initBorekOnTable = 5;

    boolean anyCakeHasLeftOnCase() {
        if (initCakeOnCase.get() > 0) {
            return true;
        } else {
            return false;
        }
    }

    boolean anyCakeHasLeftOnTable() {
        if (initCakeOnTable > 0) {
            return true;
        } else {
            return false;
        }
    }

    boolean anyDrinkHasLeftOnCase() {
        if (initDrinkOnCase.get() > 0) {
            return true;
        } else {
            return false;
        }

    }

    boolean anyDrinkHasLeftOnTable() {
        if (initDrinkOnTable > 0) {
            return true;
        } else {
            return false;
        }
    }

    boolean anyBorekHasLeftOnCase() {
        if (initBorekOnCase.get() > 0) {
            return true;
        } else {
            return false;
        }


    }

    boolean anyBorekHasLeftOnTable() {
        if (initBorekOnTable > 0) {
            return true;
        } else {
            return false;
        }

    }

    synchronized void takeCakeTable() {
        initCakeOnTable--;
    }

    synchronized void takeDrinkTable() {
        initDrinkOnTable--;
    }

    synchronized void takeBorekTable() {
        initBorekOnTable--;
    }

    synchronized void takeCakeCase() {
        initCakeOnCase.decrementAndGet();
        System.out.println("Waiter: takes cake from case");
    }

    synchronized void takeDrinkCase() {
        initDrinkOnCase.decrementAndGet();
        System.out.println("Waiter: takes drink from case");
    }

    synchronized void takeBorekCase() {
        initBorekOnCase.decrementAndGet();
        System.out.println("Waiter: takes borek from case");
    }

    void fillTableCakes() {
        //check how much
        //add
       /* if (anyCakeHasLeftOnCase()) {
            if (initCakeOnCase >= 5) {
                initCakeOnCase = initCakeOnCase - 5;
                initCakeOnTable = initCakeOnTable + 5;
            } else if (initCakeOnCase >= 4) {
                initCakeOnCase = initCakeOnCase - 4;
                initCakeOnTable = initCakeOnTable + 4;
            } else if (initCakeOnCase >= 3) {
                initCakeOnCase = initCakeOnCase - 3;
                initCakeOnTable = initCakeOnTable + 3;
            } else if (initCakeOnCase >= 2) {
                initCakeOnCase = initCakeOnCase - 2;
                initCakeOnTable = initCakeOnTable + 2;
            } else if (initCakeOnCase >= 1) {
                initCakeOnCase = initCakeOnCase - 1;
                initCakeOnTable = initCakeOnTable + 1;
            }
        }*/
        while (initCakeOnTable < 5 && anyDrinkHasLeftOnCase()) {
            takeCakeCase();
            initCakeOnTable++;
        }

    }

    void fillTableBorek() {
       /* if (anyBorekHasLeftOnCase()) {
            if (initBorekOnCase >= 5) {
                initBorekOnCase = initBorekOnCase - 5;
                initBorekOnTable = initBorekOnTable + 5;
            } else if (initBorekOnCase >= 4) {
                initBorekOnCase = initBorekOnCase - 4;
                initBorekOnTable = initBorekOnTable + 4;
            } else if (initBorekOnCase >= 3) {
                initBorekOnCase = initBorekOnCase - 3;
                initBorekOnTable = initBorekOnTable + 3;
            } else if (initBorekOnCase >= 2) {
                initBorekOnCase = initBorekOnCase - 2;
                initBorekOnTable = initBorekOnTable + 2;
            } else if (initBorekOnCase >= 1) {
                initBorekOnCase = initBorekOnCase - 1;
                initBorekOnTable = initBorekOnTable + 1;
            }
        }*/
        while (initBorekOnTable < 5 && anyBorekHasLeftOnCase()) {
            takeBorekCase();
            initBorekOnTable++;
        }

    }

    void fillTableDrink() {
        /*if (anyDrinkHasLeftOnCase()) {
            if (initDrinkOnCase >= 5) {
                initDrinkOnCase = initDrinkOnCase - 5;
                initDrinkOnTable = initDrinkOnTable + 5;
            } else if (initDrinkOnCase >= 4) {
                initDrinkOnCase = initDrinkOnCase - 4;
                initDrinkOnTable = initDrinkOnTable + 4;
            } else if (initDrinkOnCase >= 3) {
                initDrinkOnCase = initDrinkOnCase - 3;
                initDrinkOnTable = initDrinkOnTable + 3;
            } else if (initDrinkOnCase >= 2) {
                initDrinkOnCase = initDrinkOnCase - 2;
                initDrinkOnTable = initDrinkOnTable + 2;
            } else if (initDrinkOnCase >= 1) {
                initDrinkOnCase = initDrinkOnCase - 1;
                initDrinkOnTable = initDrinkOnTable + 1;
            }
        }*/
        while (initDrinkOnTable < 5 && anyDrinkHasLeftOnCase()) {
            takeDrinkCase();
            initDrinkOnTable++;
        }

    }


}

