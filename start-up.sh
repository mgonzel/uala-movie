#! /bin/sh
## NETWORK
if (docker network ls | grep uala_net)
then
	echo "uala_net network already exists"
else
	echo "Creating uala_net"
	docker network create uala_net
fi

## REDIS
echo "Starting up Redis..."
if (docker ps | grep redis.local)
then
        echo "Redis already exists"
else
        docker run --rm -d --name redis.local --network uala_net redis:alpine
        sleep 20s
fi

## Volumen en docker para logger-service
echo "Checking logger-service docker volume"
if (docker volume ls | grep uala_movie_volume)
then
        echo "uala-movie volume already exists"
else
        echo "Creating uala-movie volume"
	docker volume create uala_movie_volume
fi

## Logger Service API
echo "Starting up Uala Movie backend  API"
export GRADLE_PORT=8080:8080

docker run -it --rm -p "${GRADLE_PORT:-80}" --name=uala-movie --network=uala_net \
-v uala_movie_volume:/home/gradle/.gradle \
-v "$PWD":/home/gradle/project \
-w /home/gradle/project gradle:alpine gradle run

