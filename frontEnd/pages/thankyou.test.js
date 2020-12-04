import PaymentSuccess from './thankyou'
import { Button } from 'react-bootstrap'
import renderer from 'react-test-renderer'
import * as nextRouter from 'next/router'
import { expect } from '@jest/globals'

// setup mock

nextRouter.useRouter = jest.fn()
nextRouter.useRouter.mockImplementation(() => ({
  route: '/thankyou',
  query: {
    orderId: '123',
    trackingId: '456',
  }
}))

nextRouter.default.push = jest.fn()

// test case

describe('PaymentSuccess', () => {
  it('should render correctly', () => {
    // arrange
    const component = renderer.create(
      <PaymentSuccess/>,
    )
    const json = component.toJSON()

    // assert
    expect(json).toMatchSnapshot()
  })

  it('should call for route to index page (/) after click on OK button', () => {
    // arrange
    const component = renderer.create(
      <PaymentSuccess/>,
    )
    const testInstance = component.root

    // action
    testInstance.findByType(Button).props.onClick() // OK button

    // assert
    expect(nextRouter.default.push).toHaveBeenCalledWith('/')
  })
})
