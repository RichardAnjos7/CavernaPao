# Como corrigir o erro "Could not move temporary workspace" (Gradle)

Esse erro no Windows ocorre quando o Gradle não consegue mover pastas no cache (AccessDenied).  
**Se a limpeza do cache não resolveu**, use a **Solução 1** (cache em outra pasta).

---

## Solução 1 – Usar cache do Gradle em outra pasta (recomendado quando o erro persiste)

Assim o Gradle deixa de usar a pasta do usuário (onde costuma haver bloqueio por antivírus/OneDrive) e usa uma pasta nova, por exemplo em `C:\`.

### Opção mais confiável – Configurar no Android Studio

O Android Studio às vezes não usa a variável de ambiente. Para garantir que use outro cache:

1. Crie a pasta **`C:\GradleCache`** no Explorador de Arquivos.
2. No Android Studio: **File → Settings → Build, Execution, Deployment → Gradle**.
3. No campo **“Gradle user home”**, coloque: **`C:\GradleCache`**.
4. Apply → OK, depois **File → Sync Project with Gradle Files**.

Instruções detalhadas: abra o arquivo **`CONFIGURAR-GRADLE-NO-ANDROID-STUDIO.md`** na pasta do projeto.

### Opção B – Script automático (variável de ambiente)

1. Feche o **Android Studio**.
2. Na pasta **CavernaPao**, clique com o botão direito em **`usar-cache-gradle-em-outra-pasta.bat`**.
3. Escolha **"Executar como administrador"**.
4. Quando terminar, **feche e abra de novo o Android Studio** (para carregar a nova variável).
5. Abra o projeto e faça **File → Sync Project with Gradle Files**.

O script cria `C:\GradleCache` e define a variável de ambiente **GRADLE_USER_HOME** para esse caminho. O próximo sync usará esse cache novo e o erro tende a sumir.

### Opção C – Manual (variável de ambiente)

1. Crie a pasta **`C:\GradleCache`** (ou outro caminho, ex.: `D:\GradleCache`).
2. No Windows: **Configurações → Sistema → Sobre → Configurações avançadas do sistema → Variáveis de ambiente**.
3. Em "Variáveis do usuário", clique em **Novo**:
   - Nome: `GRADLE_USER_HOME`
   - Valor: `C:\GradleCache` (ou o caminho que você criou).
4. Confirme com OK, **feche e abra de novo o Android Studio**, depois faça **File → Sync Project with Gradle Files**.

### Opção D – Só no Android Studio (sem variável global)

1. No Android Studio: **File → Settings** (ou **Ctrl+Alt+S**).
2. **Build, Execution, Deployment → Gradle**.
3. Em **Gradle user home**, troque para: `C:\GradleCache` (crie a pasta antes, se quiser).
4. Aplique (OK) e faça **File → Sync Project with Gradle Files**.

---

## Solução 2 – Limpar o cache na pasta padrão

Use esta opção se quiser continuar usando o cache em `C:\Users\...\.gradle`.

### Passo 1: Fechar tudo que usa Gradle

1. **Feche o Android Studio** (File → Exit).
2. Feche o Cursor ou qualquer IDE que tenha o projeto aberto.
3. No Gerenciador de Tarefas (Ctrl+Shift+Esc), encerre qualquer processo **java.exe**.

### Passo 2: Limpar o cache

- **Script**: na pasta CavernaPao, botão direito em **`limpar-cache-gradle.bat`** → **Executar como administrador**.
- **Manual**: apague a pasta **`C:\Users\richard_oliveira\.gradle\caches\8.13`** (com o Android Studio fechado).

### Passo 3: Depois de limpar

1. Abra o Android Studio, abra o projeto e faça **File → Sync Project with Gradle Files**.

Se o erro voltar, use a **Solução 1** (cache em outra pasta).

---

## Se ainda der erro

- **Antivírus / Windows Defender**: adicione exclusão para a pasta do cache (a que estiver em uso: `.gradle` no usuário ou `C:\GradleCache`).
- **OneDrive**: não deixe a pasta do cache (por exemplo `C:\Users\...\.gradle` ou `C:\GradleCache`) dentro de uma pasta sincronizada pelo OneDrive.
