# Rideboard imag
# ver 0.3
#
# Changes:
#  ver 0.1	Alvin	init
#  ver 0.2	Wilson	add oracle driver
#  ver 0.3	Wilson	Kubernetes version
#  ver 0.4	Wilson	Kubernetes ARM version
#  ver 0.5	Wilson	Kubernetes ARM HA version
#
FROM rdohna/wildfly 
WORKDIR /home/opc/build_arm/
COPY . .

RUN cp Rideboard.war /opt/jboss/wildfly/standalone/deployments/
RUN cp standalone-ha.kur /opt/jboss/wildfly/standalone/configuration/standalone.xml
RUN cp persistenceConfig.properties /opt/jboss/wildfly/standalone/configuration/
RUN cp -r oracle /opt/jboss/wildfly/modules/system/layers/base/com/
RUN cp -r Wallet_RBDBPRD /opt/jboss/wildfly/

EXPOSE 5000 9990 7600 8888
ENV POD_NAMESPACE default
ENV KUBERNETES_SERVICE_HOST kubernetes.default
ENV KUBERNETES_SERVICE_PORT 443
