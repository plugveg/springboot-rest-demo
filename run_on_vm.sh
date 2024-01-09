# Variables d'environnement

IMAGE_NAME="setsuma-devops-app"
IMAGE_TAG="latest"
REGISTRY_URL="setsuma"

docker pull $REGISTRY_URL/$IMAGE_NAME:$IMAGE_TAG

docker run -d $REGISTRY_URL/$IMAGE_NAME:$IMAGE_TAG
