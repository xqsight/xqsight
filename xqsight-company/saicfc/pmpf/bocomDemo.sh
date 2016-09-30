#!/bin/sh
#
JETTY_HOME=/home/payment/jettybocom
SERVICE=jettybocom
APPNAME=bocompay
WARNAME=bocompay

default() {
	sudo service $SERVICE stop
	rm -rf $JETTY_HOME/webapps/"$APPNAME"
	unzip -o -d $JETTY_HOME/webapps/"$APPNAME"/ ~/bocomDemoWar/"$WARNAME".war
	sudo service $SERVICE start
}

case "$1" in
	restart)
		sudo service $SERVICE restart
		;;
	*)
		default
		;;
esac

