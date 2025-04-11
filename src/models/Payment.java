package models;

import java.util.Date;

public class Payment extends BaseModel{
    private Date date;
    private int totalAmount;
    private PaymentStatus status;
    private PaymentMode paymentMode;
    private Bill bill;
}
