# Rideboard image
# ver 0.3
#
# Changes:
#  ver 0.1	Alvin	init
#  ver 0.2	Wilson	add oracle driver
#  ver 0.3	Wilson	Kubernetes version
#  ver 0.4	Wilson	Kubernetes ARM version
#
FROM rdohna/wildfly 
WORKDIR /home/opc/build_arm/
COPY . .

RUN cp Rideboard.war /opt/jboss/wildfly/standalone/deployments/
RUN cp standalone.xml /opt/jboss/wildfly/standalone/configuration/
RUN cp persistenceConfig.properties /opt/jboss/wildfly/standalone/configuration/
RUN cp -r oracle /opt/jboss/wildfly/modules/system/layers/base/com/
RUN cp -r Wallet_RBDBPRD /opt/jboss/wildfly/
RUN cp run_wildfly.sh /opt/jboss/wildfly/
RUN chmod 711 /opt/jboss/wildfly/run_wildfly.sh
CMD ["/opt/jboss/wildfly/run_wildfly.sh", "start"]

EXPOSE 5000
