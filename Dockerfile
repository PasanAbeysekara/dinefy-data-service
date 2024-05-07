FROM openjdk:8-jdk-alpine
LABEL maintainer="pasankavindaabey@gmail.com"

# Install Maven
ARG MAVEN_VERSION=3.6.3
RUN apk add --no-cache curl tar bash \
    && curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    | tar -xzC /opt \
    && ln -s /opt/apache-maven-${MAVEN_VERSION} /opt/maven

# Set environment variables
ENV MAVEN_HOME /opt/maven
ENV PATH $PATH:/opt/maven/bin

# Define default command.
CMD ["mvn", "-version"]
