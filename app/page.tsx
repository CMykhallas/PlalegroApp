"use client"

import Image from "next/image"
import Link from "next/link"

export default function Home() {
  return (
    <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
      <main className="flex flex-col gap-8 row-start-2 items-center sm:items-start">
        {/* Logo do projeto */}
        <Image
          src="/logo.png"
          alt="Play Learn Grow logo"
          width={180}
          height={60}
          priority
        />

        {/* Mensagem de boas-vindas */}
        <h1 className="text-3xl font-bold text-blue-700 text-center sm:text-left">
          Play Learn Grow 🎮🌈
        </h1>
        <p className="text-gray-600 text-center sm:text-left">
          Bem-vindo ao jogo educativo para crianças de 2–5 anos! Escolha um jogo
          para começar a aprender brincando.
        </p>

        {/* Menu de jogos */}
        <div className="grid grid-cols-1 sm:grid-cols-3 gap-6 mt-6">
          <Link
            href="/play/colors"
            className="block p-6 rounded-lg bg-red-100 hover:bg-red-200 transition"
          >
            <h2 className="font-bold text-lg">Amigos das Cores</h2>
            <p>Aprenda cores brincando com amigos divertidos.</p>
          </Link>

          <Link
            href="/play/numbers"
            className="block p-6 rounded-lg bg-green-100 hover:bg-green-200 transition"
          >
            <h2 className="font-bold text-lg">Contando Estrelas</h2>
            <p>Descubra os números e conte estrelas brilhantes.</p>
          </Link>

          <Link
            href="/play/letters"
            className="block p-6 rounded-lg bg-blue-100 hover:bg-blue-200 transition"
          >
            <h2 className="font-bold text-lg">Aventura do Alfabeto</h2>
            <p>Explore o alfabeto com personagens mágicos.</p>
          </Link>
        </div>
      </main>

      {/* Rodapé institucional */}
      <footer className="row-start-3 flex gap-6 flex-wrap items-center justify-center text-sm text-gray-500">
        © 2025 Play Learn Grow – Direitos da Criança respeitados (UNICEF/OMS/ONU)
      </footer>
    </div>
  )
}
