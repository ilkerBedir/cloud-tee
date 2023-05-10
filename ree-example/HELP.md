# Read Me First
## Docker
docker build -t project/reeexample .

docker run -p 8081:8080 project/reeexample

docker run -ti --entrypoint /bin/sh project/reeexample

docker run --name project -ti --entrypoint /bin/sh project/reeexample

docker exec -ti project /bin/sh

