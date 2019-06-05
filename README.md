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

### User login:
> curl -X POST "localhost:8080/users/login" -d '{"alias":"mgonzel","password":"mypass"}'

Response:

	{"status":"OK", "token":"PiqkrJJYvT-nVe9Ifj3ru-vSWDMeUk7V-Ov93KXl1Mr"}

### Get recomendations
> curl -X GET "localhost:8080/recomendations" -H "X-Auth:PiqkrJJYvT-nVe9Ifj3ru-vSWDMeUk7V-Ov93KXl1Mr" -d '{"alias":"mgonzel","mood":"melancolic"}' -v

Response:

	{"recomended":[]}


### Check Redis content
Connect to Redis (docker-created)
> docker run --rm -it --name redis.cli --network uala_net redis:alpine redis-cli -h redis.local
