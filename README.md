# spring-jwt 

## CURL to get JWT —
curl — location — request POST ‘http://localhost:9192/authenticate' \
— header ‘Content-Type: application/json’ \
— header ‘Cookie: JSESSIONID=F468057F9BFB5B4BFCD15CA58B416E04’ \
— data-raw ‘{
“userName”: “john”,
“password” : “john”
}’

### Response —
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwiQVBQX05BTUUiOiJTcHJpbmcgU2VjdXJpdHkgQXBwIiwiZXhwIjoxNjM4MzE1NDM3LCJpYXQiOjE2MzgyNzk0Mzd9.Mk4ykXtG3O0DMqCe_O_O9twm-XbRANDcQokjb8wvFFE

## Make the API call
curl — location — request GET ‘http://localhost:9192/' \
— header ‘Content-Type: application/json’ \
— header ‘Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwiQVBQX05BTUUiOiJTcHJpbmcgU2VjdXJpdHkgQXBwIiwiZXhwIjoxNjM4MzE1NDM3LCJpYXQiOjE2MzgyNzk0Mzd9.Mk4ykXtG3O0DMqCe_O_O9twm-XbRANDcQokjb8wvFFE’ \
— header ‘Cookie: JSESSIONID=F468057F9BFB5B4BFCD15CA58B416E04’








