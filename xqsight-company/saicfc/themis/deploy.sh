#!/bin/bash

d99bill() {
	sudo service themis-99bill stop
	unzip -o themis-3rdparty-99bill-release.zip
	sudo service themis-99bill start
}

dchinaums() {
        sudo service themis-chinaums stop
        unzip -o themis-3rdparty-chinaums-release.zip
        sudo service themis-chinaums start
}

dfraudmetrix() {
	sudo service themis-fraudmetrix stop
    unzip -o themis-3rdparty-fraudmetrix-release.zip
    sudo service themis-fraudmetrix start
}

dcreditchina() {
	sudo service themis-creditchina stop
    unzip -o themis-3rdparty-creditchina-release.zip
    sudo service themis-creditchina start
}

dsms() {
        sudo service themis-sms stop
    unzip -o themis-3rdparty-sms-release.zip
    sudo service themis-sms start
}

ddaemon() {
	sudo service themis-daemon stop
    unzip -o themis-service-daemon-release.zip
    sudo service themis-daemon start
}

dprovider() {
	sudo service themis-provider stop
    unzip -o themis-service-provider-release.zip
    sudo service themis-provider start
}

dhttp() {
        sudo service themis-http stop
    unzip -o themis-service-http-release.zip
    sudo service themis-http start
}


dmanage() {
	sudo service themis-manage stop
	rm -rf /home/themis/themis-manage/webapps/ROOT
	unzip themis-manage-web.war -d /home/themis/themis-manage/webapps/ROOT
	sudo service themis-manage start
}

dtest() {
	sudo service themis-test stop
	unzip -o themis-test-client-release.zip
	sudo service themis-test start
}

case "$1" in
	"99")
		d99bill
	;;
	"cu")
		dchinaums
	;;
	"fm")
		dfraudmetrix
	;;
	"cc")
		dcreditchina
	;;
	"sms")
		dsms
	;;
	"daemon")
		ddaemon
	;;
	"provider")
		dprovider
	;;
	"http")
		dhttp
	;;
	"manage")
		dmanage
	;;
	"test")
		dtest
	;;
	"all")
		d99bill
		dchinaums
		dfraudmetrix
		dcreditchina
		ddaemon	
		dprovider
		dmanage
		dtest
	;;
	*)
		echo "Usage: sh deploy [99|cu|fm|cc|daemon|provider|manage|test]"
	;;
esac
