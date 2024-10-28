#!/usr/bin/env bash

# 쉬고 있는 profile 찾기 : real1 사용 중이면 real2 쉬는 중
function find_idle_profile() {
#  스프링부트가 정상 수행 중인지 확인. 응답값을 HttpStatus로 받기
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if [ ${RESPONSE_CODE} -ge 400 ] # 400 보다 크면 (즉, 40x/50x 에러 모두 포함)
      then
          CURRENT_PROFILE=real2
      else
          CURRENT_PROFILE=$(curl -s http://localhost/profile)
      fi

  if [ ${CURRENT_PROFILE} == real1 ]
      then
        IDLE_PROFILE=real2
      else
        IDLE_PROFILE=real1
      fi

# bash는 값 반환 기능 없음. echo로 결과 출력 후 클라이언트에서 값을 잡아야 함
  echo "${IDLE_PROFILE}"

}