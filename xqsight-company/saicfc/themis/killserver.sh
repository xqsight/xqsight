#!/bin/bash

kills(){
	echo $1
	local pid=`ps aux |grep "$1"|grep -v "grep"|awk '{ if( $1=="themis") print $2 }'`
	if [[ -z $pid ]]; then
		echo "NOT RUNNING."
	else
		kill -9 $pid
	fi
}

case "$1" in
	"99")
		kills themis-3rdparty-99bill
	;;
	"cu")
		kills themis-3rdparty-chinaums
	;;
	"fm")
		kills themis-3rdparty-fraudmetrix
	;;
	"cc")
		kills themis-3rdparty-creditchina
	;;
	"sw")
		kills themis-3rdparty-sinowaycredit
	;;
	"sms")
		kills themis-3rdparty-sms
	;;
	"provider")
		kills themis-service-provider
	;;
	"daemon")
		kills themis-service-daemon
	;;
	"all")
		kills themis-
	;;
	*)
		echo "Usage: sh killserver.sh [99|cu|fm|cc|sw|provider|daemon]"
	;;
esac
