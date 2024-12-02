import java.util.ArrayList;
import java.util.List;


// Subject Interface
interface Subject{
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

// Observer Interface
interface Observer{
    void update(String productName, boolean isInStock);
}

// Concrete Subject (Product)
class Product implements Subject{
    private String name;
    private boolean isInStock;
    private List<Observer> observers;

    public Product(String name){
        this.name = name;
        this.isInStock = false;
        this.observers = new ArrayList<>();
    }

    public void setInStock(boolean isInStock){
        this.isInStock = isInStock;
        notifyObservers();
    }

    @Override
    public void attach(Observer observer){
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer){
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(){
        for(Observer observer : observers){
            observer.update(name, isInStock);
        }
    }
}

// Concrete Observer (User)
class User implements Observer{
    private String username;

    public User(String username){
        this.username = username;
    }

    @Override
    public void update(String productName, boolean isInStock){
        if(isInStock){
            System.out.println("Hello" + username + ", the product '" + productName + "' is back in stock !!");
        } else {
            System.out.println("Hello " + username + "' the product" + productName + " ' is currently out of Stock");
        }
    }



// Client code
public static void main(String[] args){
    Product product = new Product("Wireless Headphones");

    User user1 = new User("Alice");
    User user2 = new User("Bob");

    product.attach(user1);
    product.attach(user2);

    System.out.println("Setting product to in stock");
    product.setInStock(true);

    System.out.println("\n Detaching Alice");
    product.detach(user1);

    System.out.println("Setting product ot out of stock...");
    product.setInStock(false);
}




// // Concrete Subject (Product)
// class Product implements Subject {
//     private String name;
//     private int stock; // Tracks the number of items in stock
//     private List<Observer> observers; // Customers subscribed
//     private Queue<Observer> waitlist; // Waiting list for out-of-stock

//     public Product(String name, int stock) {
//         this.name = name;
//         this.stock = stock;
//         this.observers = new ArrayList<>();
//         this.waitlist = new LinkedList<>();
//     }

//     public void purchase(Observer observer) {
//         if (stock > 0) {
//             stock--;
//             System.out.println(observer + " successfully purchased the product '" + name + "'. Remaining stock: " + stock);
//         } else {
//             System.out.println(observer + " added to the waitlist for the product '" + name + "'.");
//             waitlist.add(observer);
//         }
//         notifyObservers();
//     }

//     public void restock(int amount) {
//         stock += amount;
//         System.out.println("Product '" + name + "' restocked with " + amount + " units. Total stock: " + stock);

//         // Notify observers on the waitlist
//         while (stock > 0 && !waitlist.isEmpty()) {
//             Observer observer = waitlist.poll();
//             stock--;
//             System.out.println(observer + " successfully purchased from the restocked product '" + name + "'. Remaining stock: " + stock);
//         }

//         notifyObservers();
//     }

//     @Override
//     public void attach(Observer observer) {
//         observers.add(observer);
//     }

//     @Override
//     public void detach(Observer observer) {
//         observers.remove(observer);
//     }

//     @Override
//     public void notifyObservers() {
//         for (Observer observer : observers) {
//             observer.update(name, stock > 0, stock);
//         }
//     }
}
