package controller.web;


import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PayPalConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get cart details (you should calculate order details dynamically)
        String successUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/SuccessServlet";
        String cancelUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/CancelServlet";
        String total = request.getParameter("total"); // Total price of the cart

        try {
            APIContext apiContext = PayPalConfig.getAPIContext();

            // Set payment details
            Amount amount = new Amount();
            amount.setCurrency("USD");
            amount.setTotal(total); // Total amount

            Transaction transaction = new Transaction();
            transaction.setDescription("Your purchase from the Clothing Shop");
            transaction.setAmount(amount);

            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);

            // Set payment methods - PayPal
            Payer payer = new Payer();
            payer.setPaymentMethod("paypal");

            Payment payment = new Payment();
            payment.setIntent("sale");
            payment.setPayer(payer);
            payment.setTransactions(transactions);

            // Redirect URLs
            RedirectUrls redirectUrls = new RedirectUrls();
            redirectUrls.setCancelUrl(cancelUrl);
            redirectUrls.setReturnUrl(successUrl);
            payment.setRedirectUrls(redirectUrls);

            // Create payment
            Payment createdPayment = payment.create(apiContext);

            // Redirect user to PayPal approval URL
            List<Links> links = createdPayment.getLinks();
            for (Links link : links) {
                if (link.getRel().equalsIgnoreCase("approval_url")) {
                    response.sendRedirect(link.getHref());
                    return;
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
            response.sendRedirect("templates/error.jsp"); // Redirect to error page if payment fails
        }
    }
}