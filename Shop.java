package clientserver.shop;

import clientserver.message.ObjectDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Shop
{
    public List<String> getGroups_of_product() {
        return groups_of_product;
    }

    private List<String> groups_of_product;

    public TreeMap<String, ProductInShop> getProducts() {
        return products;
    }

    public void currentProducts() {
        for ( String i: products.keySet()){
            System.out.println(products.get(i).getGroup_of_product() + ": " + i);
        }
    }

    private TreeMap<String, ProductInShop> products;

    public Shop() {
        this.groups_of_product = new ArrayList<>();
        this.products = new TreeMap<>();
    }

    public void process_shop (ObjectDomain od)
    {
        if (od.getCommand() == 0) {
            System.out.println("Amount of " + od.getName_of_product() + " is " + get_amount_of_product(od.getName_of_product()));
        }
        if (od.getCommand() == 1) {
            delete_product(od.getName_of_product(),od.getAmount());
        }
        if (od.getCommand() == 2) {
            add_product(od.getName_of_product(),od.getAmount());
        }
        if (od.getCommand() == 3) {
            add_group_of_product(od.getName_of_product());
        }
        if (od.getCommand() == 4) {
            add_product_to_group(od.getName_of_product(), od.getGroup_of_product());
        }
        if (od.getCommand() == 5) {
            set_price(od.getName_of_product(), od.getPrice());
        }
    }

    public int get_amount_of_product(String name_of_product)
    {
        return products.get(name_of_product).getAmount_of_product();
    }
    public void delete_product(String name_of_product, int amount)
    {
        int new_amount = products.get(name_of_product).getAmount_of_product()-amount;
        if (new_amount<0) throw new ArithmeticException("Impossible negative amount of products.");
        products.get(name_of_product).setAmount_of_product(new_amount);
    }
    public void add_product(String name_of_product, int amount)
    {
        int new_amount = products.get(name_of_product).getAmount_of_product()+amount;
        products.get(name_of_product).setAmount_of_product(new_amount);
    }
    public void add_group_of_product(String name_of_group)
    {
        if (groups_of_product == null || !groups_of_product.contains(name_of_group)) {
            groups_of_product.add(name_of_group);
        }
    }
    public void add_product_to_group(String name_of_product,String name_of_group )
    {
        ProductInShop productInShop = products.get(name_of_product);
        if (productInShop==null){
            ProductInShop new_prod = new ProductInShop(name_of_product, name_of_group, 0, 0);
            products.put(name_of_product, new_prod);
        }
    }
    public void set_price(String name_of_product, double price)
    {
        products.get(name_of_product).setPrice_of_product(price);
    }
}
