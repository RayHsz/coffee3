package bean;

/**
 * @Author19793
 * @Date2022/4/27 14:58
 * @Version 1.0
 */
public class Product {
    private  int stock;
    private int id;
    private String name;
    private float price;
    private int cates;

    public Product() {
    }

    public Product(int id, String name, float price, int cates, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cates = cates;
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCates() {
        return cates;
    }

    public void setCates(int cates) {
        this.cates = cates;
    }

    @Override
    public String toString() {
        return "Product{" +
                "stock=" + stock +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", cates=" + cates +
                '}';
    }
}
