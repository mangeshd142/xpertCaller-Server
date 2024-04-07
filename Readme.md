1. Install Java
2. Install Maven
3. Install postgress detabase
4. Install elastic search
5. Add new variable for JAVA_HOME and MAVEN_HOME
6. Add new path in path variable for java and maven
    %JAVA_HOME%\bin
    %MAVEN_HOME%\bin

7. go to consultant app open command prompt on consultant app folder
    and run bellow command
    -> Build Application mvn clean install
    -> Run Application : mvn -f xpertCaller-build/pom.xml spring-boot:run
    -> Run application in debug Mode : mvn -f xpertCaller-build/pom.xml spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:5005"
       application will ge started
    -> or copy war from target folder of xpertCaller-build and it to application server config folder, like add to Tomcat/webapps

URLS

Elastic search url : http://127.0.0.1:9200/


## Installations
    # To set JAVA version 
        set JAVA_HOME=C:/Java/jdk-22
        set PATH=C:/Java/jdk-22/bin;%PATH%
        