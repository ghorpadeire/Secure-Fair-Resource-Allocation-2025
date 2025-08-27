#!/usr/bin/env bash
set -euo pipefail
: "${CATALINA_HOME:?Set CATALINA_HOME to your Tomcat 9 directory}"
WAR_PATH="${1:-./PrivateCRSFinalV4.war}"
if [[ ! -f "$WAR_PATH" ]]; then
  echo "WAR not found at $WAR_PATH"; exit 1
fi
echo "Deploying $WAR_PATH to $CATALINA_HOME/webapps/ROOT.war"
rm -rf "$CATALINA_HOME/webapps/ROOT" "$CATALINA_HOME/webapps/ROOT.war" 2>/dev/null || true
cp "$WAR_PATH" "$CATALINA_HOME/webapps/ROOT.war"
"$CATALINA_HOME/bin/startup.sh"
echo "Tailing catalina.out (Ctrl+C to stop)"
tail -f "$CATALINA_HOME/logs/catalina.out"
