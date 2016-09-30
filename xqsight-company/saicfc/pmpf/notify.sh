#!/bin/sh
#
JETTY_HOME=/home/payment/jettynotify

rm -rf $JETTY_HOME/webapps/app-pmpf-notify
unzip -o -d $JETTY_HOME/webapps/app-pmpf-notify/ ~/app-pmpf-notify.war

sudo service jettynotify restart
#cp ~/jdbc.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/
#cp ~/global.app.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/

