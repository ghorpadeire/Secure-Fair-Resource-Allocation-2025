# VS Code Tomcat Workflow (No Deprecated Extensions)

## Prereq
- Set environment variable `CATALINA_HOME` to your Tomcat 9 folder.
- Ensure your WAR is at `./PrivateCRSFinalV4.war` (or provide a path when prompted).

## Tasks
- **Deploy WAR as ROOT.war** → copies your WAR to `${CATALINA_HOME}/webapps/ROOT.war`
- **Start Tomcat** → runs Tomcat normally
- **Start Tomcat (Debug 5005)** → starts Tomcat with JPDA debug on port 5005
- **Stop Tomcat** → stops Tomcat
- **Smoke: curl /** → quick HTTP 200 check

Open VS Code → `Terminal` → `Run Task…` (Ctrl/Cmd+Shift+P) → choose a task.

## Debugging
1. Run **Start Tomcat (Debug 5005)** task.
2. Go to Run and Debug panel → **Attach to Tomcat (5005)**.
3. Set breakpoints in your Java code; requests will hit them.

## Notes
- If you run on Windows, the tasks use PowerShell equivalents.
- If the Deploy task can't find CATALINA_HOME or the WAR, it will fail with a clear message.
