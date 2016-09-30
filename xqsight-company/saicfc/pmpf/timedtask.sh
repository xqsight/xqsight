#!/bin/sh
#
JETTY_HOME=/home/payment/jettytimedtask

rm -rf $JETTY_HOME/webapps/app-pmpf-timedtask
unzip -o -d $JETTY_HOME/webapps/app-pmpf-timedtask/ ~/timedtask.war

sudo service jettytimedtask restart
#cp ~/jdbc.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/
#cp ~/global.app.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/

