import React from "react";
import { Container, Form, Row, Col, Button, CardDeck } from "react-bootstrap";
import Route from "next/router";
import CardProduct from "../components/CardProduct";
import ItemProduct from "../components/ItemProduct";
import axios from "axios";

export default class Home extends React.Component {
  state = {
    products: [],
    cart: null,
  };

  onClickCheckout() {
    Route.push(`/order?cart_id=${this.state.cart.cart_id}`);
  }

  addCart(product) {
    axios
      .get(
        `http://localhost:4000/api/v1/products/add_cart?product_id=${product.id}`
      )
      .then(({ data }) => {
        this.setState({ cart: data });
      });
  }

  UNSAFE_componentWillMount() {
    axios.get("http://localhost:4000/api/v1/products/list").then(({ data }) => {
      this.setState({ products: data });
    });
  }

  render() {
    if (this.state.cart) {
      return (
        <Container className="pb-4">
          {this.state.cart.payload.map((product) => (
            <ItemProduct className="mt-4" data={product} key={product.id} />
          ))}
          <div className="text-right mt-4">
            <div>
              Point: <span id="point-amount">{this.state.cart.point}</span>
            </div>
            <div className="mt-2">
              Total: <span id="total-amount">{this.state.cart.total}</span>
            </div>
          </div>
          <div className="d-flex justify-content-end mt-4">
            <Button
              variant="light"
              type="button"
              className="mr-4"
              onClick={() => this.setState({ cart: null })}
            >
              Shopping More
            </Button>
            <Button
              variant="primary"
              type="button"
              id="checkout-button"
              onClick={this.onClickCheckout.bind(this)}
            >
              Checkout
            </Button>
          </div>
        </Container>
      );
    } else {
      return (
        <Container>
          <Row>
            {this.state.products.map((product) => (
              <Col xs={4} key={product.id}>
                <CardProduct
                  data={product}
                  onClickAddToCart={() => this.addCart(product)}
                />
              </Col>
            ))}
          </Row>
        </Container>
      );
    }
  }
}
