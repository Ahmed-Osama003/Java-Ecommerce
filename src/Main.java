import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Product cheese = new Product("Cheese", 200, 10, LocalDate.now().plusDays(30), 0.4); // Expirable, Shippable
        Product biscuits = new Product("Biscuits", 150, 5, LocalDate.now().plusDays(15), 0.7); // Expirable, Shippable
        Product tv = new Product("TV", 1500, 5, null, 3.0); // Non-expirable, Shippable
        Product scratchCard = new Product("Mobile scratch card", 50, 10, null, 0); // Non-expirable, weight = 0 ->Non-shippable


        Product expiredCheese = new Product("Cheese", 200, 10, LocalDate.now().minusDays(1), 0.4); // Expired
        Product expiredBiscuits = new Product("Biscuits", 150, 5, LocalDate.now().minusDays(5), 0.7); // Expired
        Customer customer = new Customer("Ahmedosama", 2000);

        try {
            Cart cart = new Cart();

//            cart.add(cheese, 1);
            cart.add(tv, 3);
//            cart.add(scratchCard, 2);
//            cart.add(biscuits, 1);

            Checkout checkoutService = new Checkout();
            checkoutService.checkout(customer, cart);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}