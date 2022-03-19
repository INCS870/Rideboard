# syntax=docker/dockerfile:1
FROM node:12-alpine
RUN apk add --no-cache openjdk8 curl
WORKDIR /app
COPY . .
RUN curl -O https://download.jboss.org/wildfly/23.0.2.Final/wildfly-23.0.2.Final.tar.gz
RUN gunzip wildfly-23.0.2.Final.tar.gz
RUN tar xf wildfly-23.0.2.Final.tar
RUN cp Rideboard.war /app/wildfly-23.0.2.Final/standalone/deployments/
RUN cp standalone.xml /app/wildfly-23.0.2.Final/standalone/configuration/
#COPY Rideboard.war $HOME/wildfly-23.0.2.Final/standalone/deployments/
#COPY standalone.xml $HOME/wildfly-23.0.2.Final/standalone/configuration/

RUN ln -sf wildfly-23.0.2.Final wildfly
#RUN mv wildfly-23.0.2.Final.tar
RUN rm wildfly-23.0.2.Final.tar
RUN chmod 755 run_wildfly.sh
RUN /app/run_wildfly.sh start

# RUN yarn install --production
# CMD ["node", "src/index.js"]
EXPOSE 5000
