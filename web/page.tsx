"use client";

import { useState } from "react";

export default function Home() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");

  async function registerUser() {
    const res = await fetch("http://localhost:8080/registerUser", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name, email }),
    });
    const data = await res.json();
    if (data.error) setMessage("Erro: " + data.error);
    else setMessage("Usuário registrado: " + data.name);
  }

  return (
    <main style={{ padding: 20 }}>
      <h1>Plalegro App</h1>
      <input
        value={name}
        onChange={(e) => setName(e.target.value)}
        placeholder="Nome"
      />
      <input
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        placeholder="Email"
      />
      <button onClick={registerUser}>Registrar</button>
      <p>{message}</p>
    </main>
  );
}
