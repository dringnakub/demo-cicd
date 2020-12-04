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
    const component = renderer.create(
      <PaymentSuccess/>,
    )
    const json = component.toJSON()
    expect(json).toMatchSnapshot()
  })

  it('should route to index page after click OK button', () => {
    const component = renderer.create(
      <PaymentSuccess/>,
    )
    const testInstance = component.root
    testInstance.findByType(Button).props.onClick()

    expect(nextRouter.default.push).toHaveBeenCalledWith('/')
  })
})
