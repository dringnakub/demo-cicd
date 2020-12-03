import React from "react";
import { Container, Form, Row, Col, Button, CardDeck } from "react-bootstrap";
import Route from "next/router";
import ItemProduct from "../components/ItemProduct";

export default class Cart extends React.Component {
  state = {
    products: [
      {
        id: "123",
        image: "17432f12ec88c0d0ea3d0cffc69d25ce.jpg",
        name: "43 Piece Dinner Set",
        price: "12.95 USD",
      },
      {
        id: "1234",
        image: "61uc4bgUPlL._AC_SL1500_.jpg",
        name: "Balance Training Bicycle",
        price: "119.95 USD",
      },
    ],
  }

  onClickCheckout() {
    console.log('onClickCheckout!!', this.state.products)
    Route.push("/order");
  }

  render() {
    return (
      <Container className="pb-4">
        {this.state.products.map((product) => (
          <ItemProduct className="mt-4" data={product} key={product.id} />
        ))}
        <div className="text-right mt-4">
          <div>Point: <span id="point-amount">1324</span></div>
          <div className="mt-2">Total: <span id="total-amount">1324</span></div>
        </div>
        <div className="d-flex justify-content-end mt-4">
          <Button variant="light" type="button" className="mr-4">
            Shopping More
          </Button>
          <Button variant="primary" type="button" id="checkout-button" onClick={this.onClickCheckout.bind(this)}>
            Checkout
          </Button>
        </div>
      </Container>
    );
  }
}
