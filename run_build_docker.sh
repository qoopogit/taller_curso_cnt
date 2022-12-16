#!/bin/bash
ORIGEN="facturacion-ear/target/facturacion-ear.ear"
mvn clean install && \
#mvn package && \
 cp $ORIGEN res/facturacion-ear.ear -u && \
  docker build -t qoopo/facturacion-ear:1.0 . && \
  rm -f res/facturacion-ear.ear