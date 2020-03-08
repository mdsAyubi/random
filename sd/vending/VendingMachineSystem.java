import java.util.Map;

public class VendingMachineSystem {
    public static void main(String[] args) {

    }
}

class VendingMachine {
    MoneySlotManager moneySlot;
    ItemRequestService itemRequestService;

    public void processRequest(int itemId) {
        boolean success = false;

        while (!success) {
            success = moneySlot.hasEnoughMoneyForItem(itemId);
            moneySlot.insertMoney();
        }

        if (success) {
            itemRequestService.vendItem(itemId);
            moneySlot.dispenseLeftOver(itemId);

        }

    }
}

class ItemPricingService {

    public Long getPriceForItem(int itemId) {
        return null;
    }

}

/**
 * Implement state machine here
 */
class MoneySlotManager {

    long total = 0;
    MoneyDispenser moneyDispenser;
    MoneyRequester moneyRequester;
    ItemPricingService itemPricingService;

    public boolean hasEnoughMoneyForItem(int itemId) {
        return total == itemPricingService.getPriceForItem(itemId);
    }

    public void deductMoney(Long priceForItem) {
        total -= priceForItem;
    }

    public boolean insertMoney() {
        long amount = moneyRequester.request();
        total += amount;
        return true;
    }

    public void dispenseLeftOver(int itemId) {
        long itemPrice = itemPricingService.getPriceForItem(itemId);
        deductMoney(itemPrice);
        moneyDispenser.dispense(total);
        total = 0;
    }

}

class MoneyRequester {

    public long request() {
        return 0;
    }

}

class MoneyDispenser {

    public void dispense(long amount) {
    }

}

enum Denomination {

}

class ItemRequestService {

    Map<Integer, Item> inventory;

    public Item vendItem(int itemId) {
        return inventory.get(itemId);
    }

}

class Item {
    int id;
    String name;
}