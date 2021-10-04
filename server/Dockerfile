FROM openjdk:11-jre

MAINTAINER Indra Basak <indra.basak1@gmail.comr>

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/local/dristhi/bin/app.jar
RUN sh -c 'touch /usr/local/dristhi/bin/app.jar'
ADD src/docker/entrypoint.sh /usr/local/dristhi/bin
RUN chmod 755 /usr/local/dristhi/bin/entrypoint.sh

CMD /usr/local/dristhi/bin/entrypoint.sh