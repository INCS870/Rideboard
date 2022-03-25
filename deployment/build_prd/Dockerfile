# Rideboard image
# ver 0.3
#
# Changes:
#  ver 0.1	Alvin	init
#  ver 0.2	Wilson	add oracle driver
#  ver 0.3	Wilson	Kubernetes version
#
FROM jboss/wildfly 
WORKDIR /Volumes/Document/Dev/Rideboard/build/
COPY . .

RUN cp Rideboard.war /opt/jboss/wildfly/standalone/deployments/
RUN cp standalone.xml /opt/jboss/wildfly/standalone/configuration/
RUN cp persistenceConfig.properties /opt/jboss/wildfly/standalone/configuration/
RUN gunzip -dc oracle.tar.gz |tar xf -
RUN mv oracle /opt/jboss/wildfly/modules/system/layers/base/com/
RUN gunzip -dc Wallet_ATPAS.tar.gz |tar xf -
RUN mv Wallet_ATPAS /opt/jboss/
RUN cp run_wildfly.sh /opt/jboss/
RUN chmod 711 /opt/jboss/run_wildfly.sh
CMD ["/opt/jboss/run_wildfly.sh", "start"]

EXPOSE 5000
