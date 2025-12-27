import { useTranslation } from 'react-i18next'

export default function LanguageSwitch() {
  const { i18n } = useTranslation()
  const current = i18n.language

  return (
    <div className="flex items-center gap-2">
      <button
        className={`btn ${current === 'pt-MZ' ? 'btn-primary' : 'btn-secondary'}`}
        onClick={() => i18n.changeLanguage('pt-MZ')}
      >
        PT
      </button>
      <button
        className={`btn ${current === 'en-US' ? 'btn-primary' : 'btn-secondary'}`}
        onClick={() => i18n.changeLanguage('en-US')}
      >
        EN
      </button>
    </div>
  )
}
