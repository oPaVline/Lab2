package barBossHouse;

public class Order {

    private Dish[] dishes;
    private int size;
    private static final int DEFAULT_VALUE = 16;

    public Order(Dish[] dishes) {
        this.dishes = dishes;
        size = 0;
    }

    public Order() {
        this(new Dish[DEFAULT_VALUE]);
    }

    public Order(int size) {
        this(new Dish[size]);
    }

    public boolean add(Dish dish) {
        if (size >= dishes.length) {
            Dish[] newDishes = new Dish[dishes.length * 2];
            System.arraycopy(dishes, 0, newDishes, 0, dishes.length);

            dishes = newDishes;
        }
        //todo нахер цикл+
        dishes[size] = dish;
        size++;

        return true;
    }

    public boolean remove(String dishName) {
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (dishName.equals(dishes[i].getName())) {
                    shiftArray(i);
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    public int removeAll(String dishName) {
        int count = 0;
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (dishName.equals(dishes[i].getName())) {
                    shiftArray(i);
                    count++;
                    size--;
                }
            }
        }
        return count;

    }

    public int dishQuantity() {
        return size;
    }

    public int dishQuantity(String dishName) {
        int count = 0;
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (dishName.equals(dishes[i].getName())) {
                    count++;
                }
            }
        }
        return count;
    }

    public Dish[] getDishes() {
        Dish[] newDishes = new Dish[size];
        System.arraycopy(dishes, 0, newDishes, 0, size);
        return newDishes;
    }

    public double costTotal() {
        int i;
        double TotalCost = 0;
        for (i = 0; i < dishes.length; i++)
            TotalCost += dishes[i].getCost();
        return TotalCost;
    }

    //todo не работает, да и говнокод+
    public String[] dishesNames() {
        boolean check = true;
        int uniq = 1;

        //подсчет уникальных названий
        for (int i = 1; i < size; i++) {
            for (int j = i - 1; j != 0; j--) {
                if (dishes[i].getName().equals(dishes[j].getName())) {
                    check = false;
                    break;
                }
            }
            if (check)
                uniq++;
            check = true;
        }

        //создание массива уникальных
        String[] dishesNamesArray = new String[uniq];
        int t = 1;
        dishesNamesArray[0] = dishes[0].getName();

        for (int i = 1; i < size; i++) {
            for (int j = i - 1; j != 0; j--) {
                if (dishes[i].getName().equals(dishes[j].getName())) {
                    check = false;
                    break;
                }
            }
            if (check) {
                dishesNamesArray[t] = dishes[i].getName();
                t++;
            }
            check = true;
        }
        return dishesNamesArray;
    }

    public Dish[] sortedDishesByCost() {
        int min = 0;
        Dish[] sortedArray = new Dish[size];
        double buf = 0;

        //todo метод какой-то вставки+
        for (int i = 0; i < size; i++) {
            for (int j=1; j<size; j++)
            if (sortedArray[0].getCost() < sortedArray[j].getCost())
                min++;
            buf = sortedArray[min].getCost();
            sortedArray[min].setCost(sortedArray[0].getCost());
            sortedArray[0].setCost(buf);
        }
        return sortedArray;
    }

    public int dishesCount(String dishName) {
        if (size != 0) {
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (dishName.equals(dishes[i].getName()))
                    count++;
            }
            return count;
        }
        return 0;

    }

    public void shiftArray(int i) {
        System.arraycopy(dishes, i + 1, dishes, i, dishes.length - i - 1);
        dishes[dishes.length - 1] = null;
    }

    public void shiftArray() {
        for (int i = 0; i < dishes.length; i++) {
            if (dishes[i] == null)
                shiftArray(i);
        }
    }


}
