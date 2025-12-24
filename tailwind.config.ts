import type { Config } from "tailwindcss";
import plugin from "tailwindcss/plugin";

const config: Config = {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./components/**/*.{js,ts,jsx,tsx,mdx}",
    "./app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        background: "var(--background)",
        foreground: "var(--foreground)",
        primary: "var(--color-primary)", // Azul educativo
        secondary: "var(--color-secondary)", // Amarelo alegre
        accent: "var(--color-accent)", // Vermelho destaque
        success: "var(--color-success)", // Verde para acertos
      },
      fontFamily: {
        comic: ["var(--font-comic-neue)", "cursive"],
        nunito: ["var(--font-nunito)", "sans-serif"],
      },
      borderRadius: {
        xl: "1rem",
      },
      keyframes: {
        fadeIn: {
          "0%": { opacity: "0" },
          "100%": { opacity: "1" },
        },
        bounceSlow: {
          "0%, 100%": { transform: "translateY(-5%)" },
          "50%": { transform: "translateY(0)" },
        },
      },
      animation: {
        fadeIn: "fadeIn 0.5s ease-in-out",
        bounceSlow: "bounceSlow 2s infinite",
      },
    },
  },
  plugins: [
    plugin(function ({ addUtilities }) {
      addUtilities({
        ".center-flex": {
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
        },
        ".btn-base": {
          padding: "0.5rem 1rem",
          borderRadius: "0.5rem",
          fontWeight: "600",
          transition: "background-color 0.3s ease",
          backgroundColor: "var(--color-primary)",
          color: "#fff",
        },
      });
    }),
  ],
};

export default config;
