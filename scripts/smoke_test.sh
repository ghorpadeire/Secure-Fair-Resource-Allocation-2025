#!/usr/bin/env bash
set -euo pipefail
curl -I http://localhost:8080/ || exit 1
echo "Smoke OK"
