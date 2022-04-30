package bean;

import java.sql.Timestamp;

/**
 * @Author聆雨
 * @Date14:01
 * @Version 1.0
 */
public class Order {
    private int id;
    private int mch_id;
    private int out_trade_no;
    private Timestamp order_time;
    private int transaction_id;
    private int user_id;
    private int item_id;
    private float item_price;
    private int amount;
    private String Product_name;
    private float order_price;

    public float getOrder_price() {
        return order_price;
    }

    public void setOrder_price(float order_price) {
        this.order_price = order_price;
    }

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMch_id() {
        return mch_id;
    }

    public void setMch_id(int mch_id) {
        this.mch_id = mch_id;
    }

    public int getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(int out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public Timestamp getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Timestamp order_time) {
        this.order_time = order_time;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public float getItem_price() {
        return item_price;
    }

    public void setItem_price(float item_price) {
        this.item_price = item_price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
