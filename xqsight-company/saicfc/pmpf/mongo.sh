#!/bin/sh
#
JETTY_HOME=/home/payment/jettymanage

rm -rf $JETTY_HOME/webapps/app-pmpf-mongodb-service
unzip -o -d $JETTY_HOME/webapps/app-pmpf-mongodb-service/ ~/app-pmpf-mongodb-service.war

sudo service jettymanage restart
#cp ~/jdbc.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/
#cp ~/global.app.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/

