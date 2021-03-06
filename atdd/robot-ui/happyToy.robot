*** Settings ***
Library    SeleniumLibrary
*** Test Cases ***
ซื้อของเล่น OMG-Gossip Girl จำนวน 1 ชิ้น ด้วยบัตร Visa ส่งผ่าน Kerry ได้คะแนน 15 points และได้รับการแจ้งเตือนทางอีเมล
    เลือกของเล่น    productId=123    productName=OMG-Gossip Girl
    เอาสินค้าใส่ตะกร้า    productId=123
    คำนวณราคาสินค้า    totalPrice=755.985    totalPoint=7
    เลือกการจัดส่ง    shippingId=123    shippingType=Kerry
    คำนวณค่าจัดส่งสินค้า    shippingPrice=40    total=795.985
    ระบุที่อยู่ในการจัดส่ง    address1=123    address2=Ratchdapisek R. Dindang Bankok    postcode=10120    country=Thailand    tel=0864567891
    คำนวณแต้ม    point=7
    เลือกช่องทางการชำระเงิน    channel=Visa
    ระบุข้อมูลบัตรเครดิต    cardNumber=4555 3413 4907 7109    expiredMonth=3    expiredYear=2025    name=Nareenart    cvv=372
    ลูกค้าจะเห็นเลขออเดอร์    orderId=OB3456    trackingNumber=12345

*** Keywords ***
เลือกของเล่น
    [Arguments]    ${productId}    ${productName}
    Open Browser    http://localhost:3000/    googlechrome
    Wait Until Element Is Visible    locator=add-product-${productId}
เอาสินค้าใส่ตะกร้า
    [Arguments]    ${productId}
    Click Button    locator=add-product-${productId}
คำนวณราคาสินค้า
    [Arguments]    ${totalPrice}    ${totalPoint}
    Wait Until Element Contains    total-amount    text=${totalPrice}
    Wait Until Element Contains    point-amount    text=${totalPoint}
    Click Button    locator=checkout-button
เลือกการจัดส่ง
    [Arguments]    ${shippingId}    ${shippingType}
    Wait Until Element Is Visible    locator=shipping-item-${shippingId}
    Click Button    locator=shipping-item-${shippingId}
คำนวณค่าจัดส่งสินค้า
    [Arguments]    ${shippingPrice}    ${total}
    Wait Until Element Contains    shipping_amount    text=${shippingPrice}
    Wait Until Element Contains    total_amount    text=${total}
ระบุที่อยู่ในการจัดส่ง
    [Arguments]    ${address1}    ${address2}    ${postcode}    ${country}    ${tel}
    Input Text    locator=ADDR1_TEXTBOX    text=${address1}
    Input Text    locator=ADDR2_TEXTBOX    text=${address2}
    Input Text    locator=POST_TEXTBOX    text=${postcode}
    Input Text    locator=COUNTRY_TEXTBOX    text=${country}
    Input Text    locator=TEL_TEXBOX    text=${tel}
    Click Button    locator=submit-shipping-button
คำนวณแต้ม
    [Arguments]    ${point}
    Wait Until Element Contains    point_amount    text=${point}
เลือกช่องทางการชำระเงิน
    [Arguments]    ${channel}
    Wait Until Element Is Visible    locator=visa-radio-button
    Click Button    locator=visa-radio-button
ระบุข้อมูลบัตรเครดิต
    [Arguments]    ${cardNumber}    ${expiredMonth}    ${expiredYear}    ${name}    ${cvv}
    Input Text    locator=visa-placeholder    text=${name}
    Input Text    locator=visa-number    text=${cardNumber}
    Input Text    locator=visa-cvv    text=${cvv}
    Select From List By Value    visa-expire-month    ${expiredMonth}
    Select From List By Value    visa-expire-year    ${expiredYear}
    Click Button    locator=pay-button
ลูกค้าจะเห็นเลขออเดอร์
    [Arguments]    ${orderId}    ${trackingNumber}
    Wait Until Element Contains    locator=order_id   text=${orderId}
    Wait Until Element Contains    locator=tracking_id   text=${trackingNumber}


