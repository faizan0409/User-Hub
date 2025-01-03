# User-Hub
# A Spring Boot Application with secured Google SSO utilizing JWT tokens secured by RSA algorithm.
# Spring - 06 updated security mechanism is used that increased robustness of Application.
# Secured Authentication and Authorization.
# Functionalities: User Registration, User Login, Update User, Delete User, Search All Users, Search User using filter, Pagination. 

# Steps to Generate RSA Keys.

# 01. Use below command to generate 2048 bit RSA Key
- openssl genrsa -out keypair.pem 2048
# 02. Use below command to extract public key from 2048 bit RSA key.
- openssl rsa -in keypair.pem -pubout -out public.pem
# 03. Use below command to extract private key from 2048 bit RSA key.
- openssl pkcs8 -topk8 -inform PEM  -outform PEM -nocrypt -in keypair.pem -out private.pem
