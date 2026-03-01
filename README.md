# Caverna do Pão — App Android

Aplicativo Android para controle de **vendas**, **pedidos sob encomenda** e **estoque** da Padaria Doce Lar. Desenvolvido em Kotlin como projeto de extensão da disciplina Programação para Dispositivos Móveis em Android (Análise e Desenvolvimento de Sistemas).

## Funcionalidades

- **Dashboard:** acesso rápido a Vendas, Pedidos e Estoque.
- **Vendas:** registro de vendas (descrição, quantidade, valor unitário) e listagem com total.
- **Pedidos:** cadastro de pedidos sob encomenda (cliente, descrição, valor, data de entrega) e marcação como concluído.
- **Estoque:** cadastro de itens (nome, quantidade, quantidade mínima, unidade) e alerta visual quando o estoque está abaixo do mínimo.

## Tecnologias

- **Kotlin**
- **Android SDK** (minSdk 24, targetSdk 34)
- **Room** — persistência local
- **ViewBinding** — binding de layouts
- **Material Design** — componentes de UI
- **Coroutines e Flow** — operações assíncronas

## Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/SEU_USUARIO/DocelarPadaria.git
   cd DocelarPadaria
   ```

2. Abra o projeto no **Android Studio** (recomendado: versão mais recente).

3. Sincronize o Gradle (File → Sync Project with Gradle Files).

4. Execute em um emulador ou dispositivo físico (Run ▶️).

## Estrutura do projeto

```
app/src/main/
├── java/com/docelar/padaria/
│   ├── data/           # Room: entidades, DAOs, Database
│   └── ui/              # Activities e Adapters
├── res/
│   ├── layout/          # XML das telas
│   └── values/          # strings, colors, themes
└── AndroidManifest.xml
```

## Licença

Projeto educacional — uso livre para fins de estudo e extensão universitária.
