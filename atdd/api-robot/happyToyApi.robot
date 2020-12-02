*** Settings ***
Library     RequestsLibrary
Library     Collections
Suite Setup    Create Session    ${happy_toy_store}      ${URL}
Suite Teardown    Delete All Sessions
Test Template    Checkout Product

*** Variables ***
${happy_toy_store}
${URL}                         http://localhost:8080
&{CONTENT_TYPE}                Content-Type=application/json
&{ACCEPT}                      Accept=application/json
&{POST_HEADERS}                &{ACCEPT}    &{CONTENT_TYPE}
${CALCULATE_POST}              {
...                                 "shipping_id" : ${shipping_id},
...                                 "cart_id" : ${cart_id}
...                             }
${CREATE_TRANSACTION}           {
...                                 "address1" : "123",
...                                 "address2" : "Ratchadapisek R. Dindang Bangkok",
...                                 "post_code" : "10120",
...                                 "country" : "Thailand",
...                                 "mobile_number" : "0863873913",
...                                 "shipping_id" : ${shipping_id},
...                                 "cart_id" : ${cart_id}
...                             }
${PAYMENT_INFO}           {
...                                 "card_number" : "4555 3413 4907 7109",
...                                 "card_holder" : "Nareenart",
...                                 "cvv" : "372",
...                                 "expire_month" : "03",
...                                 "expire_year" : "27"
...                             }

*** Test Cases ***
Success Case    success_status_code=0
...             success_status_message=success
...             product_id=123
...             expected_price=755.985
...             expected_price_shipping=795.985
...             shipping_id=1
...             shipping_fee=40
...             order_number=OB34567


*** Keywords ***
Checkout Product
    Get Product List       ${success_status_code}    ${success_status_message}
    Add Product To Cart    ${success_status_code}    ${success_status_message}    ${product_id}    ${expected_price}
    Calculate Shipping     ${success_status_code}    ${success_status_message}    ${expected_price_shipping}
    Create Transaction     ${success_status_code}    ${success_status_message}
    Get Transaction        ${success_status_code}    ${success_status_message}    ${shipping_fee}
    Update Transaction

Get Product List
    [Arguments]    ${success_status_code}    ${success_status_message}
    ${productList}=   Get Request    ${happy_toy_store}    /api/v1/product/list    headers=&{ACCEPT}
    Status Should Be  200            ${productList}
    Should Be Equal As Strings       ${productList.json()["status_code"]}    ${success_status_code}
    Should Be Equal As Strings       ${productList.json()["status_message"]}    ${success_status_message}

Add Product To Cart
    [Arguments]   ${success_status_code}    ${success_status_message}    ${product_id}    ${expected_price}
    ${calculatePrice}=   Get Request    ${happy_toy_store}    /api/v1/product/add_cart?product_id=${product_id}    headers=&{ACCEPT}
    Status Should Be  200            ${calculatePrice}
    Should Be Equal As Strings       ${calculatePrice.json()["status_code"]}    ${success_status_code}
    Should Be Equal As Strings       ${calculatePrice.json()["status_message"]}    ${success_status_message}
    Should Be Equal                  ${calculatePrice.json()["total"]}    ${expected_price}
    ${cart_id}=    Get From Dictionary     ${calculatePrice.json()}    cart_id
    Set Test Variable    ${cart_id}    ${cart_id}

Calculate Shipping
    [Arguments]    ${success_status_code}    ${success_status_message}    ${expected_price_shipping}
    ${message}=     Replace Variables    ${CALCULATE_POST}
    ${requestBody}=    To Json    ${message}
    ${calculatePriceShipping}=   Post Request    ${happy_toy_store}    /api/v1/product/calculate    json=${requestBody}    headers=&{ACCEPT}
    Status Should Be    200    ${calculatePriceShipping}
    Should Be Equal As Strings       ${calculatePriceShipping.json()["status_code"]}    ${success_status_code}
    Should Be Equal As Strings       ${calculatePriceShipping.json()["status_message"]}    ${success_status_message}
    Should Be Equal As Strings       ${calculatePriceShipping.json()["cart_id"]}    ${cart_id}
    Should Be Equal                  ${calculatePriceShipping.json()["total_with_ship"]}    ${expected_price_shipping}

Create Transaction
    [Arguments]    ${success_status_code}    ${success_status_message}
    ${message}=     Replace Variables    ${CREATE_TRANSACTION}
    ${requestBody}=    To Json    ${message}
    ${createTransaction}=   Post Request    ${happy_toy_store}    /api/v1/transaction    json=${requestBody}    headers=&{ACCEPT}
    Status Should Be    200    ${createTransaction}
    Should Be Equal As Strings       ${createTransaction.json()["status_code"]}    ${success_status_code}
    Should Be Equal As Strings       ${createTransaction.json()["status_message"]}    ${success_status_message}
    ${transaction_id}=    Get From Dictionary     ${createTransaction.json()}    transaction_id
    Set Test Variable    ${transaction_id}    ${transaction_id}

Get Transaction
    [Arguments]    ${success_status_code}    ${success_status_message}    ${shipping_fee}
    ${getTransaction}=   Get Request    ${happy_toy_store}    /api/v1/product/transaction?transaction_id=${transaction_id}    headers=&{ACCEPT}
    Status Should Be    200    ${getTransaction}
    Should Be Equal As Strings       ${getTransaction.json()["status_code"]}    ${success_status_code}
    Should Be Equal As Strings       ${getTransaction.json()["status_message"]}    ${success_status_message}
    Should Be Equal                  ${getTransaction.json()["total"]}    ${expected_price_shipping}
    Should Be Equal                  ${getTransaction.json()["shipping_fee"]}    ${shipping_fee}

Update Transaction
    [Arguments]    ${success_status_code}    ${success_status_message}    ${order_number}
    ${message}=     Replace Variables    ${PAYMENT_INFO}
    ${requestBody}=    To Json    ${message}
    ${updateTransaction}=   Put Request    ${happy_toy_store}    /api/v1/transaction    json=${requestBody}    headers=&{ACCEPT}
    Status Should Be    200    ${updateTransaction}
    Should Be Equal As Strings       ${updateTransaction.json()["status_code"]}    ${success_status_code}
    Should Be Equal As Strings       ${updateTransaction.json()["status_message"]}    ${success_status_message}
    Should Be Equal As Strings       ${updateTransaction.json()["order_no"]}    ${order_number}




