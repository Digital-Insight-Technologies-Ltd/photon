FROM amazoncorretto:15-al2-jdk as build

RUN yum install -y maven
COPY . build
RUN cd build && mvn package -Dmaven.test.skip=true

FROM amazoncorretto:15-al2-jdk

EXPOSE 80
RUN mkdir -p /photon/photon_data
WORKDIR /photon
COPY --from=build build/target/photon-0.3.4.jar ./
CMD ["java", "-jar", "photon-0.3.4.jar", "-listen-port", "80"]