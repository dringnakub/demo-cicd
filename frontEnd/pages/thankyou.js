import React from 'react'
import { Col, Row, Container, Button, Image } from 'react-bootstrap'
import Route, { useRouter } from 'next/router'

const PaymentSucess = () => {
  const router = useRouter()
  const { orderId, trackingId } = router.query

  function onClickOkButton() {
    Route.push('/')
  }

  return (
    <Container>
      <Row className="justify-content-md-center">
        <Col sm={4}>
          <h1 id="thankyou">
            {'Thankyou'}
          </h1>
        </Col>
      </Row>

      <Row className="justify-content-md-center">
        <Col sm={12}>
          <Image src="https://www.pngitem.com/pimgs/m/69-692608_transparent-answer-icon-png-check-pass-icon-png.png" width="8%" />
        </Col>
      </Row>

      <Row className="justify-content-md-center">
        <Col sm={4}>
          <p id="success">
            {'Successfully'}
          </p>
        </Col>
      </Row>

      <Row className="justify-content-md-center">
        <Col sm={4}>
          <p id="order_id">
            {`Order ID : ${orderId}`}
          </p>
        </Col>
      </Row>

      <Row className="justify-content-md-center">
        <Col sm={4}>
          <p id="tracking_id">
            {`Tracking ID : ${trackingId}`}
          </p>
        </Col>
      </Row>

      <Row className="justify-content-md-center">
        <Col sm={4}>
          <p id="check_email">
            {'Please check your email'}
          </p>
        </Col>
      </Row>

      <Row className="justify-content-md-center">
        <Col sm={4}>
          <Button id="ok_button" onClick={onClickOkButton}>{'OK'}</Button>
        </Col>
      </Row>
    </Container>
  )
}

export default PaymentSucess
