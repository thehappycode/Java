echo "-> Bắt đầu chạy script..."

mkdir microservices
cd microservices

echo "-> Tạo thự mục microservices thành công"

spring init `
--boot-version=3.5.7 `
--type=gradle-project `
--java-version=21 `
--packaging=jar `
--name=product-service `
--package-name=thehappycode.microservices.core.product `
--groupId=thehappycode.microservices.core.product `
--dependencies=actuator,webflux `
--version=1.0.0-SNAPSHOT `
product-service

echo "-> Tạo project product-service thành công"

spring init `
--boot-version=3.5.7 `
--type=gradle-project `
--java-version=21 `
--packaging=jar `
--name=review-service `
--package-name=thehappycode.microservices.core.review `
--groupId=thehappycode.microservices.core.review `
--dependencies=actuator,webflux `
--version=1.0.0-SNAPSHOT `
review-service

echo "-> Tạo project review-service thành công"

spring init `
--boot-version=3.5.7 `
--type=gradle-project `
--java-version=21 `
--packaging=jar `
--name=recommendation-service `
--package-name=thehappycode.microservices.core.recommendation `
--groupId=thehappycode.microservices.core.recommendation `
--dependencies=actuator,webflux `
--version=1.0.0-SNAPSHOT `
recommendation-service

echo "-> Tạo project recommendation-service thành công"

spring init `
--boot-version=3.5.7 `
--type=gradle-project `
--java-version=21 `
--packaging=jar `
--name=product-composite-service `
--package-name=thehappycode.microservices.composite.product `
--groupId=thehappycode.microservices.composite.product `
--dependencies=actuator,webflux `
--version=1.0.0-SNAPSHOT `
product-composite-service

echo "-> Tạo project product-composite-service thành công"

echo "-> Chạy script thành công"

Pause