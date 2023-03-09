FROM registry.access.redhat.com/ubi8/openjdk-8:1.10-1

USER root

EXPOSE 8080
 
COPY target/prueba-tecnica-0.0.1.jar aplicacion.jar
ENTRYPOINT ["java","-XX:+UseParNewGC", "-Xss512k", "-Xms64m", "-Xmx128m", "-XX:+UseConcMarkSweepGC", "-XX:+CMSClassUnloadingEnabled", "-XX:+ExplicitGCInvokesConcurrent", "-XX:+UseCMSInitiatingOccupancyOnly", "-XX:CMSInitiatingOccupancyFraction=70", "-jar","aplicacion.jar", "--debug"]
