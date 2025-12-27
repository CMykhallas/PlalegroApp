import { Link, NavLink } from 'react-router-dom'
import LanguageSwitch from './LanguageSwitch'

export default function Header() {
  return (
    <header className="bg-white shadow-sm">
      <div className="container mx-auto px-4 py-3 flex items-center justify-between">
        <Link to="/" className="text-xl font-bold text-plum">
          PlayLearn
        </Link>
        <nav className="flex items-center gap-4">
          <NavLink to="/" className="hover:underline">
            Início
          </NavLink>
          <NavLink to="/terms" className="hover:underline">
            Termos
          </NavLink>
          <NavLink to="/privacy" className="hover:underline">
            Privacidade
          </NavLink>
          <LanguageSwitch />
        </nav>
      </div>
    </header>
  )
}
