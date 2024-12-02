public class PizzaOven{
    private static PizzaOven instance;

    private PizzaOven(){
        System.out.println("Pizza Oven is ready!");
    }

    public static PizzaOven getInstance(){
        if(instance == null){
            instance = new PizzaOven();
        }
        return instance;
    }

    public void makePizza(String pizzaType){
        System.out.println("Making a " + pizzaType + "pizza in the oven");
    }

    public static void main(String[] args) {
        PizzaOven oven1 = PizzaOven.getInstance();
        PizzaOven oven2 = PizzaOven.getInstance();

        System.out.println(oven1 == oven2);

        oven1.makePizza("Margherita");
        oven2.makePizza("Pepperoni");
    }
}

