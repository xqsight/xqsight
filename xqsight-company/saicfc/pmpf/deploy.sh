#!/bin/sh
#
JETTY_HOME=$1
APP_NAME=$2
WAR_NAME=$3
JETTY_SERVICE_NAME=$4

clear(){
    rm -rf $JETTY_HOME/webapps/$APP_NAME
}

upload(){
    unzip -o -d $JETTY_HOME/webapps/$APP_NAME/ ~/$WAR_NAME
}

start(){
    sudo service $JETTY_SERVICE_NAME start
}

stop(){
    sudo service $JETTY_SERVICE_NAME stop
}

restart(){
    sudo service $JETTY_SERVICE_NAME restart
}


case "$5" in
    clear)
        clear
    ;;
    upload)
        upload
    ;;
    start)
        start
    ;;
    stop)
        stop
    ;;
    restart)
        restart
    ;;
    deploy)
        clear
        upload
        restart
    ;;
    *)
        echo "Usage $0 JETTY_HOME APP_NAME WAP_NAME JETTY_SERVICE_NAME {clear|upload|start|stop|restart|deploy}"
    ;;
esac
