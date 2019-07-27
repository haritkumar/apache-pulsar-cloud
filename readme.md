## Apache Pulsar 2.4.0
![Apache Pulsar 2.4.0](pulsar-system-architecture.png)
Apache Pulsar is an open-source distributed pub-sub messaging system originally created at Yahoo and now part of the Apache Software Foundation.

### Run apache pulsar (standalone)
```sh
docker run -p 6650:6650 -p 8080:8080 haritkumar/apache-pulsar:2.4.0
```

### Run apache pulsar-express (UI Manager)
```sh
docker run -p 3000:3000 haritkumar/pulsar-express:latest
```

### Run apache pulsar-client (Producer/Consumer)
```sh
docker run -p 9000:9000 haritkumar/pulsar-client:latest
```

### Use docker compose to run all nodes at once
```sh
docker-compose up
```