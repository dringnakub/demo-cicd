import React from 'react'
import { Container, Row, Button, Col, Form } from 'react-bootstrap'
import fetch from 'isomorphic-unfetch'
import Cookies from 'js-cookie'
import Route from 'next/router'
import ItemProduct from '../components/ItemProduct'
import axios from 'axios'


export default class ConfirmOrder extends React.Component {
  constructor(props) {
    super(props)

    // this.submitOrder = this.submitOrder.bind(this)
    this.submitForm = this.submitForm.bind(this)
  }

  state = {
    form: {
      address1: "",
      address2: "",
      postcode: "",
      country: "",
      tel: ""
    },
    cart: {
      payload: []
    }
  }

  componentDidMount() {
    axios.get("http://localhost:4000/products_add_cart").then((result) => {
      console.log('result', result)
      this.setState({
        cart: result.data
      })
    })
  }

  submitForm(event) {
    event.preventDefault()
    console.log(this.state)
    Route.push("/payment");


  }

  render() {
    return (
      <Container>
        <Row>
          <Col>
            <h4>
              ยืนยันคำสั่งซื้อ
            </h4>
          </Col>
          <hr className="w-100" />
        </Row>
        <Row>
          <Col lg={4}>
            <h6>
              Shipping Method:
            </h6>
          </Col>
        </Row>
        <Row>
          <Col>
            <Form.Check
              defaultChecked={true}
              type="radio"
              label="Kerry"
              id="shipping-item-123"
            />
          </Col>
        </Row>

        <Row >
          <Col lg={4}>
            <h6>
              Shipping Address:
            </h6>
          </Col>
        </Row>
        <Form className="w-100" onSubmit={this.submitForm}>
          <Form.Group as={Row} controlId="ADDR1_TEXTBOX">
            <Form.Label column lg={2}>
              Address1
            </Form.Label>
            <Col lg={6}>
              <Form.Control type="text" value={this.state.address1}
                onChange={e => this.setState({ form: { ...this.state.form, address1: e.target.value } })}
              />
            </Col>
          </Form.Group>

          <Form.Group as={Row} controlId="ADDR2_TEXTBOX">
            <Form.Label column lg={2}>
              Address2
              </Form.Label>
            <Col lg={6}>
              <Form.Control type="text" value={this.state.address2} onChange={e => this.setState({ form: { ...this.state.form, address2: e.target.value } })} />
            </Col>
          </Form.Group>

          <Form.Group as={Row} controlId="POST_TEXTBOX">
            <Form.Label column lg={2}>
              Postcode
            </Form.Label>
            <Col lg={6}>
              <Form.Control type="text" value={this.state.postcode} onChange={e => this.setState({ form: { ...this.state.form, postcode: e.target.value } })} />
            </Col>
          </Form.Group>

          <Form.Group as={Row} controlId="COUNTRY_TEXTBOX">
            <Form.Label column lg={2}>
              Country
            </Form.Label>
            <Col lg={6}>
              <Form.Control type="text" />
            </Col>
          </Form.Group>

          <Form.Group as={Row} controlId="TEL_TEXBOX">
            <Form.Label column lg={2}>
              Tel
            </Form.Label>
            <Col lg={6}>
              <Form.Control type="text" />
            </Col>
          </Form.Group>
          <Row>
            <Col>รายการสินค้า</Col>
          </Row>
          {this.state.cart.payload.map((product) => (
            <Row key={product.id}>
              <Col>
                <ItemProduct className="mt-2" data={product} />
              </Col>
            </Row>
          ))}
          <Row>
            <Col>รายการชำระเงิน</Col>
            <Col>
              <Row>
                <Col>POINT</Col>
                <Col id="point_amount">{this.state.cart.point}</Col>
                <Col>Point</Col>
              </Row>
              <Row>
                <Col>ค่าจัดส่ง</Col>
                <Col id="shipping_amount">40</Col>
                <Col>บาท</Col>
              </Row>
              <Row>
                <Col>รวมทั้งสิ้น</Col>
                <Col id="total_amount">795.985</Col>
                <Col>บาท</Col>
              </Row>
            </Col>
          </Row>
          <Row>
            <Col className="text-right">
              <Button id="submit-shipping-button" type="submit">Next</Button>
            </Col>
          </Row>
        </Form>
      </Container>
    )
  }
}
