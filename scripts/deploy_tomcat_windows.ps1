param(
  [string]$WarPath = ".\PrivateCRSFinalV4.war"
)
if (-not $env:CATALINA_HOME) {
  Write-Error "CATALINA_HOME is not set. Set it to your Tomcat 9 folder."; exit 1
}
if (-not (Test-Path $WarPath)) {
  Write-Error "WAR not found at $WarPath"; exit 1
}
$webapps = Join-Path $env:CATALINA_HOME "webapps"
Remove-Item (Join-Path $webapps "ROOT") -Recurse -Force -ErrorAction SilentlyContinue
Remove-Item (Join-Path $webapps "ROOT.war") -Force -ErrorAction SilentlyContinue
Copy-Item $WarPath (Join-Path $webapps "ROOT.war")
& (Join-Path $env:CATALINA_HOME "bin\startup.bat")
Write-Host "Tailing catalina.out (Ctrl+C to stop)"
Get-Content (Join-Path $env:CATALINA_HOME "logs\catalina.out") -Wait
