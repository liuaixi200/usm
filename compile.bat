@echo off

rem -- Lipsion
color 1f
:menu
echo   ________________________________________________________________
echo  ^|                                                                ^|
echo  ^|                     Maven  -  控制面板                         ^|
echo  ^|                                                                ^|
echo  ^|  0 - mvn clean                 1 - clean package -D...skip=true^|
echo  ^|  2 - eclipse:e.. -Ddown..      3 - package -D...skip=true      ^|
echo  ^|      -Dwtp..                                                   ^|
echo  ^|  4 - 打包开发环境              5 - 打包测试环境                ^|
echo  ^|  6 - 打包生产环境              7 - mvn install                 ^|
echo  ^|                                                                ^| 
echo  ^|________________________________________________________________^|
:input
set /p input=-^> 请选择: 

if "%input%"== "0" goto clean
if "%input%"== "1" goto clean-package
if "%input%"== "2" goto eclipse
if "%input%"== "3" goto package
if "%input%"== "4" goto dev-package
if "%input%"== "5" goto test-pacakge
if "%input%"== "6" goto prd-package
if "%input%"== "7" goto install
goto end

:clean
echo  # 消除工程编译 #
mvn clean&&pause&&cls&& call compile.bat

:clean-package
echo  # 消除编译并打包 #
mvn clean package -U -Dmaven.test.skip=true &&pause&&cls&& call compile.bat

:eclipse
echo  # 转成Eclipse工程 #
mvn eclipse:clean eclipse:eclipse -DdownloadSources=true &&pause&&cls&& call compile.bat

:package
echo  # 打包 #
mvn package -U -Dmaven.test.skip=true &&pause&&cls&& call compile.bat

pause&&cls&& call compile.bat

:dev-package
mvn clean package -Dmaven.test.skip=true -P dev &&pause&&cls&& call compile.bat

:test-pacakge
mvn clean package -Dmaven.test.skip=true -P test &&pause&&cls&& call compile.bat

:prd-package
mvn clean package -Dmaven.test.skip=true -P prod &&pause&&cls&& call compile.bat

:install
mvn install &&pause&&cls&& call compile.bat

echo 结束
prompt
popd