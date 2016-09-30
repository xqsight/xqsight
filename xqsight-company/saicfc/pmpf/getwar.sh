. ./setjkenv.sh
wget --auth-no-challenge ${url}app-pmpf-manager/ws/target/pmpf.war -O ./pmpf.war
wget --auth-no-challenge ${url}app-pmpf-notify/ws/target/app-pmpf-notify.war -O ./app-pmpf-notify.war
wget --auth-no-challenge ${url}app-pmpf-merchantgateway/ws/target/merchantgateway.war -O ./merchantgateway.war
wget --auth-no-challenge ${url}app-pmpf-wallet/ws/target/app-pmpf-wallet.war -O ./app-pmpf-wallet.war
wget --auth-no-challenge ${url}app-pmpf-timedtask/ws/target/timedtask.war -O ./timedtask.war
