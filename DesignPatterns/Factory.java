public class PizzaFactoryPattern {
    
    //Pizza Interface
    interface Pizza{
        void prepare();
        void bake();
        void cut();
        void box();
    }

    //Cheese Pizza
    static class CheesePizza implements Pizza{
        @Override
        public void prepare(){
            System.out.println("Preparing Cheese Pizza");
        }

        @Override
        public void bake(){
            System.out.println("Baking Cheese Pizza");
        }

        @Override
        public void cut(){
            System.out.println("Cutting Cheese Pizza");
        }

        @Override
        public void box(){
            System.out.println("Boxing Cheese Pizza");
        }
    }


    //Pepperoni Pizza
    static class PepperoniPizza implements Pizza{
        @Override
        public void prepare(){
            System.out.println("Preparing Pepperoni Pizza");
        }

        @Override
        public void bake(){
            System.out.println("Baking Pepperoni Pizza");
        }

        @Override
        public void cut(){
            System.out.println("Cutting Pepperoni Pizza");
        }

        @Override
        public void box(){
            System.out.println("Boxing Pepperoni Pizza");
        }
    }

    //Veggie Pizza
    static class VeggiePizza implements Pizza{
        @Override
        public void prepare(){
            System.out.println("Preparing Veggie Pizza");
        }

        @Override
        public void bake(){
            System.out.println("Baking Veggie Pizza");
        }

        @Override
        public void cut(){
            System.out.println("Cutting Veggie Pizza");
        }


        @Override
        public void box(){
            System.out.println("Boxing Veggie Pizza");
        }
    }


    //Factory Method
    public static Pizza createPizza(String type){
        switch(type.toLowerCase()){
            case "cheese":
            return new CheesePizza();
            case "pepperoni":
            return new PepperoniPizza();
            case "veggie":
            return new VeggiePizza();
            default:
            throw new IllegalArgumentException("Unknown Pizza type: " + type);
        }
    }

    public static void main(String[] args){
        Pizza cheesePizza = createPizza("cheese");
        cheesePizza.prepare();
        cheesePizza.bake();
        cheesePizza.cut();
        cheesePizza.box();

        Pizza veggiePizza = createPizza("veggie");
        veggiePizza.prepare();
        veggiePizza.bake();
        veggiePizza.cut();
        veggiePizza.box();
    }
}
