/** @type {import('postcss-load-config').Config} */
const config = {
  plugins: {
    tailwindcss: {},
    autoprefixer: {}, // agora funciona porque o pacote está instalado
  },
};

export default config;
