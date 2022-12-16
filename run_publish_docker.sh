#!/bin/bash
##################################################################################################
## Script que genera las imagenes de Docker para la aplicacion Seguridades Web
## latest:Ultima version
##
## ver 1.0
## fecha 2021.09.19
## Alberto Garcia
##################################################################################################
./run_build_docker.sh
#docker tag cnt/disney-landing-page:1.0 172.18.95.8:5000/disney-landing-page:1.0
docker push qoopo/facturacion-ear:1.0
