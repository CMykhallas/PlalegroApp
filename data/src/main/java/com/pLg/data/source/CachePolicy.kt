package com.pLg.data.source

enum class CachePolicy {
    CacheFirst,    // retorna cache, atualiza em background
    NetworkFirst,  // tenta rede, cai para cache
    NetworkOnly,   // ignora cache
    CacheOnly      // ignora rede
}
