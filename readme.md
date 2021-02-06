
cd target
jar xf transport_api.war

installation:
1. install java 1.8 > and set java_home in environment
2. install mysql
3. create database transport
4. change the config in application.properties
5. url:  http://localhost:9092/transport/#/dashboard



/usr/local/tomcat8
give permission 755


cd /usr/local/tomcat8
./bin/startup.sh
./bin/shutdown.sh

sh catalina.sh run

