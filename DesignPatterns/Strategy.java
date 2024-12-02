public class PizzaStrategyPattern {
    
    //Strategy Interface
    interface CostStrategy{
        double calculateCost();
    }

    //Concrete Strategies
    static class CheesePizzaCostStrategy implements CostStrategy{
        @Override
        public double calculateCost(){
            return 8.99;
        }
    }

    static class PepperoniPizzaCostStrategy implements CostStrategy{
        @Override
        public double calculateCost(){
            return 10.99;
        }
    }

    static class VeggiePizzaCostStrategy implements CostStrategy{
        @Override
        public double calculateCost(){
            return 9.99;
        }
    }

    // Context class (Pizza)
    static class Pizza{
        private String name;
        private CostStrategy costStrategy;

        //Constructor
        public Pizza(String name, CostStrategy costStrategy){
            this.name = name;
            this.costStrategy = costStrategy;
        }


        //Method to calculate cost
        public double calculateCost(){
            return costStrategy.calculateCost();
        }

        public String getName(){
            return name;
        }
    }

    public static void main(String[] args) {
        Pizza cheesePizza = new Pizza("Cheese Pizza", new CheesePizzaCostStrategy());
        System.out.println(cheesePizza.getName() + " costs $" + cheesePizza.calculateCost());

        Pizza pepperoniPizza = new Pizza("Pepperoni Pizza", new PepperoniPizzaCostStrategy());
        System.out.println(pepperoniPizza.getName() + " costs $" + pepperoniPizza.calculateCost());

        Pizza veggiePizza = new Pizza("Veggie Pizza", new VeggiePizzaCostStrategy());
        System.out.println(veggiePizza.getName() + " costs $" + veggiePizza.calculateCost());
    }
}
