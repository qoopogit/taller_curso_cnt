#!/bin/bash
./run_build_docker.sh
docker run -it --rm \
   --network dev_default \
   --name facturacion-ear  \
   -p 6060:8080 \
   --link db-postgres-1:qoopodb \
   -e DB_HOST=qoopodb \
   -e DB_DATABASE=taller_facturacion \
   -e DB_USER=taller \
   -e DB_PASSWORD=taller \
   qoopo/facturacion-ear:1.0
