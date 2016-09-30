#!/bin/sh
#
JETTY_HOME=/usr/local/jetty-distribution-8.1.15.v20140411

sudo service jetty stop
rm -fr $JETTY_HOME/webapps/service/*
unzip -o -d $JETTY_HOME/webapps/service/ ~/payment.war
sudo service jetty start


