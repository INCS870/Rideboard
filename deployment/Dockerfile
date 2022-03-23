# Rideboard image
# ver 0.2
#
# Changes:
#  ver 0.1	Alvin	init
#  ver 0.2	Wilson	add oracle driver
#
# syntax=docker/dockerfile:1
FROM oraclelinux:8-slim
RUN microdnf update
RUN microdnf install curl 
RUN microdnf install tar 
RUN microdnf install net-tools
RUN microdnf install java-11-openjdk

WORKDIR /home/opc
COPY . .
RUN curl -O https://download.jboss.org/wildfly/23.0.2.Final/wildfly-23.0.2.Final.tar.gz
RUN gunzip wildfly-23.0.2.Final.tar.gz | tar xf -
RUN gunzip -dc oracle.tar.gz | tar xf -
RUN cp Rideboard.war /home/opc/wildfly-23.0.2.Final/standalone/deployments/
RUN cp standalone.xml /home/opc/wildfly-23.0.2.Final/standalone/configuration/
RUN cp persistenceConfig.properties /home/opc/wildfly-23.0.2.Final/standalone/configuration/
RUN mv oracle $HOME/wildfly-23.0.2.Final/modules/system/layers/base/com/
RUN ln -sf wildfly-23.0.2.Final wildfly
RUN rm wildfly-23.0.2.Final.tar.gz
RUN chmod 755 run_wildfly.sh
CMD ["/home/opc/run_wildfly.sh", "start"]

EXPOSE 5000