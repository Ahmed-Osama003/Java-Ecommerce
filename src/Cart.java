import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<CartItem>();
    }

    public void add(Product item, int count) throws Exception {
        if (count > item.getQuantity()) {
            throw new Exception("Not enough " + item.getName() + " available." );
        }
        if (item.isExpired()){
            throw new Exception("Product " + item.getName() + " is expired");
        }
        items.add(new CartItem(item, count));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }

    public List<CartItem> getShippableItems() {
        List<CartItem> shippableItems = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct().isShippable()) {
                shippableItems.add(item);
            }
        }
        return shippableItems;
    }
    public boolean isEmpty() {
        return items.isEmpty();
    }

}