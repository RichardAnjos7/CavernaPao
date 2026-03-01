# Configurar cache do Gradle no Android Studio (para corrigir o erro de build)

O Android Studio **ignora** a variável de ambiente se não tiver sido definida antes do IDE abrir. Para o erro "Could not move temporary workspace" sumir, defina o cache do Gradle **dentro do Android Studio**.

---

## Passo a passo

### 1. Criar a pasta do cache (uma vez)

No Explorador de Arquivos, crie a pasta:

**`C:\GradleCache`**

(Clique com o botão direito → Novo → Pasta → nome: `GradleCache` dentro de `C:\`.)

### 2. Abrir as configurações do Gradle no Android Studio

1. Abra o **Android Studio**.
2. No menu: **File** → **Settings** (no Mac: **Android Studio** → **Preferences**).
3. No painel da esquerda: **Build, Execution, Deployment** → **Gradle**.

### 3. Definir o “Gradle user home”

1. Na seção **Gradle**, procure o campo **“Gradle user home”** (ou **“Service directory path”** em versões mais novas).
2. Troque o caminho que estiver (geralmente `C:\Users\richard_oliveira\.gradle`) para:

   **`C:\GradleCache`**

3. Clique em **Apply** e depois em **OK**.

### 4. Sincronizar o projeto

1. No menu: **File** → **Sync Project with Gradle Files**.
2. Aguarde o sync. O primeiro pode demorar (download das dependências no novo cache).

A partir daí o Gradle passa a usar `C:\GradleCache` e o erro “Could not move temporary workspace” deve parar.

---

## Se não achar “Gradle user home”

- Em algumas versões o texto é **“Service directory path”** ou está em **Build, Execution, Deployment** → **Build Tools** → **Gradle**.
- O campo é um caminho de pasta; use exatamente: **C:\GradleCache**
