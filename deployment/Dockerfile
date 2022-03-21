# syntax=docker/dockerfile:1
FROM oraclelinux:8-slim
RUN microdnf update
RUN microdnf install curl 
RUN microdnf install tar 
RUN microdnf install net-tools
RUN microdnf install java-11-openjdk

WORKDIR /app
COPY . .
RUN curl -O https://download.jboss.org/wildfly/23.0.2.Final/wildfly-23.0.2.Final.tar.gz
RUN gunzip wildfly-23.0.2.Final.tar.gz
RUN tar xf wildfly-23.0.2.Final.tar
RUN cp Rideboard.war /app/wildfly-23.0.2.Final/standalone/deployments/
RUN cp standalone.xml /app/wildfly-23.0.2.Final/standalone/configuration/
RUN ln -sf wildfly-23.0.2.Final wildfly
RUN rm wildfly-23.0.2.Final.tar
RUN chmod 755 run_wildfly.sh
CMD ["/app/run_wildfly.sh", "start"]

EXPOSE 5000
