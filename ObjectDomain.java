package clientserver.message;

import clientserver.message.Commands;

public class ObjectDomain
{
    public String getName_of_product() {
        return name_of_product;
    }

    private String name_of_product;

    public String getGroup_of_product() {
        return group_of_product;
    }

    private String group_of_product;

    public int getAmount() {
        return amount;
    }

    private int amount;

    public double getPrice() {
        return price;
    }

    private double price;

    public int getCommand() {
        return command;
    }

    private int command;


    public ObjectDomain(String name_of_product, int command)
    {
        this.name_of_product=name_of_product;
        this.command=command;
    }

    public ObjectDomain(String name_of_product, int amount, int command)
    {
        this.name_of_product=name_of_product;
        this.amount = amount;
        this.command=command;
    }

    public ObjectDomain(String name_of_product, String group_of_product, int command)
    {
        this.name_of_product=name_of_product;
        this.group_of_product = group_of_product;
        this.command=command;
    }

    public ObjectDomain(String name_of_product, double price, int command)
    {
        this.name_of_product=name_of_product;
        this.price = price;
        this.command=command;
    }



}
