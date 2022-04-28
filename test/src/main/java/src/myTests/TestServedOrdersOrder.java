package src.myTests;

public class TestServedOrdersOrder {
    
    public static boolean isFirstComeFirstServed(int[] takeOutOrders, int[] dineInOrders, int[] servedOrders) {
        return isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders, 0, 0, 0);
    }
    
    private static boolean isFirstComeFirstServed(int[] takeOutOrders, int[] dineInOrders, int[] servedOrders,
                                          int servedOrdersIndex, int takeOutOrdersIndex, int dineInOrdersIndex) {
    
        // base case we've hit the end of servedOrders
        if (servedOrdersIndex == servedOrders.length) {
            return true;
        }
    
        // if we still have orders in takeOutOrders
        // and the current order in takeOutOrders is the same
        // as the current order in servedOrders
        if ((takeOutOrdersIndex < takeOutOrders.length)
                && (takeOutOrders[takeOutOrdersIndex] == servedOrders[servedOrdersIndex])) {
            takeOutOrdersIndex++;
    
        // if we still have orders in dineInOrders
        // and the current order in dineInOrders is the same
        // as the current order in servedOrders
        } else if ((dineInOrdersIndex < dineInOrders.length)
                && (dineInOrders[dineInOrdersIndex] == servedOrders[servedOrdersIndex])) {
            dineInOrdersIndex++;
    
        // if the current order in servedOrders doesn't match
        // the current order in takeOutOrders or dineInOrders, then we're not
        // serving in first-come, first-served order.
        } else {
            return false;
        }
    
        // the current order in servedOrders has now been "accounted for"
        // so move on to the next one
        servedOrdersIndex++;
        return isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders, servedOrdersIndex, takeOutOrdersIndex, dineInOrdersIndex);
    }
    public static void main(String[] args){
        int[] takeOutOrders =  {17, 8, 24};
        int[] dineInOrders =  {12, 19, 2};
        int[] servedOrders =  {17, 8, 12, 19, 24, 2};
        
        isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
    }
}
