package barBossHouse;

import java.util.function.Predicate;
import java.util.Objects;

public class OrderManager {

    private Order[] orders;

    OrderManager(int tableCount) {
        orders = new Order[tableCount];
    }
    //todo где все конструкторы?+
    //https://pp.userapi.com/c840231/v840231059/8888a/xU5bYtZOpkk.jpg

    public void add(Order order, int tableNumber) {
        orders[tableNumber] = order;
    }

    public Order getOrder(int tableNumber) {
            return orders[tableNumber];
    }

    public void addDish(Dish dish, int tableNumber) {
        orders[tableNumber].add(dish);
    }

    public void removeOrder(int tableNumber) {
        orders[tableNumber] = null;
    }

/*
    public int freeTableNumber() {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                return i;
            }
        }
        return -1;
    }
*/

    private int countFreeTable(Predicate<Order> predicate) {
        int i;
        int count = 0;
        for (i = 0; i < orders.length; i++) {
            if (predicate.test(orders[i])) count++;
        }
        return count;
    }

    private int[] createArrTableNum(Predicate<Order> predicate) {
        int[] arrTableNum = new int[countFreeTable(predicate)];
        int i = 0, j = 0;
        while (i < orders.length) {
            if (predicate.test(orders[i])) {
                arrTableNum[j] = i;
                j++;
            }
            i++;
        }
        return arrTableNum;
    }

    public int freeTableNumber() {
        for (int i = 0; i < orders.length; i++) {
            if (Objects.isNull(orders[i])) return i;
        }
        return -1;
    }

    public int[] freeTableNumbers() {
        return createArrTableNum(Objects::isNull);
    }

    public Order[] getOrders() {
        Order[] orders;
        int i = 0, j = 0, count = 0;
        for (Order order : orders = new Order[countFreeTable(p -> !Objects.isNull(p))]) {
        }
        while (i < this.orders.length) {
            if (this.orders[i] != null) {
                orders[j] = this.orders[i];
                j++;
            }
            i++;
        }
        return orders;
    }

    public double orderCostSummary(){
        double cost = 0;
        for (Order o: orders)
            if (o!=null)
                cost+=o.costTotal();
        return cost;
    }

    public int dishQuantity(String dishName){
        int count=0;
        int i=0;

        while(i<orders.length){

            count+=orders[i].dishesCount(dishName);
            i++;

        }
        return count;
    }

}
