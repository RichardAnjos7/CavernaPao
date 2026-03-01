@echo off
chcp 65001 >nul
echo === Limpeza do cache Gradle 8.13 ===
echo.
echo Feche o Android Studio antes de continuar.
pause

echo Encerrando processos Java (Gradle daemon)...
taskkill /F /IM java.exe 2>nul
timeout /t 3 /nobreak >nul

set "CACHE=%USERPROFILE%\.gradle\caches\8.13"
if exist "%CACHE%" (
    echo Removendo pasta: %CACHE%
    rd /s /q "%CACHE%"
    if exist "%CACHE%" (
        echo.
        echo Falha: pasta ainda em uso. Feche o Android Studio e execute este arquivo
        echo clicando com botao direito em "Executar como administrador".
        pause
        exit /b 1
    )
    echo Cache 8.13 removido com sucesso.
) else (
    echo Pasta 8.13 nao encontrada.
)

echo.
echo Proximo passo: abra o Android Studio e faca File ^> Sync Project with Gradle Files.
pause
