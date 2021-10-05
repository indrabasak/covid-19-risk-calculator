![New Build Status][github-action-badge]
[![Quality Gate][sonarqube-badge]][sonarqube-badge-url]
[![Technical debt ratio][technical-debt-ratio-badge]][technical-debt-ratio-badge-url]
[![Coverage][coverage-badge]][coverage-badge-url]

COVID-19 Risk Calculator
====================================================
TODO

## Modules
The project consist of the following modules:

1. `server` - contains code related to REST API for calculating COVID-19 risk

## Build and Run
### Maven Build
Make sure you have `Maven` installed. Execute the following maven command from the directory of the
parent project, `covid-19-risk-calculator`:
```
mvn clean install
```
It will create the Spring Boot executable JAR,`server-1.0.0-SNAPSHOT.jar`, under `server/target`
folder.

### Run
To run the newly created Spring Boot JAR from the terminal:
```
java -jar server-1.0.0-SNAPSHOT.jar
```
This should start up the example application at port `8080`. The application can be accessed at `http://localhost:8080`

### Docker Build
Before you build the Docker image, make sure Docker is available in your environment.
Execute the following maven command from the directory of the parent project, `covid-19-risk-calculator`:
```
mvn clean install
```
This should build a Docker image named `docker-example`.

### Docker Run
Run the newly created Docker image, `basaki/covid-19-risk-calculator-server`, by executing the
[`docker run`](https://docs.docker.com/engine/reference/run/) command from the terminal:
```
docker run --rm -p 8080:8080  --name=server basaki/covid-19-risk-calculator-server:1.0.0-SNAPSHOT
```
##### Options
* `--rm` option automatically clean up the container and remove the file system when the container exit.
* `--name` option names the Docker container as `server`. In absence of the `--name` option, the Docker generates a
  random name for your container.
* [`-p 8080:8080`](https://docs.docker.com/engine/reference/run/#expose-incoming-ports) option publishes all
  exposed ports to the host interfaces. In our example, it is port `8080` is both `hostPort` and `containerPort`

This should start up the example application and it can be accessed at `http://localhost:8080`

## Docker Commands
### List Container
Run the [`docker ps`](https://docs.docker.com/v1.11/engine/reference/commandline/ps/) to list all the containers.
To see all running containers, execute the following command:
```
$ docker ps
CONTAINER ID   IMAGE                                                   COMMAND                  CREATED          STATUS          PORTS                                       NAMES
9d19c27a9f1e   basaki/covid-19-risk-calculator-server:1.0.0-SNAPSHOT   "/bin/sh -c /usr/loc…"   26 seconds ago   Up 24 seconds   0.0.0.0:8080->8080/tcp, :::8080->8080/tcp   server
```
To see all running containers including the non-running ones, execute the following command:
```
$ docker ps -a
CONTAINER ID   IMAGE                                                   COMMAND                  CREATED          STATUS                      PORTS                                                  NAMES
9d19c27a9f1e   basaki/covid-19-risk-calculator-server:1.0.0-SNAPSHOT   "/bin/sh -c /usr/loc…"   54 seconds ago   Up 53 seconds               0.0.0.0:8080->8080/tcp, :::8080->8080/tcp              server
a500ffb370c6   bitnami/kafka:2                                         "/opt/bitnami/script…"   4 months ago     Exited (255) 3 weeks ago    0.0.0.0:9092->9092/tcp                                 docker_kafka_1
5bae2b723042   bitnami/zookeeper:3                                     "/opt/bitnami/script…"   4 months ago     Exited (255) 3 weeks ago    2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp, 8080/tcp   docker_zookeeper_1
```

### Remove Container
To remove a Docker container, execute [`docker rm`](https://docs.docker.com/v1.11/engine/reference/commandline/rm/)
command. This will remove a non-running container.
```
$ docker rm server
server
```
To forcefully remove a running container
```
$ docker rm -f server
server
```

### Stop Container
To stop a container, execute [`docker stop`](https://docs.docker.com/v1.11/engine/reference/commandline/stop/)
```
command:
$ docker server
server
```

### Docker Compose 

#### Run
To run docker compose, execute the following command from the parent
project directory, `covid-19-risk-calculator`:

```
 docker-compose up --build
```

This should start up the server in port 8080.

#### Stop
To shutdown docker compose, execute the following command:

```
docker-compose down
```

[github-action-badge]: https://github.com/indrabasak/covid-19-risk-calculator/actions/workflows/main.yml/badge.svg

[sonarqube-badge]: https://sonarcloud.io/api/project_badges/measure?project=org.dristhi.covid19%3Acovid-19-risk-calculator&metric=alert_status
[sonarqube-badge-url]: https://sonarcloud.io/dashboard/index/org.dristhi.covid19:covid-19-risk-calculator

[technical-debt-ratio-badge]: https://sonarcloud.io/api/project_badges/measure?project=org.dristhi.covid19%3Acovid-19-risk-calculator&metric=sqale_index
[technical-debt-ratio-badge-url]: https://sonarcloud.io/dashboard/index/org.dristhi.covid19:covid-19-risk-calculator

[coverage-badge]: https://sonarcloud.io/api/project_badges/measure?project=org.dristhi.covid19%3Acovid-19-risk-calculator&metric=coverage
[coverage-badge-url]: https://sonarcloud.io/dashboard/index/org.dristhi.covid19:covid-19-risk-calculator




