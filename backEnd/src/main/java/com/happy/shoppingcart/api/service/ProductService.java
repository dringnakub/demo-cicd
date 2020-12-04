package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.common.entities.ProductTb;
import com.happy.shoppingcart.common.entities.Shipping;
import com.happy.shoppingcart.common.entities.ShoppingCart;
import com.happy.shoppingcart.common.repo.ProductTbRepo;
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
    private ProductTbRepo productTbRepos;

    private ShoppingCart getCart(int id) {
        Optional<ShoppingCart> result = shoppingRepos.findById(id);
        return result.get();
    }

    private Shipping getShipping(int id) {
        Optional<Shipping> result = shippingRepos.findById(id);
        return result.get();
    }

    private ProductTb getProductListById(int productId) {
        Optional<ProductTb> result = productTbRepos.findById(productId);
        return result.get();
    }

//    public ProductResponse calculate(int shippingId, int cartId) {
//        ProductResponse response = new ProductResponse();
//        ShoppingCart dtCart = this.getCart(cartId);
//        Shipping dtShipping = this.getShipping(shippingId);
//        ProductTb dtProduct = this.getProductListById(dtCart.getProductId());
//        Integer totalWithShipping = dtShipping.getShippingRate() + dtProduct.getPrice();
//        response.setCartId(dtCart.getCartId());
//        response.setMessage("Successfully");
//        response.setPoint(dtProduct.getPrice());
//        response.setTotalWithShip();
//
//        return
//    }

    public List<ProductTb> getProductList(@Nullable Integer age, @Nullable String gender) {

        if (age != null && gender != null) {
            return this.productTbRepos.findByAgeAndGender(age, gender);
        }
        if (age == null && gender == null) {
            return this.productTbRepos.findAll();
        }
        if (age != null) {
            return this.productTbRepos.findByAge(age);
        }
        return this.productTbRepos.findByGender(gender);
    }


}
