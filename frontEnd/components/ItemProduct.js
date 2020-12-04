import React from "react";
import PropTypes from "prop-types";
import { Button, Card, Col, Row } from "react-bootstrap";

function ItemProduct({ className, data }) {
  return (
    <Card className={className} id={'product-item-' + data.id}>
      <Card.Body>
        <Row className="d-flex align-items-center">
          <Col xs={2}>
            <div className="card-img">
              <img src={data.image} className="card-img" />
            </div>
          </Col>
          <Col>
            <h5>{data.product_name}</h5>
          </Col>
          <Col xs={2}>
            <p>{data.price}</p>
          </Col>
        </Row>
      </Card.Body>
    </Card>
  );
}

ItemProduct.propTypes = {
  className: PropTypes.string,
  data: PropTypes.object,
};

ItemProduct.defaultProps = {
  data: {},
};

export default ItemProduct;
