# Variables d'environnement

IMAGE_NAME="setsuma-devops-app"
IMAGE_TAG="latest"
REGISTRY_URL="setsuma"


docker login $REGISTRY_URL

docker tag $IMAGE_NAME:$IMAGE_TAG $REGISTRY_URL/$IMAGE_NAME:$IMAGE_TAG

docker push $REGISTRY_URL/$IMAGE_NAME:$IMAGE_TAG
