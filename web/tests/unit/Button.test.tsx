import { render, screen } from '@testing-library/react'
import Button from '../../src/components/Button'

describe('Button', () => {
  it('renders with primary variant by default', () => {
    render(<Button>Click</Button>)
    const btn = screen.getByRole('button', { name: /click/i })
    expect(btn).toBeInTheDocument()
    expect(btn.className).toContain('btn-primary')
  })

  it('renders with secondary variant', () => {
    render(<Button variant="secondary">Click</Button>)
    const btn = screen.getByRole('button', { name: /click/i })
    expect(btn.className).toContain('btn-secondary')
  })
})
