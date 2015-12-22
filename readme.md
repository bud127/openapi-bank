# OpenAPI + OAuth2 Sample
OpenAPI를 생각하고 만든 간단한 OAuth2 샘플입니다. 
테스트를 위해서 3개의 서버를 모두 실행합니다. Spring boot 기반이니 간단히 'spring-boot:run'으로 실행하면 ok.
테스트는 

1. http://localhost:9000/client/index.app (클라이언트, 서버 모두 비로그인 상태)  
2. 로그인 링크 클릭해 로그인 
3. 메인으로 Redirect (클라이언트 로그인 상태) 
4. inquiry 링크 클릭 
5. Auth 서버로 Redirect 후 서버 로그인 
6. 다시 클라이언트로 Redirect
7. Resource 서버로 API 호출이 일어난 후 정상적으로 inquiry 샘플 화면 출력
8. 하면 ok!

가장 많은 참조는 Spring OAuth2 샘플 프로젝트를 참고했습니다. (https://github.com/spring-projects/spring-security-oauth/tree/master/samples/oauth2) 