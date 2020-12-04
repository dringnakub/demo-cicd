import React from 'react'
import { Container, Row, Button, Col, Form } from 'react-bootstrap'
import fetch from 'isomorphic-unfetch'
import Cookies from 'js-cookie'
import Route, { Router, withRouter } from 'next/router'
import checkPaymentMethod from '../ecommerce/payment'

export default class Payment extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      form: {
        paymentType: '',
        cardNumber: '',
        expiredMonth: '',
        expiredYear: '',
        cvv: '',
        cardName: '',
      },
      monthList: this.getMonthList(),
      yearList: this.getYearList()

    }
    this.handleChangePaymentType = this.handleChangePaymentType.bind(this)
    this.handleChangeCardNumber = this.handleChangeCardNumber.bind(this)
    this.handleChangeExpiredMonth = this.handleChangeExpiredMonth.bind(this)
    this.handleChangeExpiredYear = this.handleChangeExpiredYear.bind(this)
    this.handleChangeCVV = this.handleChangeCVV.bind(this)
    this.handleChangeCardName = this.handleChangeCardName.bind(this)
  }

  getMonthList() {
    let monthList = []
    for (let index = 1; index < 13; index++) {
      monthList.push(index)
    }
    return monthList
  }

  getYearList() {
    let d = new Date();
    let nowYear = new Date().getFullYear();
    let yearList = []
    for (let index = 0; index < 7; index++) {
      yearList.push(nowYear + index)
    }
    return yearList
  }
  confrimPayment() {
    const {
      paymentType, cardNumber, expiredMonth, expiredYear, cvv, cardName,
    } = this.state
    const cradType = checkPaymentMethod(cardNumber)
    const order = Cookies.getJSON('order')
    const totalPrice = order ? order.total_price : 0
    const orderId = order ? order.order_id : 0

    const request = {
      payment_type: paymentType,
      type: cradType,
      card_number: cardNumber,
      cvv,
      expired_month: parseInt(expiredMonth, 2),
      expired_year: parseInt(expiredYear, 2),
      card_name: cardName,
      total_price: totalPrice,
      order_id: orderId,
    }
    fetch('/api/v1/confirmPayment', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        ...request,
      }),
    })
      .then((r) => r.json())
  }

  handleChangePaymentType(event) {
    this.setState({ paymentType: event.target.value })
  }

  handleChangeCardNumber(event) {
    this.setState({ cardNumber: event.target.value })
  }

  handleChangeExpiredMonth(event) {
    this.setState({ expiredMonth: event.target.value })
  }

  handleChangeExpiredYear(event) {
    this.setState({ expiredYear: event.target.value })
  }

  handleChangeCVV(event) {
    this.setState({ cvv: event.target.value })
  }

  handleChangeCardName(event) {
    this.setState({ cardName: event.target.value })
  }


  submitForm(event) {
    event.preventDefault()
    console.log('submitForm!!')
    Route.push({
      pathname: '/thankyou',
      query: { orderId: "OB3456", trackingId: "12345" },
    })
  }

  render() {

    return (
      <Container>
        <div>
          <Row>
            <h6>
              Summary
            </h6>
          </Row>
          <Row>
            <Col>POINT</Col>
            <Col id="point_amount">12</Col>
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
          <hr className="w-100" />
          <h6>
            Payment Method
          </h6>
          <Row>
            <Col>
              <Form.Check
                defaultChecked={true}
                type="radio"
                label="VISA Card"
                id="visa-radio-button"
              />
            </Col>
          </Row>
          <Form className="w-100" onSubmit={this.submitForm}>
            <Form.Group as={Row} controlId="visa-placeholder">
              <Form.Label column lg={3}>
                Name
            </Form.Label>
              <Col lg={6}>
                <Form.Control type="text" value={this.state.cardName}
                  onChange={e => this.setState({ form: { ...this.state.form, cardName: e.target.value } })}
                />
              </Col>
            </Form.Group>

            <Form.Group as={Row} controlId="visa-number">
              <Form.Label column lg={3}>
                Card Number
              </Form.Label>
              <Col lg={6}>
                <Form.Control type="text" value={this.state.cardNumber} onChange={e => this.setState({ form: { ...this.state.form, cardNumber: e.target.value } })} />
              </Col>
            </Form.Group>

            <Form.Group as={Row} controlId="visa-cvv">
              <Form.Label column lg={3}>
                CVV
              </Form.Label>
              <Col lg={6}>
                <Form.Control type="text" value={this.state.cvv} onChange={e => this.setState({ form: { ...this.state.form, cvv: e.target.value } })} />
              </Col>
            </Form.Group>

            <Form.Group as={Row} controlId="visa-expire">
              <Form.Label column lg={3}>
                EXP
              </Form.Label>
              <Col lg={2}>
                <Form.Control as="select" custom id="visa-expire-month">
                  {this.state.monthList.map((itemMonth, index) => {
                    return <option key={index}>{itemMonth}</option>
                  })}

                </Form.Control>
              </Col>
              <Col lg={2}>
                <Form.Control as="select" custom id="visa-expire-year">
                  {this.state.yearList.map((itemYear, index) => {
                    return <option key={index}>{itemYear}</option>
                  })}
                </Form.Control>
              </Col>
            </Form.Group>
            <Row>
              <Col className="text-right">
                <Button id="pay-button" type="submit">PAY</Button>
              </Col>
            </Row>
          </Form>
        </div>
      </Container>
    )
  }
}