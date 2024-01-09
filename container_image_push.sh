# Variables d'environnement

IMAGE_NAME="setsuma-devops-app"
IMAGE_TAG="latest"
REGISTRY_URL="setsuma"


docker login

docker tag $IMAGE_NAME $REGISTRY_URL/$IMAGE_NAME:$IMAGE_TAG

docker push $REGISTRY_URL/$IMAGE_NAME:$IMAGE_TAG
