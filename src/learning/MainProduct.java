package learning;

class ProductManager{
    public void Add(Product product){
        System.out.println("Sepete eklendi " + product.getName());
    }

}
class Product{
    // attribute , field
    private int id;
    private String name;
    private String description;
    private double price;
    private int stockAmount;
    private String color;
    private String kod;


    public int getId() {
        return id;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getKod() {
        return this.name.substring(0,1) + id;
    }


}

public class MainProduct {
    public static void main(String[] args) {
        Product product = new Product();
        product.setName("Laptop");
        product.setId(1);
        product.setDescription("Apple");
        product.setPrice(10000);
        product.setColor("DarkGray");
        product.setStockAmount(5);

        ProductManager productManager = new ProductManager();
        productManager.Add(product);

        System.out.println(product.getKod());

    }
}
