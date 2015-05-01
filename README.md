# Eclipse configuration
* project: runtime
* main class: com.databrigade.RestService
* arguments: "server /path/to/databrigade-rest/config/config.yml"

# Swagger docs
http://localhost:8080/swagger

# CURL examples
* curl localhost:8080/hello-world?name=aman
* curl localhost:8080/hello-world/aman
* curl -XPOST -H "content-type:application/x-www-form-urlencoded" -d "id=0&content=abc" localhost:8080/hello-world/form
* curl -XPOST -d '{"id":10,"content":"aman"}' -H "Content-Type: application/json" localhost:8080/hello-world/post

# Health Check
http://localhost:8081
