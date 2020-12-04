import React from 'react'
import { Container, Row, Button, Col, Form } from 'react-bootstrap'
import Route, { Router, withRouter } from 'next/router'
import checkPaymentMethod from '../ecommerce/payment'
import axios from 'axios'

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
      summary: {
        point: '',
        shipping_fee: '',
        total: ''
      },
      monthList: this.getMonthList(),
      yearList: this.getYearList()

    }
  }

  componentWillMount() {
    axios.get("http://localhost:4000/transaction").then((result) => {
      console.log(result)
      this.setState({ summary: result.data })
    })
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
            <Col id="point_amount">{this.state.summary.point}</Col>
            <Col>Point</Col>
          </Row>
          <Row>
            <Col>ค่าจัดส่ง</Col>
            <Col id="shipping_amount">{this.state.summary.shipping_fee}</Col>
            <Col>บาท</Col>
          </Row>
          <Row>
            <Col>รวมทั้งสิ้น</Col>
            <Col id="total_amount">{this.state.summary.total}</Col>
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

            <Form.Group as={Row} >
              <Form.Label column lg={3}>
                EXP
              </Form.Label>
              <Col lg={2}>
                <Form.Control as="select" custom id="visa-expire-month">
                  {this.state.monthList.map((itemMonth, index) => {
                    return <option key={index} id={index} value={itemMonth}>{itemMonth}</option>
                  })}

                </Form.Control>
              </Col>
              <Col lg={2}>
                <Form.Control as="select" custom id="visa-expire-year">
                  {this.state.yearList.map((itemYear, index) => {
                    return <option key={index} id={index} value={itemYear}>{itemYear}</option>
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