@echo off
cd /d "%~dp0"
java -cp "lib\*;classes" com.mynewpackage.ISOMessageHandler
pause

