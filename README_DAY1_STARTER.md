# Project Setup (Day 1)

## Prerequisites
- Java 21, Git, IntelliJ or VS Code
- Tomcat 9 (set `CATALINA_HOME`)
- MariaDB 11 or Docker
- Postman / curl

## Database
**Local MariaDB**
```bash
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS private_deduplication;"
mysql -u root -p private_deduplication < private_deduplication.sql
```

**Docker MariaDB**
```bash
docker run -d --name scs-db -e MARIADB_ROOT_PASSWORD=change-me -e MARIADB_DATABASE=private_deduplication -p 3306:3306 mariadb:11
docker cp private_deduplication.sql scs-db:/tmp/schema.sql
docker exec -it scs-db mariadb -uroot -pchange-me private_deduplication < /tmp/schema.sql
```

## Run on Tomcat
Copy `PrivateCRSFinalV4.war` → `${CATALINA_HOME}/webapps/ROOT.war`, then start Tomcat.

- macOS/Linux:
```bash
$CATALINA_HOME/bin/startup.sh
tail -f $CATALINA_HOME/logs/catalina.out
```

- Windows (PowerShell):
```powershell
& "$env:CATALINA_HOME\bin\startup.bat"
Get-Content "$env:CATALINA_HOME\logs\catalina.out" -Wait
```

Open: http://localhost:8080/

## Env Example (Day 2)
Create `.env` (do not commit):
```
DB_HOST=localhost
DB_PORT=3306
DB_NAME=private_deduplication
DB_USER=root
DB_PASS=change-me
```
