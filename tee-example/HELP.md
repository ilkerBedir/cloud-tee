# Read Me First
## Docker
docker build -t project/teeexample .

docker run -p 8082:8080 project/teeexample

docker run -ti --entrypoint /bin/sh project/teeexample

docker run --name project -ti --entrypoint /bin/sh project/teeexample

docker exec -ti project /bin/sh

