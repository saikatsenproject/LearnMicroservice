## FROM -- Used for Indicating Base Image
FROM openjdk:27-ea-17-jdk-oracle

# MAINTAINER -- Who maintains the image
MAINTAINER Saikat Sen

# COPY -- Use for from which jar to which jar image we have to create
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# ENTRYPOINT -- Used for which commands we need to run our docker image
# when we run our jar from cmd we generally use /target/[jar name] but here in entrypoint we don't need that ,because we
# have already copy the jar from target to jar without target folder
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]