import java.time.LocalDate;

public class Product implements Shippable{
    private String name;
    private int quantity;
    private double weight;
    private double price;
    private LocalDate expireDate;

    public Product(String name, double price, int quantity, LocalDate expireDate,double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expireDate = expireDate;
        this.weight = weight;
    }
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getexpireDate() {
        return expireDate;
    }

    @Override
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isExpired() {
        if (expireDate == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        return today.isAfter(expireDate);
    }
    //  weight > 0 Products are shippable
    public boolean isShippable() {
        return weight > 0;
    }
}