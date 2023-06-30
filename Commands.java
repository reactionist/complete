package clientserver.message;

public enum Commands
{
    GET_AMOUNT_OF_PRODUCT(0),
    DELETE_SOME_PRODUCTS(1),
    ADD_PRODUCT(2),
    ADD_GROUP_OF_PRODUCT(3),
    ADD_NAME_OF_PRODUCT_TO_GROUP(4),
    SET_PRICE_PRODUCT(5);
    private int num_of_command;
    Commands(int num){
        this.num_of_command = num;
    }
    public int getNum_of_command() {
        return num_of_command;
    }
}