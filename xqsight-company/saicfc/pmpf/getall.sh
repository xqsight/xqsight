. ./setjkenv.sh
DEPLOY_PATH=deploy
wget --auth-no-challenge ${url}app-pmpf-wallet-api/ws/target/wallet-api.war -O ./"$DEPLOY_PATH"/wallet-api.war
wget --auth-no-challenge ${url}app-pmpf-manager/ws/target/pmpf.war -O ./"$DEPLOY_PATH"/pmpf.war
wget --auth-no-challenge ${url}app-pmpf-merchantgateway/ws/target/merchantgateway.war -O ./"$DEPLOY_PATH"/merchantgateway.war
wget --auth-no-challenge ${url}app-pmpf-mongodb-service/ws/target/app-pmpf-mongodb-service.war -O ./"$DEPLOY_PATH"/app-pmpf-mongodb-service.war
wget --auth-no-challenge ${url}app-pmpf-notify/ws/target/app-pmpf-notify.war -O ./"$DEPLOY_PATH"/app-pmpf-notify.war
wget --auth-no-challenge ${url}app-pmpf-recon-manage/ws/target/app-pmpf-recon-manage.war -O ./"$DEPLOY_PATH"/app-pmpf-recon-manage.war
wget --auth-no-challenge ${url}app-pmpf-stub/ws/target/teststub.war -O ./"$DEPLOY_PATH"/teststub.war
wget --auth-no-challenge ${url}app-pmpf-timedtask/ws/target/timedtask.war -O ./"$DEPLOY_PATH"/timedtask.war
wget --auth-no-challenge ${url}app-pmpf-wallet/ws/target/wallet.war -O ./"$DEPLOY_PATH"/wallet.war
wget --auth-no-challenge ${url}app-pmpf-manager/ws/target/pmpf.war -O ./"$DEPLOY_PATH"/pmpf.war
wget --auth-no-challenge ${url}app-pmpf-core-service/ws/target/app-pmpf-core-service.war -O ./"$DEPLOY_PATH"/app-pmpf-core-service.war
wget --auth-no-challenge ${url}app-pmpf-channelgateway/ws/target/channelgateway.war -O ./"$DEPLOY_PATH"/channelgateway.war
wget --auth-no-challenge ${url}app-pmpf-channelgateway-security/ws/target/app-pmpf-channelgateway-security.war -O ./"$DEPLOY_PATH"/app-pmpf-channelgateway-security.war
wget --auth-no-challenge ${url}app-pmpf-refund/ws/target/app-pmpf-refund.war -O ./"$DEPLOY_PATH"/app-pmpf-refund.war
wget --auth-no-challenge ${url}app-pmpf-recon/ws/target/app-pmpf-recon.war -O ./"$DEPLOY_PATH"/app-pmpf-recon.war
wget --auth-no-challenge ${url}app-pmpf-resume-1/ws/target/app-pmpf-resume-1.war -O ./"$DEPLOY_PATH"/app-pmpf-resume-1.war
wget --auth-no-challenge ${url}app-pmpf-resume-2/ws/target/app-pmpf-resume-2.war -O ./"$DEPLOY_PATH"/app-pmpf-resume-2.war
