import React from "react";
import PropTypes from "prop-types";
import { Button, Card, Col, Row } from "react-bootstrap";

function CardProduct({ data, onClickAddToCart }) {
  return (
    <Card>
      <Card.Body>
        <div className="card-img">
          <img
            src={data.image}
            className="card-img"
          />
        </div>
        <h5>{data.name}</h5>
        <p>{data.price}</p>
        <Button
          id={`add-product-${data.id}`}
          variant="primary"
          onClick={onClickAddToCart}
        >
          Add To Cart
        </Button>
      </Card.Body>
    </Card>
  );
}

CardProduct.propTypes = {
  data: PropTypes.object,
  onClickAddToCart: PropTypes.func,
};

CardProduct.defaultProps = {
  data: {},
};

export default CardProduct;
