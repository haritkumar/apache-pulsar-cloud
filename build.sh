docker build -t haritkumar/apache-pulsar:2.4.0 .
docker build -t haritkumar/pulsar-express -f Dockerfile.pulsar-express .
mvn clean install -f spring-boot-pulsar-client/pom.xml
docker build -t haritkumar/pulsar-client -f Dockerfile.pulsar-client .
