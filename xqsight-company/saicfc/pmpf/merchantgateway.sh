#!/bin/sh
#
JETTY_HOME=/home/payment/jetty01
SERVICE=jetty01
APPNAME=merchantgateway
WARNAME=merchantgateway

default() {
	sudo service $SERVICE stop
	rm -rf $JETTY_HOME/webapps/"$APPNAME"
	unzip -o -d $JETTY_HOME/webapps/"$APPNAME"/ ~/"$WARNAME".war
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

