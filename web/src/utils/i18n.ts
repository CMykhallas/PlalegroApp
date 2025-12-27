import i18n from 'i18next'
import { initReactI18next } from 'react-i18next'

const resources = {
  'pt-MZ': {
    translation: {
      welcome: 'Bem-vindo ao PlayLearn',
      terms: 'Termos de Serviço',
      privacy: 'Política de Privacidade'
    }
  },
  'en-US': {
    translation: {
      welcome: 'Welcome to PlayLearn',
      terms: 'Terms of Service',
      privacy: 'Privacy Policy'
    }
  }
}

i18n.use(initReactI18next).init({
  resources,
  lng: 'pt-MZ',
  fallbackLng: 'en-US',
  interpolation: { escapeValue: false }
})

export default i18n
