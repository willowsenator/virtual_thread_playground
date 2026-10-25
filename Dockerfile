FROM bellsoft/liberica-openjdk-alpine:27
WORKDIR app
ADD https://github.com/vinsguru/java-virtual-thread-course/raw/refs/heads/master/02-external-services/external-services.jar external-services.jar
CMD java -jar external-services.jar