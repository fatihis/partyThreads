public class guest {
    int borekEaten;
    int drinkTaken;
    int cakeEaten;
    String name;

    public guest(String nm) {
        this.name = nm;
        borekEaten = 0;
        drinkTaken = 0;
        cakeEaten = 0;
    }

    void eatCake() {
        cakeEaten++;
    }

    ;

    void eatBorek() {
        borekEaten++;
    }

    ;

    void takeDrink() {
        drinkTaken++;
    }

    ;

    boolean haveEatenAll() {
        if (borekEaten > 0 && drinkTaken > 0 && cakeEaten > 0) {
            return true;
        } else {
            return false;
        }
    }


}
