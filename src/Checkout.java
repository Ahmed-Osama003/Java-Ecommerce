import java.util.List;
import java.util.ArrayList;

public class Checkout {

    public void checkout(Customer customer, Cart cart) throws Exception {

        if (cart.isEmpty()) {
            throw new Exception("Cart is empty");
        }

        TestCartItems(cart);

        double subtotal = cart.getSubtotal();
        List<CartItem> shippableItems = cart.getShippableItems();
        double shippingFee = calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        if (totalAmount > customer.getBalance()) {
            throw new Exception("Customer's balance is insufficient");
        }

        if (!shippableItems.isEmpty()) {
            printShipmentNotice(shippableItems);
        }

        printCheckoutReceipt(cart);

        updateProductQuantities(cart);


        customer.pay(totalAmount);

        printFinalSummary(subtotal, shippingFee, totalAmount, customer);
    }

    private void TestCartItems(Cart cart) throws Exception {
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().isExpired()) {
                throw new Exception("Product " + item.getProduct().getName() + " is expired");
            }
            if (item.getQuantity() > item.getProduct().getQuantity()) {
                throw new Exception("Product " + item.getProduct().getName() + " is out of stock");
            }
        }
    }

    private void updateProductQuantities(Cart cart) {
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
        }
    }

    private double calculateShippingFee(List<CartItem> shippableItems) {
        double totalWeight = 0;
        for (CartItem item : shippableItems) {
            totalWeight += item.getQuantity() * item.getProduct().getWeight();
        }
        return totalWeight * 3;
    }

    private void printShipmentNotice(List<CartItem> shippableItems) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;

        for (CartItem item : shippableItems) {
            Product product = item.getProduct();
            double itemWeight = item.getQuantity() * product.getWeight();
            totalWeight += itemWeight;

            int weightInGrams = (int)(itemWeight * 1000);
            System.out.println(item.getQuantity() + "x " + product.getName() +
                    "\t\t" + weightInGrams + "g");
        }

        System.out.println("Total package weight " + totalWeight + "kg");
        System.out.println();
    }

    private void printCheckoutReceipt(Cart cart) {
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            int totalPrice = (int)(item.getTotalPrice());
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() +
                    "\t\t" + totalPrice);
        }
    }

    private void printFinalSummary(double subtotal, double shippingFee, double totalAmount, Customer customer) {
        System.out.println("----------------------");
        System.out.println("Subtotal\t\t" + (int)subtotal);
        System.out.println("Shipping\t\t" + (int)shippingFee);
        System.out.println("Amount\t\t\t" + (int)totalAmount);
        System.out.println("Customer balance after payment: " + Math.ceil(customer.getBalance()));
    }
}