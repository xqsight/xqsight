#!/bin/sh
#
JETTY_HOME=/home/payment/jettywalletapi

rm -rf $JETTY_HOME/webapps/wallet-api
unzip -o -d $JETTY_HOME/webapps/wallet-api/ ~/wallet-api.war

sudo service jettywalletapi restart
#cp ~/jdbc.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/
#cp ~/global.app.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/
