#!/bin/sh
#
JETTY_HOME=/home/payment/jettywallet

rm -rf $JETTY_HOME/webapps/wallet
unzip -o -d $JETTY_HOME/webapps/wallet/ ~/wallet.war

sudo service jettywallet restart
#cp ~/jdbc.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/
#cp ~/global.app.properties $JETTY_HOME/webapps/imonitor/WEB-INF/classes/

