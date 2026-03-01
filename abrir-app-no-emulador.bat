@echo off
rem Abre o app Caverna do Pao no emulador (precisa estar instalado).
rem Pacote: com.docelar.padaria  |  Activity: .ui.MainActivity

set ADB=%LOCALAPPDATA%\Android\Sdk\platform-tools\adb.exe

echo Verificando dispositivo...
"%ADB%" devices

echo.
echo Iniciando o app...
"%ADB%" shell am start -n com.docelar.padaria/.ui.MainActivity

if %ERRORLEVEL% neq 0 (
    echo.
    echo Se o app nao abrir, instale antes pelo Android Studio: Run ^(Shift+F10^) ou Build -^> Build APK e depois: %ADB% install -r app\build\outputs\apk\debug\app-debug.apk
)
pause
