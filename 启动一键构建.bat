@ECHO OFF
TITLE 启动一键构建
color 17
goto TASK1
@rem 〓〓〓〓〓〓〓〓〓※※※※※※※※※※※※※※※※※※※〓〓〓〓〓〓〓〓〓
:TASK1
ECHO.
ECHO. 			一键构建 版本：1.5
ECHO.
ECHO. 			forge-1.12.2-14.23.5.2768-mdk
ECHO.		〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
ECHO.
ECHO.		※ 用户一键构建 √
ECHO.
ECHO.		※ 解压工具 √
ECHO.						BY:豆焰大爷
ECHO.
ECHO.		〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
ECHO.
ECHO.
ECHO.
ECHO.		按任意键一键构建！！！
PAUSE >nul
goto TASK2
@rem 〓〓〓〓〓〓〓〓〓※※※※※※※※※※※※※※※※※※※〓〓〓〓〓〓〓〓〓
@rem 〓〓〓〓〓〓〓〓〓※※※※※※※※※※※※※※※※※※※〓〓〓〓〓〓〓〓〓
:TASK2
CLS
color 7
zip\rar.exe x "zip\构建的压缩.zip" ".gradle" "C:%homepath%\"
ECHO.
ECHO.
ECHO.		〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
ECHO.
ECHO.
ECHO.		※解压构建成功 √
ECHO.
ECHO.
ECHO.		〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
ECHO.
ECHO.
java -Dorg.gradle.appname=%~n0 -classpath gradle\wrapper\gradle-wrapper.jar org.gradle.wrapper.GradleWrapperMain setupDecompWorkspace
java -Dorg.gradle.appname=%~n0 -classpath gradle\wrapper\gradle-wrapper.jar org.gradle.wrapper.GradleWrapperMain eclipse
java -Dorg.gradle.appname=%~n0 -classpath gradle\wrapper\gradle-wrapper.jar org.gradle.wrapper.GradleWrapperMain idea
ECHO.
ECHO.
ECHO.
ECHO.		按任意键下一步。
PAUSE >nul
goto TASK3
@rem 〓〓〓〓〓〓〓〓〓※※※※※※※※※※※※※※※※※※※〓〓〓〓〓〓〓〓〓
@rem 〓〓〓〓〓〓〓〓〓※※※※※※※※※※※※※※※※※※※〓〓〓〓〓〓〓〓〓
:TASK3
CLS
color 2
ECHO.		〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
ECHO.
ECHO. 			构建成功 √
ECHO.
ECHO.		〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
ECHO.
ECHO.
ECHO.
ECHO.		按任意键退出。
PAUSE >nul
@rem 〓〓〓〓〓〓〓〓〓※※※※※※※※※※※※※※※※※※※〓〓〓〓〓〓〓〓〓