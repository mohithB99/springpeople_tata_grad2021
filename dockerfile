FROM java:8
EXPOSE 8070
ADD /target/appointmentdocker-0.0.1-SNAPSHOT.jar appointmentdocker-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","appointmentdocker-0.0.1-SNAPSHOT.jar"]
