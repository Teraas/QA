package DesignPatterns.Structural.Facade;

import DesignPatterns.example.Invoice;
import DesignPatterns.example.Notification;
import DesignPatterns.example.Payment;
import DesignPatterns.example.Product;

/**
 * @author harish.kumar-mbp
 * @created 21/02/23
 */
public class OrderFacade {
    Product product;
    Payment payment;
    Invoice invoice;
    Notification notif;

    public OrderFacade(Product product, Payment payment, Invoice invoice, Notification notif) {
        this.product = product;
        this.payment = payment;
        this.invoice = invoice;
        this.notif = notif;
    }
    public void createOrder(){
        product.getProduct("sd");
        payment.makePayment();
        invoice.generateInvoice();
        notif.sentNotification("mail");
    }
}
