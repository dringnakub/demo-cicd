module.exports = (req, res, next) => {

    if (req.path === '/api/v1/product/calculate') {
        res.jsonp({
            "status_code": 0,
            "status_message": "success",
            "point": 7,
            "total": 755.985,
            "cart_id": 2,
            "payload": [
                {
                    "id": 123,
                    "product_name": "OMG-Grossip Girl",
                    "price": 755.985,
                    "image": "https://cf.shopee.co.th/file/b0212ad002b15496391cd08a359c70bf"
                }
            ]
        });
    } else if (req.path === '/api/v1/transaction' && req.method === 'POST') {
        res.jsonp({
            "transaction_id": "10011"
        });
    } else if (req.path === '/api/v1/transaction' && req.method === 'PUT') {
        res.jsonp({
            "order_no": "OB34567",
            "tracking_no": 'SHP4227646'
        });
    } else if (req.path === '/visa/api/verify') {
        res.jsonp({
            "status_code": "00",
            "status_message": "APPROVE",
        });
    }
    else if (req.path === '/kerry/api/create_tracking') {
        res.jsonp({
            "status_code": "0",
            "status_message": "success",
            "tracking_num": 'SHP4227646'
        });
    }






    else { next(); }

}