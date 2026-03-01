# Script para corrigir erro "Could not move temporary workspace" / AccessDeniedException do Gradle
# IMPORTANTE: Feche o Android Studio (e Cursor/IDE) antes de executar.

$cache813 = "$env:USERPROFILE\.gradle\caches\8.13"

Write-Host "=== Limpeza do cache Gradle 8.13 ===" -ForegroundColor Cyan
Write-Host ""

# 1. Encerrar todos os processos Java (daemons do Gradle)
Write-Host "Encerrando processos Java (Gradle daemon)..." -ForegroundColor Yellow
Get-Process -Name "java" -ErrorAction SilentlyContinue | Stop-Process -Force -ErrorAction SilentlyContinue
Start-Sleep -Seconds 3

# 2. Remover todo o cache da versao 8.13 (transforms + demais)
if (Test-Path $cache813) {
    Write-Host "Removendo pasta: $cache813" -ForegroundColor Yellow
    try {
        Remove-Item -Path $cache813 -Recurse -Force -ErrorAction Stop
        Write-Host "Cache 8.13 removido com sucesso." -ForegroundColor Green
    } catch {
        Write-Host "Falha ao remover (pasta pode estar em uso): $($_.Exception.Message)" -ForegroundColor Red
        Write-Host ""
        Write-Host "Tente:" -ForegroundColor Yellow
        Write-Host "  1. Fechar Android Studio e qualquer IDE que use o projeto." -ForegroundColor White
        Write-Host "  2. Executar este script clicando com botao direito > 'Executar como administrador'." -ForegroundColor White
        Write-Host "  3. Ou excluir manualmente a pasta em: $cache813" -ForegroundColor White
        exit 1
    }
} else {
    Write-Host "Pasta $cache813 nao encontrada (ja foi removida ou nunca existiu)." -ForegroundColor Gray
}

Write-Host ""
Write-Host "Proximo passo: abra o Android Studio e faca File > Sync Project with Gradle Files." -ForegroundColor Green
Read-Host "Pressione Enter para sair"
