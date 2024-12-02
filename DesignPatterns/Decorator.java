public class PizzaDecoratorPattern{

    // Pizza interface
    interface Pizza{
        String getDescription();
        double getCost();
    }

    //Basic Pizza class 
    static class BasicPizza implements Pizza{
        @Override
        public String getDescription(){
            return "Basic Pizza";
        }

        @Override
        public double getCost(){
            return 5.00;
        }
    }

    //Abstract Decorator Class
    static abstract class PizzaDecorator implements Pizza{
        protected Pizza pizza;

        public PizzaDecorator(Pizza pizza){
            this.pizza = pizza;
        }

        @Override
        public String getDescription(){
            return pizza.getDescription();
        }

        @Override
        public double getCost(){
            return pizza.getCost();
        }
    }

    //Chesse Topping Decorator
    static class CheeseDecorator extends PizzaDecorator{
        public CheeseDecorator(Pizza pizza){
            super(pizza);
        }

        @Override
        public String getDescription(){
            return pizza.getDescription() + ", Cheese";
        }

        @Override
        public double getCost(){
            return pizza.getCost() + 1.50;
        }
    }

    //Pepperoni Topping Decorator
    static class PepperoniDecorator extends PizzaDecorator{
        public PepperoniDecorator(Pizza pizza){
            super(pizza);
        }

        @Override
        public String getDescription(){
            return pizza.getDescription() + ". Pepperoni";
        }

        @Override
        public double getCost(){
            return pizza.getCost() + 2.00;
        }
    }

    //Olives Topping Decorator
    static class OlivesDecorator extends PizzaDecorator{
        public OlivesDecorator(Pizza pizza){
            super(pizza);
        }

        @Override
        public String getDescription(){
            return pizza.getDescription() + ",Olives";
        }

        @Override
        public double getCost(){
            return pizza.getCost() + 0.75;
        }
    }

    // Main Method
    public static void main(String[] args){
        Pizza myPizza = new BasicPizza();

        //Let's add some cheese mate 
        myPizza = new CheeseDecorator(myPizza);

        //Let's go for some pepperoni
        myPizza = new PepperoniDecorator(myPizza);

        //Now the final Olives
        myPizza = new OlivesDecorator(myPizza);

        System.out.println("Pizza Description" + myPizza.getDescription());
        System.out.println("Total Cost: $"+myPizza.getCost());
    }

}
