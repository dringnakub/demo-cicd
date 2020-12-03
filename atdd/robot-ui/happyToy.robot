*** Settings ***
Library    SeleniumLibrary
*** Test Cases ***
ซื้อของเล่น OMG-Gossip Girl จำนวน 1 ชิ้น ด้วยบัตร Visa ส่งผ่าน Kerry ได้คะแนน 15 points และได้รับการแจ้งเตือนทางอีเมล
    เลือกของเล่น    productId=123    productName=OMG-Gossip Girl
    เอาสินค้าใส่ตะกร้า จำนวน    productId=123
    คำนวณราคาสินค้าทั้งหมด    totalPrice=755.985
    เลือกการจัดส่งเป็น    shippingType=Kerry
    คำนวณค่าจัดส่งสินค้า    shippingPrice=40    total=795.985
    ระบุที่อยู่ในการจัดส่งที่    address1=123    address2=Ratchdapisek R. Dindang Bankok    postcode=10120    country=Thailand    mobile=0864567891
    คำนวณแต้ม    point=7    
    เลือกช่องทางการชำระเงินเป็น    channel=Visa
    ระบุข้อมูลบัตรเครดิต    cardNumber=4555 3413 4907 7109    expiredMonth=03/27    name=Nareenart    cvv=372
    ลูกค้าจะเห็นเลขออเดอร์    orderId=OB34567    trackingNumber=111111

*** Keywords ***
เลือกของเล่น
    [Arguments]    ${productId}
    Open Browser    http://www.happy.com    googlechrome
    Wait Until Element IS Enabled    locator=id:PRODUCT_IMAGE_${productId}
    Click Button    locator=id:ADD_PRODUCT_BUTTON_${productId}
เอาสินค้าใส่ตะกร้า
    [Arguments]    ${productId}    
    Wait Until Element IS Visble    PRODUCT_TITLE_LABEL    text=${productId}
คำนวณราคาสินค้า
    [Arguments]    ${total}    ${totalPoint}
    Wait Until Element IS Visble    TOTAL_LABEL    text=${total}
    Wait Until Element IS Visble    POINT_LABEL    text=${totalPoint}
    Click Button    locator=CHECKOUT_BUTTON
เลือกการจัดส่ง
    [Arguments]    ${shippingType}
    Wait Until Element IS Visble    KERRY_RADIO_KERRY    text=${shippingType}
คำนวณค่าจัดส่งสินค้า
    [Arguments]    ${total}
    Wait Until Element IS Visble    KERRY_LABEL    text=${shippingPrice}
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
    Wait Until Element IS Visble    KERRY_LABEL    text=${channel}
คำนวณแต้ม
    [Arguments]    ${point}
    Wait Until Element IS Visble   locator=POINT_LABEL    text=${point}
เลือกช่องทางชำระเงิน
    Input Text    locator=cardNumber    text=4555 3413 4907 7109 
    Input Text    locator=expiredMonth    text=03/27
    Input Text    locator=name    text=Nareenart
    Input Text    locator=cvv    text=372
    Click Button    locator=PAY_BUTTON
ลูกค้าจะเห็นเลขออเดอร์
    [Arguments]    ${orderId}    ${trackingNumber}
    Wait Until Element IS Visble    locator=ORDER_ID_LABEL   text=${orderId}
    Wait Until Element IS Visble    locator=TRACKING_NUMBER_LABEL   text=${trackingNumber}


