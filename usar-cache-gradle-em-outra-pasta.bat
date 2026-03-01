@echo off
chcp 65001 >nul
echo ============================================================
echo   Gradle: usar cache em outra pasta (contorna o erro de move)
echo ============================================================
echo.

set "NOVO_CACHE=C:\GradleCache"
echo O cache do Gradle sera usado em: %NOVO_CACHE%
echo (Assim evitamos bloqueios na pasta do usuario.)
echo.

if not exist "%NOVO_CACHE%" (
    echo Criando pasta %NOVO_CACHE% ...
    mkdir "%NOVO_CACHE%"
    echo Pasta criada.
) else (
    echo A pasta ja existe.
)
echo.

echo Definindo variavel de ambiente GRADLE_USER_HOME ...
setx GRADLE_USER_HOME "%NOVO_CACHE%"
if errorlevel 1 (
    echo ERRO ao definir variavel. Execute este arquivo como Administrador.
    pause
    exit /b 1
)
echo.
echo Variavel GRADLE_USER_HOME definida com sucesso.
echo.
echo *** IMPORTANTE ***
echo 1. Feche o Android Studio agora (se estiver aberto).
echo 2. Abra o Android Studio de novo.
echo 3. Abra o projeto e faca: File ^> Sync Project with Gradle Files.
echo.
echo O Gradle passara a usar %NOVO_CACHE% e o erro deve parar.
echo.
pause
