CREATE TABLE shopping_cart (
    cart_id INT NOT NULL AUTO_INCREMENT,
    product_id INT NOT  NULL,
    created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (cart_id)
)
;
