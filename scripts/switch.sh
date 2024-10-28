#!/usr/bin/env bash

# stop.sh가 속해있는 경로 찾아서 profile.sh 경로 찾기
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
# import와 유사. stop.sh에서 profile.sh의 function 사용
source ${ABSDIR}/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 Port: $IDLE_PORT"
    echo "> Port 전환"
#    하나의 문장을 만들어 파이프라인(|)으로 넘겨주기 위해 echo 사용
#    엔진엑스가 변경할 프록시 주소 생성. 쌍따옴표 사용하지 않으면 $service_url 인식 못하고 변수 찾음
#    생성된 문장을 service-url.inc 덮어씀
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

#   엔진엑스 설정 다시 불러오기. restart는 잠시 끊기지만 reload는 끊김 없음.
#   중요 설정 변경은 restart 해야하지만 url 불러오기는 reload 가능.
    echo "> 엔진엑스 Reload"
    sudo service nginx reload
}