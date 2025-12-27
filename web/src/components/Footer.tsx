export default function Footer() {
    return (
      <footer className="bg-gray-100">
        <div className="container mx-auto px-4 py-6 text-sm text-gray-600 flex justify-between">
          <span>&copy; {new Date().getFullYear()} PlayLearn. Todos os direitos reservados.</span>
          <span>v1.0.0</span>
        </div>
      </footer>
    )
  }
  