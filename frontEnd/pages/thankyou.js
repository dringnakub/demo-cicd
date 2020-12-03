import React from 'react'
import { Col, Row, Container, Button, Image } from 'react-bootstrap'
import Route, { useRouter } from 'next/router'

const PaymentSucess = () => {
  const router = useRouter()
  const { orderId, trackingId } = router.query

  const textStyle = {
    textAlign: 'center'
  }

  function onClickOkButton() {
    Route.push('/')
  }

  return (
    <Container>
      <Row className="justify-content-md-center">
        <Col>
          <h1 id="thankyou" style={textStyle}>
            {'Thankyou'}
          </h1>
        </Col>
      </Row>

      <Row className="justify-content-md-center">
        <Col style={textStyle}>
          <Image src="https://www.pngitem.com/pimgs/m/69-692608_transparent-answer-icon-png-check-pass-icon-png.png" width="8%" />
        </Col>
      </Row>

      <Row className="justify-content-md-center">
        <Col>
          <p id="success" style={textStyle}>
            {'Successfully'}
          </p>
        </Col>
      </Row>

      <Row className="justify-content-md-center">
        <Col>
          <p id="order_id" style={textStyle}>
            {`Order ID : ${orderId}`}
          </p>
        </Col>
      </Row>

      <Row className="justify-content-md-center">
        <Col>
          <p id="tracking_id" style={textStyle}>
            {`Tracking ID : ${trackingId}`}
          </p>
        </Col>
      </Row>

      <Row className="justify-content-md-center">
        <Col>
          <p id="check_email" style={textStyle}>
            {'Please check your email'}
          </p>
        </Col>
      </Row>

      <Row className="justify-content-md-center">
        <Col sm={4}>
          <Button block id="ok_button" onClick={onClickOkButton}>{'OK'}</Button>
        </Col>
      </Row>
    </Container>
  )
}

export default PaymentSucess
