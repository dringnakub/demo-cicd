import React from "react";
import { Container, Form, Row, Col, Button, CardDeck } from "react-bootstrap";
import Route from "next/router";
import CardProduct from "../components/CardProduct";

export default class Home extends React.Component {
  state = {
    form: {
      age: "",
      gender: "",
    },
  };

  onSubmitSearch(e) {
    e.preventDefault();
    console.log("onSubmitSearch!!", this.state.form);
  }

  addCart(product) {
    console.log("addCart!!", product);
    Route.push("/cart");
  }

  render() {
    const products = [
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
    ];
    return (
      <Container>
        <Form onSubmit={this.onSubmitSearch.bind(this)}>
          <Form.Row>
            <Form.Group as={Col} controlId="inputAge">
              <Form.Label>Select age</Form.Label>
              <Form.Control
                as="select"
                className="mr-sm-2"
                custom
                defaultValue={this.state.form.age}
                onChange={(e) =>
                  this.setState({
                    form: { ...this.state.form, age: e.target.value },
                  })
                }
              >
                <option value="">Choose</option>
                <option value="1">1-3</option>
                <option value="2">3-10</option>
              </Form.Control>
            </Form.Group>
            <Form.Group as={Col} controlId="inputGender">
              <Form.Label>Select gender</Form.Label>
              <Form.Control
                as="select"
                className="mr-sm-2"
                custom
                defaultValue={this.state.form.gender}
                onChange={(e) =>
                  this.setState({
                    form: { ...this.state.form, gender: e.target.value },
                  })
                }
              >
                <option value="">Choose</option>
                <option value="girl">girl</option>
                <option value="boy">boy</option>
                <option value="unisex">unisex</option>
              </Form.Control>
            </Form.Group>
            <Form.Group as={Col} controlId="submit">
              <Button
                id="search"
                variant="primary"
                type="submit"
                className="btn-custom"
              >
                Search
              </Button>
            </Form.Group>
          </Form.Row>
        </Form>
        <Row>
          {products.map((product) => (
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
