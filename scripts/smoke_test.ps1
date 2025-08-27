try {
  $resp = iwr http://localhost:8080/ -Method Head -UseBasicParsing -TimeoutSec 5
  if ($resp.StatusCode -ge 200 -and $resp.StatusCode -lt 400) {
    Write-Host "Smoke OK"
    exit 0
  } else { exit 1 }
} catch { exit 1 }
