import java.time.LocalDateTime;
import java.util.*;

public class RestaurentSystem {
    public static void main(String[] args) {

    }
}

class Restaurent {
    List<Table> tables;
    Queue<Customer> customers;
    MenuService menuService;

    private Map<Customer, Table> customerToTableMap = new HashMap<>();
    private Map<Table, Customer> tableToCustomerMap = new HashMap<>();

    public Restaurent(List<Table> tables, Queue<Customer> customers) {
        this.tables = tables;
        this.customers = customers;
    }

    public void serveCustomer() {
        Customer customer = customers.poll();
        Table table = null;
        while (table == null) {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table = getTable(customer);
        }
        if (table != null) {
            table.isOccupied = true;
            customerToTableMap.put(customer, table);
            tableToCustomerMap.put(table, customer);
        }
    }

    public void releaseCustomer(Customer customer, Table table) {
        customerToTableMap.remove(customer);
        tableToCustomerMap.remove(table);
    }

    public List<Customer> getWaitingCustomer() {
        return new ArrayList<Customer>(customers);
    }

    public Table getAssignedTableForCustomer(Customer customer) {
        return customerToTableMap.getOrDefault(customer, null);

    }

    public Customer getAssignedCustomerForTable(Table table) {
        return tableToCustomerMap.getOrDefault(table, null);

    }

    public Table getTable(Customer customer) {
        Optional<Table> table = tables.stream().filter(t -> !t.isOccupied).findAny();
        if (table.isPresent()) {
            return table.get();
        }
        return null;
    }

    public Menu getMenu(Customer customer) {
        return menuService.getMenu(customer.entryTime);
    }

    public void addCustomer(Customer customer) {
        this.customers.offer(customer);
    }
}

class MenuService {
    public Menu getMenu(LocalDateTime time) {
        return null;
    }
}

class Menu {
    String name;
    MenuType type;
    List<MenuItem> items;
}

enum MenuType {
    BREAKFAST, LUNCH, DINNER
}

class MenuItem {
    int id;
    int name;
    double price;
    Map<String, Object> metaInfo;

    public MenuItem(int id, int name, double price, Map<String, Object> metaInfo) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.metaInfo = metaInfo;
    }
}

class Customer {
    long id;
    String name;
    int partyCount;
    LocalDateTime entryTime;
    Map<String, Object> metaInfo;
}

class Table {
    int id;
    TableType type;
    int capacity;
    boolean isOccupied;

    public Table(int id, TableType type, int capacity, boolean isOccupied) {
        this.id = id;
        this.type = type;
        this.capacity = capacity;
        this.isOccupied = isOccupied;
    }

}

enum TableType {
    SMALL, MEDIUM, LARGE
}