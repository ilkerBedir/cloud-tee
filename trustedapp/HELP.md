# Read Me First
## Docker
docker build -t project/trustedapp .

docker network create mynetwork

docker run -p 8083:8080 --network=mynetwork --name=ta project/trustedapp

docker run -ti --entrypoint /bin/sh project/trustedapp

docker run --name project -ti --entrypoint /bin/sh project/trustedapp

docker exec -ti project /bin/sh

