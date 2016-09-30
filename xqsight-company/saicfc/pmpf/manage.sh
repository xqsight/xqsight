#!/bin/sh
#
JETTY_HOME=/home/payment/jettymanage9

rm -rf $JETTY_HOME/webapps/pmpf
unzip -o -d $JETTY_HOME/webapps/pmpf/ ~/pmpf.war

sudo service jettymanage9 restart
#cp ~/jdbc.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/
#cp ~/global.app.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/

