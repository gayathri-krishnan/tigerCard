#!/bin/sh -e

# The "ENVIRONMENT" environment variable is set by the CI/CD process.
# Converting the it to lower case
export SPRING_PROFILES_ACTIVE=$(echo ${ENVIRONMENT:-dev} | tr '[:upper:]' '[:lower:]')
echo "SPRING_PROFILES_ACTIVE: ${SPRING_PROFILES_ACTIVE}"
exec java -jar tigercard.jar
