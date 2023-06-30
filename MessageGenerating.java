package clientserver.message;

import clientserver.shop.Shop;

public interface MessageGenerating <T>
{
    T generate(Shop shop, int num_of_command);
}
