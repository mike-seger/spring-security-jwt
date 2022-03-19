# spring-security-jwt

Source: https://prateek-ashtikar512.medium.com/spring-security-jwt-d2c8d83a5f17

## Run the application
```
./gradlew -p app bootRun
```

## Get the JWT
```
jwt=$(curl -X POST 'http://localhost:9191/authenticate' -H 'Content-Type: application/json' -H 'Cookie: JSESSIONID=F468057F9BFB5B4BFCD15CA58B416E04' -d '{ "userName": "john", "password" : "john" }')
echo $jwt
```

### Response
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwiQVBQX05BTUUiOiJTcHJpbmcgU2VjdXJpdHkgQXBwIiwiZXhwIjoxNjM4MzE1NDM3LCJpYXQiOjE2MzgyNzk0Mzd9.Mk4ykXtG3O0DMqCe_O_O9twm-XbRANDcQokjb8wvFFE

## Make the API call
```
curl 'http://localhost:9191/' -H 'Content-Type: application/json' -H "Authorization: Bearer $jwt" -H 'Cookie: SESSIONID=F468057F9BFB5B4BFCD15CA58B416E04'
```

## Run the gateway
```
./gradlew -p gw bootRun
```

http://localhost:9190/
