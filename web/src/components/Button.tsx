import { ComponentProps } from 'react'

type Props = ComponentProps<'button'> & { variant?: 'primary' | 'secondary' }

export default function Button({ variant = 'primary', className = '', ...props }: Props) {
  const base = 'btn'
  const variantCls = variant === 'primary' ? 'btn-primary' : 'btn-secondary'
  return <button {...props} className={`${base} ${variantCls} ${className}`} />
}
