module.exports = (req, res, next) => {


    console.log(req.path);
    if (req.path === '/api/v1/product/calculate') {
        res.jsonp({
            "status_code": "0",
            "status_message": "success",
            "point": "7",
            "total": "755.985",
            "cart_id": "2",
            "payload": [
                {
                    "id": 123,
                    "product_name": "OMG-Grossip Girl",
                    "price": "xx.xx"
                }
            ]
        });
    } else { next(); }

}