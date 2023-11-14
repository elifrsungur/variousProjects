package learning;
class product{
    private String name;
    private String description;
    private double price; // private- sadece bu classın içinde diğer claslarda kullanabilemk için getter setter

    public String getName(){
        return name;
    }
    public void setName(String isim){
        name = isim;
        this.name=isim;
    }
}

public class Main {
    public static void main(String[] args) {
        product product1 = new product();
        product1.setName("Laptop");

        product product2 = new product();
        product2.setName("mouse");
        product [] urunler = new product[]{
                product1, product2
        };
        for(int i = 0 ; i < urunler.length ; i++){
            System.out.println(urunler[i].getName());
        }
    }
}
