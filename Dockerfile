FROM haritkumar/oracle_jdk:8_unlimited_jce_ubuntu_16.04

RUN apt-get -y update && apt-get install -y wget curl net-tools && \
    mkdir /opt/pulsar && cd /opt/pulsar && \
    wget http://mirrors.estointernet.in/apache/pulsar/pulsar-2.4.0/apache-pulsar-2.4.0-bin.tar.gz && \
    tar zxvpf apache-pulsar-2.4.0-bin.tar.gz && \
    rm -rf apache-pulsar-2.4.0-bin.tar.gz && apt-get clean

RUN ls -lrt /opt/pulsar
EXPOSE 6650 8080
WORKDIR /opt/pulsar/apache-pulsar-2.4.0
CMD [ "bin/pulsar","standalone" ]