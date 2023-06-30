package clientserver.message;

import clientserver.shop.Shop;

import java.nio.ByteBuffer;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class MessageGeneration implements MessageGenerating<Message> {
    @Override
    public Message generate(Shop shop, int num_of_command) {
        String message;
        Scanner scanner = new Scanner(System.in);
        switch (num_of_command){
            case 1:
                int id_del = new Random().nextInt(shop.getProducts().size());
                int j = 1;
                String product = null;
                for (String i: shop.getProducts().keySet()){
                    product = i;
                    if (j==id_del) {
                        break;
                    }
                    i+=1;
                }
                int current_amount = new Random().nextInt(shop.getProducts().get(product).getAmount_of_product()) / 2;
                message = product + " " + String.valueOf(current_amount);
                break;
            case 2:
                id_del = new Random().nextInt(shop.getProducts().size());
                j = 1;
                product = null;
                for (String i: shop.getProducts().keySet()){
                    product = i;
                    if (j==id_del) {
                        break;
                    }
                    j+=1;
                }
                current_amount = new Random().nextInt(shop.getProducts().get(product).getAmount_of_product());
                message = product + " " + String.valueOf(current_amount);
                break;
            case 3:
                String[] variant_groups = {"Shoes", "Clothes", "Phones", "Laptops", "Kitchen", "Bedroom", "Sofas", "Bathroom"};
                int num_of_group = new Random().nextInt(variant_groups.length);
                message = variant_groups[num_of_group];
            case 4:
                TreeMap<String, String[]> variant_products = new TreeMap<>();
                variant_products.put("Food", new String[]{"Nachos", "Potato", "Snickers", "Carrot", "Horseradish", "Nesquik"});
                variant_products.put("Drink", new String[]{"Cola", "Vodka", "Revo", "Beer", "Fanta", "Sprite"});
                id_del = new Random().nextInt(variant_products.size());
                j = 1;
                String group = null;
                for (String i: variant_products.keySet()){
                    group = i;
                    if (j==id_del) {
                        break;
                    }
                    j+=1;
                }
                String[] array_of_prod = variant_products.get(group);
                int id_pr = new Random().nextInt(array_of_prod.length);
                message = array_of_prod[id_pr] + " " + group;
                break;
            case 5:
                id_del = new Random().nextInt(shop.getProducts().size());
                j = 1;
                product = null;
                for (String i: shop.getProducts().keySet()){
                    product = i;
                    if (j==id_del) {
                        break;
                    }
                    j+=1;
                }
                double price_ch = Math.round(new Random().nextDouble()*100);
                message = product + " " + price_ch;
                break;
            default:
                id_del = new Random().nextInt(shop.getProducts().size());
                j = 1;
                product = null;
                for (String i: shop.getProducts().keySet()){
                    product = i;
                    if (j==id_del) {
                        break;
                    }
                    j+=1;
                }
                message = product;
                break;
        }
        int cType=num_of_command;
        int bUserId=new Random().nextInt(50);
        ByteBuffer byteBuffer_message = ByteBuffer.allocate(8+message.length());
        byteBuffer_message.putInt(cType).putInt(bUserId).put(message.getBytes());
        return new Message(byteBuffer_message, message.length());
    }
}
