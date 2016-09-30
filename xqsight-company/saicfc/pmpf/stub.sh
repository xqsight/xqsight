#!/bin/sh
#
JETTY_HOME=/home/payment/jettystub

sudo service jettystub stop
rm -rf $JETTY_HOME/webapps/teststub
unzip -o -d $JETTY_HOME/webapps/teststub/ ~/teststub.war
sudo service jettystub start
#cp ~/jdbc.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/
#cp ~/global.app.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/

