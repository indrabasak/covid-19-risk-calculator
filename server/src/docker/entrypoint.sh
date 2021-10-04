#!/bin/bash

cd /usr/local/dristhi
[[ -f config/env ]] && . config/env

java $JAVA_OPTS -jar bin/app.jar