@ECHO OFF
TITLE ����һ������
color 17
goto TASK1
@rem ��������������������������������������������������������������������������
:TASK1
ECHO.
ECHO. 			һ������ �汾��1.5
ECHO.
ECHO. 			forge-1.12.2-14.23.5.2768-mdk
ECHO.		������������������������������������������
ECHO.
ECHO.		�� �û�һ������ ��
ECHO.
ECHO.		�� ��ѹ���� ��
ECHO.						BY:�����ү
ECHO.
ECHO.		������������������������������������������
ECHO.
ECHO.
ECHO.
ECHO.		�������һ������������
PAUSE >nul
goto TASK2
@rem ��������������������������������������������������������������������������
@rem ��������������������������������������������������������������������������
:TASK2
CLS
color 7
zip\rar.exe x "zip\������ѹ��.zip" ".gradle" "C:%homepath%\"
ECHO.
ECHO.
ECHO.		������������������������������������������
ECHO.
ECHO.
ECHO.		����ѹ�����ɹ� ��
ECHO.
ECHO.
ECHO.		������������������������������������������
ECHO.
ECHO.
java -Dorg.gradle.appname=%~n0 -classpath gradle\wrapper\gradle-wrapper.jar org.gradle.wrapper.GradleWrapperMain setupDecompWorkspace
java -Dorg.gradle.appname=%~n0 -classpath gradle\wrapper\gradle-wrapper.jar org.gradle.wrapper.GradleWrapperMain eclipse
java -Dorg.gradle.appname=%~n0 -classpath gradle\wrapper\gradle-wrapper.jar org.gradle.wrapper.GradleWrapperMain idea
ECHO.
ECHO.
ECHO.
ECHO.		���������һ����
PAUSE >nul
goto TASK3
@rem ��������������������������������������������������������������������������
@rem ��������������������������������������������������������������������������
:TASK3
CLS
color 2
ECHO.		������������������������������������������
ECHO.
ECHO. 			�����ɹ� ��
ECHO.
ECHO.		������������������������������������������
ECHO.
ECHO.
ECHO.
ECHO.		��������˳���
PAUSE >nul
@rem ��������������������������������������������������������������������������