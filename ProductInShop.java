package clientserver.shop;
import java.util.List;

public class ProductInShop {
    private String name_of_product;
    private String group_of_product;
    private int amount_of_product;
    private double price_of_product;

    public ProductInShop(String name_of_product, String group_of_product, int amount_of_product, double price_of_product) {
        this.name_of_product = name_of_product;
        this.group_of_product = group_of_product;
        this.amount_of_product = amount_of_product;
        this.price_of_product = price_of_product;
    }
    public String getGroup_of_product() {
        return group_of_product;
    }

    public void setGroup_of_product(String group_of_product) {
        this.group_of_product = group_of_product;
    }

    public String getName() {
        return name_of_product;
    }

    public void setName(String name_of_product) {
        this.name_of_product = name_of_product;
    }

    public int getAmount_of_product() {
        return amount_of_product;
    }

    public void setAmount_of_product(int amount_of_product) {
        this.amount_of_product = amount_of_product;
    }

    public double getPrice_of_product() {
        return price_of_product;
    }

    public void setPrice_of_product(double price_of_product) {
        this.price_of_product = price_of_product;
    }


}
