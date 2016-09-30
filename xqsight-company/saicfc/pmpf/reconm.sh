#!/bin/sh
#
JETTY_HOME=/home/payment/jettyreconmanage

rm -rf $JETTY_HOME/webapps/recon
unzip -o -d $JETTY_HOME/webapps/recon/ ~/app-pmpf-recon-manage.war

sudo service jettyreconm restart
#cp ~/jdbc.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/
#cp ~/global.app.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/

