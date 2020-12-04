package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.api.service.domain.VisaDetail;
import com.happy.shoppingcart.common.entities.Currency;
import com.happy.shoppingcart.common.entities.LoyaltyConfig;
import com.happy.shoppingcart.common.entities.ProductTb;
import com.happy.shoppingcart.common.entities.Transaction;
import com.happy.shoppingcart.common.repo.ProductTbRepo;
import com.happy.shoppingcart.common.repo.TransactionRepo;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import com.happy.shoppingcart.api.controller.domain.TransactionGetResponse;
import com.happy.shoppingcart.api.controller.domain.TransactionRequest;
import com.happy.shoppingcart.common.entities.*;
import com.happy.shoppingcart.common.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Slf4j
@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private ShippingRepo shippingRepo;
    @Autowired
    private ShoppingCartRepo shoppingCartRepo;
    @Autowired
    private CurrencyRepo currencyRepo;
    @Autowired
    private ProductTbRepo productTbRepo;
    @Autowired
    private TrackingService trackingService;
    @Autowired
    private LoyaltyConfigRepo loyaltyConfigRepo;

    public TransactionGetResponse getTransactionById(int id) {
        Optional<Transaction> result = transactionRepo.findById(id);
        Transaction res = result.get();
        TransactionGetResponse transactionGetResponse = new TransactionGetResponse(
                res.getPoint(),
                res.getTotal().doubleValue(),
                res.getShippingFee()
        );
        return transactionGetResponse;
    }

    public int createTransaction(TransactionRequest requestBody) {
        
        int cartId = requestBody.getCartId();
        int shippingId = requestBody.getShippingId();
        int productId;
        BigDecimal total;
        BigDecimal shippingRate;
        BigDecimal net;
        int point;
        
        // Get Cart content.
        Optional<ShoppingCart> shoppingCart = shoppingCartRepo.findById(cartId);
        productId = shoppingCart.get().getProductId();
        // Get Product info.
        Optional<ProductTb> product = productTbRepo.findById(productId);
        // Get Shipping info.
        Optional<Shipping> shipping = shippingRepo.findById(shippingId);
        shippingRate = shipping.get().getShippingRate();
        // Get currency info.
        Currency currency = currencyRepo.findByCurrencyCode("USD");
        // Get loyalty config.
        Optional<LoyaltyConfig> loyaltyConfig = loyaltyConfigRepo.findById(1);
        // Calculate totals = Product price * exchange rate.
        total = product.get().getPrice().multiply(currency.getExcRate());
        // Calculate point = totals / point rate.
        net = total.add(shippingRate);
        point = total.divide(BigDecimal.valueOf(loyaltyConfig.get().getPointRate()))
                .setScale(0, RoundingMode.FLOOR).intValue();
        // Create transaction record.
        Transaction transaction = new Transaction();
        transaction.setAddr1(requestBody.getAddress1());
        transaction.setAddr2(requestBody.getAddress2());
        transaction.setAddr3(requestBody.getPostCode() + "");
        transaction.setCountry(requestBody.getCountry());
        transaction.setMobileNumber(requestBody.getMobileNumber());
        transaction.setShippingId(shippingId);
        transaction.setCartId(cartId);
        transaction.setTotal(net);
        transaction.setPoint(point);
        transaction.setShippingId(shippingId);
        transaction.setShippingFee(shippingRate.intValue());
        transaction.setStatus("created");
        
        // Save transaction.
        return transactionRepo.save(transaction).getTransactionId();
    }

    public BigDecimal getTotalFromTransaction(int tnxId){
        Transaction transaction = transactionRepo.getOne(tnxId);
        BigDecimal total = transaction.getTotal();
        return total;
    }

    public void updateTransaction(int tnxId){
        Transaction transaction = transactionRepo.getOne(tnxId);
        transaction.setStatus("success");
        transactionRepo.save(transaction);
    }

    public void updateProductQuantity(int productId){
        ProductTb productTb = productTbRepo.getOne(productId);
        int quantity = productTb.getQuantity()-1;
        productTb.setQuantity(quantity);
        productTbRepo.save(productTb);
    }

    public String createTrackingNumber(VisaDetail requestBody) throws JSONException {
        Optional<Transaction> transaction = transactionRepo.findById(requestBody.getTransactionId());
        log.info("Result [{}]", transaction.get());
        String trackingNumber = trackingService.createTrackingNumber(transaction.get().getAddr1()
                , transaction.get().getAddr2()
                , transaction.get().getPostCode()
                , transaction.get().getCountry()
                , transaction.get().getMobileNumber());
        return trackingNumber;
    }

}
