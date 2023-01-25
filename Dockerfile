FROM payara/server-full:5.2022.4-jdk11

LABEL mantainer="Alberto Garcia <alberto.garcia@cnt.gob.ec>"
LABEL version=1.0

EXPOSE 8080 4848

#USER root
#RUN apt-get update && apt-get install iputils-ping -y

USER payara
ENV DB_HOST=localhost
ENV DB_PORT=5432
ENV DB_DATABASE=facturacion
ENV DB_USER=facturacion
ENV DB_PASSWORD=facturacion

# url que muestra como usar los parametros jvm para mejorar la gesiton de memoria
# https://stackoverflow.com/questions/4952568/is-there-a-way-to-lower-java-heap-when-not-in-use

# mantiene el tamanio del heap pegado al head usado realmente
ENV JVM_ARGS="-XX:MinHeapFreeRatio=5 -XX:MaxHeapFreeRatio=10 -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=2g -Xmx2g -Xms128m -Djdk.tls.rejectClientInitiatedRenegotiation=true  -XX:+UseG1GC -XX:+UseStringDeduplication -XX:+DisableExplicitGC"

RUN echo 'set configs.config.server-config.admin-service.das-config.dynamic-reload-enabled=false configs.config.server-config.admin-service.das-config.autodeploy-enabled=false' > $PREBOOT_COMMANDS
RUN echo 'set-hazelcast-configuration --clusterMode=multicast --multicastGroup=224.2.2.1 --multicastPort=54327 --dynamic=true' > $POSTBOOT_COMMANDS

#COPY --chown=payara res/postgresql-42.2.5.jar ${PAYARA_DIR}/glassfish/domains/domain1/lib/ext/
COPY --chown=payara res/postgresql-42.2.5.jar ${PAYARA_DIR}/glassfish/domains/domain1/lib/
#COPY --chown=payara res/sqljdbc4.jar ${PAYARA_DIR}/glassfish/domains/domain1/lib/
COPY --chown=payara res/commands.asadmin $PREBOOT_COMMANDS
COPY --chown=payara res/facturacion-ear.ear $DEPLOY_DIR