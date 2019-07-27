#Remove Docker Containers, Images, Volumes, and Networks
docker system prune -a -f
docker system prune --volumes -f
docker container prune -f
docker image prune -f
docker volume prune -f
docker network prune -f
