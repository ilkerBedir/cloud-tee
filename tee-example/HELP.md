# Read Me First
## Docker

docker build -t project/teeexample .

docker network create mynetwork

docker run -p 8082:8060 --network=mynetwork --name=tee project/teeexample

docker run -ti --entrypoint /bin/sh project/teeexample

docker run --name project -ti --entrypoint /bin/sh project/teeexample

docker exec -ti project /bin/sh

