#!/bin/sh

. ./.env # load env variables

# use correct docker compose command (v1 or v2)
if [ "$COMPOSE_VERSION" = "2" ]
then
    composeCommand="docker compose"
elif [ "$COMPOSE_VERSION" = "1" ]
then
    composeCommand="docker-compose"
else
    echo "Specify COMPOSE_VERSION in .env"
    exit 1
fi

# commands
command=$1
if [ "$command" = "start" ]
then
    $composeCommand up -d --build
elif [ "$command" = "stop" ]
then
    $composeCommand down
elif [ "$command" = "logs" ]
then
    $composeCommand logs -f --tail 50
else
    echo "Command \"$command\" not found"
    exit 1
fi