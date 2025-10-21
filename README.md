# 🚀 User-Hub 1.0.0
A secure and scalable Spring Boot application featuring Google / Facebook / GitHub SSO integration and JWT authentication using RSA encryption.🔐

# 🌟 Key Features
- 🔐 Secure Authentication & Authorization
- 👤 User Registration & Login
- ✏️ Update User
- ❌ Delete User
- 🔍 Search All Users
- 🧭 Filtered User Search
- 📄 Pagination Support

# 🔑 RSA Key Generation for JWT
This app uses RSA 2048-bit keys to secure JWT tokens.

# 🔗 SSO Integration
SSO is secured using OAuth2 and Spring Security for seamless login experiences.
- 🌐 Google
- 📘 Facebook
- 🐙 GitHub

# 🔑 RSA Key Generation Steps
- openssl genrsa -out keypair.pem 2048
- openssl rsa -in keypair.pem -pubout -out public.pem
- openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
