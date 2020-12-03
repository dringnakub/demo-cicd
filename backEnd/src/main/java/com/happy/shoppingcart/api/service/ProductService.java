package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.common.entities.ProductDb;
import com.happy.shoppingcart.common.entities.Shipping;
import com.happy.shoppingcart.common.entities.ShoppingCart;
import com.happy.shoppingcart.common.repo.ProductDbRepo;
import com.happy.shoppingcart.common.repo.ShippingRepo;
import com.happy.shoppingcart.common.repo.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ShoppingCartRepo shoppingRepos;
    @Autowired
    private ShippingRepo shippingRepos;
    @Autowired
    private ProductDbRepo productDbRepos;

    private ShoppingCart getCart(int id) {
        Optional<ShoppingCart> result = shoppingRepos.findById(id);
        return result.get();
    }

    private Shipping getShipping(int id) {
        Optional<Shipping> result = shippingRepos.findById(id);
        return result.get();
    }

    private ProductDb getProductListById(int productId) {
        Optional<ProductDb> result = productDbRepos.findById(productId);
        return result.get();
    }

//    public ProductResponse calculate(int shippingId, int cartId) {
//        ProductResponse response = new ProductResponse();
//        ShoppingCart dtCart = this.getCart(cartId);
//        Shipping dtShipping = this.getShipping(shippingId);
//        ProductDb dtProduct = this.getProductListById(dtCart.getProductId());
//        Integer totalWithShipping = dtShipping.getShippingRate() + dtProduct.getPrice();
//        response.setCartId(dtCart.getCartId());
//        response.setMessage("Successfully");
//        response.setPoint(dtProduct.getPrice());
//        response.setTotalWithShip();
//
//        return
//    }

    public List<ProductDb> getProductList(@Nullable Integer age, @Nullable String gender) {

        if (age != null && gender != null) {
            return this.productDbRepos.findByAgeAndGender(age, gender);
        }
        if (age == null && gender == null) {
            return this.productDbRepos.findAll();
        }
        if (age != null) {
            return this.productDbRepos.findByAge(age);
        }
        return this.productDbRepos.findByGender(gender);
    }


}
