import type { Metadata } from "next";
import { Comic_Neue, Nunito } from "next/font/google";
import "./globals.css";

/* Fonte lúdica para títulos e textos infantis */
const comicNeue = Comic_Neue({
  variable: "--font-comic-neue",
  subsets: ["latin"],
  weight: ["400", "700"],
});

/* Fonte secundária para textos corridos */
const nunito = Nunito({
  variable: "--font-nunito",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "Play Learn Grow 🎮🌈",
  description:
    "Jogo educativo para crianças de 2–5 anos, focado em cognição, psicologia infantil e aprendizagem inclusiva.",
  keywords: [
    "educação infantil",
    "jogo educativo",
    "cores",
    "números",
    "alfabeto",
    "UNICEF",
    "OMS",
  ],
  authors: [{ name: "Play Learn Grow Team" }],
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="pt">
      <body
        className={`${comicNeue.variable} ${nunito.variable} antialiased bg-[var(--background)] text-[var(--foreground)]`}
      >
        {children}
      </body>
    </html>
  );
}
