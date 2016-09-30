if[`cat /opt/logs/pmpf/app-pmpf-merchantgateway.log | grep ' ERROR |Exception'|wc -l` -gt 0];then
	cat cat /opt/logs/pmpf/app-pmpf-merchantgateway.log | grep ' ERROR |Exception'>> 1.txt
fi
