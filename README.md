# uala-movie

Start the server:
> ./start-up

This command will:
- create a docker network called "uala_net"
- start up redis server connected to uala_net
- start up Uala-Movie API webserver connected to uala_net

Server:
- Will execute under docker, exported to port 8080

## How to use API
### Create new user:
> curl "localhost:8080/users" -X POST  -d '{"alias":"mgonzel","password":"mypass","first_name":"Mariano","last_name":"Gonzel"}' -v

### Check Redis content
Connect to Redis (docker-created)
> docker run --rm -it --name redis.cli --network uala_net redis:alpine redis-cli -h redis.local
