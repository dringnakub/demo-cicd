*** Settings ***
Library    SeleniumLibrary
*** Test Cases ***
ซื้อของเล่น OMG-Gossip Girl จำนวน 1 ชิ้น ด้วยบัตร Visa ส่งผ่าน Kerry ได้คะแนน 15 points และได้รับการแจ้งเตือนทางอีเมล
    เลือกของเล่น    productId=123    productName=OMG-Gossip Girl
    เอาสินค้าใส่ตะกร้า    productId=123
    คำนวณราคาสินค้า    totalPrice=1324    totalPoint=1324
    เลือกการจัดส่ง    shippingId=123    shippingType=Kerry
    คำนวณค่าจัดส่งสินค้า    shippingPrice=40    total=795.985
    ระบุที่อยู่ในการจัดส่งที่    address1=123    address2=Ratchdapisek R. Dindang Bankok    postcode=10120    country=Thailand    mobile=0864567891
    คำนวณแต้ม    point=7    
    เลือกช่องทางการชำระเงินเป็น    channel=Visa
    ระบุข้อมูลบัตรเครดิต    cardNumber=4555 3413 4907 7109    expiredMonth=03/27    name=Nareenart    cvv=372
    ลูกค้าจะเห็นเลขออเดอร์    orderId=OB34567    trackingNumber=111111

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
    [Arguments]    ${total}
    Wait Until Element Is Visible    KERRY_LABEL    text=${shippingPrice}
ระบุที่อยู่ในการจัดส่ง
    [Arguments]    ${cartId}
    Input Text    locator=address1    text=123
    Input Text    locator=address2    text=Ratchdapisek R. Dindang Bankok 
    Input Text    locator=postcode    text=10120
    Input Text    locator=country    text=Thailand
    Input Text    locator=mobile    text=0864567891
    Click Button    locator=SUBMIT_SHIPPING_BUTTON
เลือกช่องทางการชำระเงิน
    [Arguments]    ${channel}
    Wait Until Element Is Visible    KERRY_LABEL    text=${channel}
คำนวณแต้ม
    [Arguments]    ${point}
    Wait Until Element Is Visible   locator=POINT_LABEL    text=${point}
เลือกช่องทางชำระเงิน
    Input Text    locator=cardNumber    text=4555 3413 4907 7109 
    Input Text    locator=expiredMonth    text=03/27
    Input Text    locator=name    text=Nareenart
    Input Text    locator=cvv    text=372
    Click Button    locator=PAY_BUTTON
ลูกค้าจะเห็นเลขออเดอร์
    [Arguments]    ${orderId}    ${trackingNumber}
    Wait Until Element Is Visible    locator=ORDER_ID_LABEL   text=${orderId}
    Wait Until Element Is Visible    locator=TRACKING_NUMBER_LABEL   text=${trackingNumber}


