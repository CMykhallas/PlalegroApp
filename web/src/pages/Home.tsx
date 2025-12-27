import { useTranslation } from 'react-i18next'
import { useQuery } from '@tanstack/react-query'
import { fetchContents } from '../services/api'
import Button from '../components/Button'

export default function Home() {
  const { t } = useTranslation()
  const { data, isLoading, error, refetch } = useQuery({
    queryKey: ['contents'],
    queryFn: fetchContents
  })

  return (
    <section>
      <h1 className="text-2xl font-bold mb-4">{t('welcome')}</h1>
      <Button onClick={() => refetch()}>Atualizar conteúdos</Button>

      {isLoading && <p className="mt-4">Carregando...</p>}
      {error && <p className="mt-4 text-red-600">Erro ao carregar conteúdos.</p>}

      <ul className="mt-4 space-y-2">
        {data?.map((c) => (
          <li key={c.id} className="bg-white rounded p-3 shadow-sm flex justify-between">
            <span>{c.title}</span>
            <span className="text-gray-500">Nível {c.level}</span>
          </li>
        )) ?? <li className="text-gray-500">Nenhum conteúdo disponível.</li>}
      </ul>
    </section>
  )
}
