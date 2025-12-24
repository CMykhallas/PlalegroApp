import { notFound } from "next/navigation";

export default function PlayPage({ params }: { params: { gameId: string } }) {
  const { gameId } = params;

  if (!["numbers", "colors", "letters"].includes(gameId)) {
    notFound();
  }

  return (
    <main className="p-8">
      <h1 className="text-2xl font-bold">Jogo: {gameId}</h1>
      {/* Renderize o conteúdo do jogo aqui */}
    </main>
  );
}
