# Тестовое задание ПТМК

В проекте используется СУБД PostgreSQL

Для запуска использовалась база данных ptmk_test, пользователь postges и пароль

Если что-то из этого не совпадает, неоходимо изменить несовпадающие поля в файле ./src/main/resources/application.yml
- url: jdbc:postgresql://localhost:5432/{your_database}
- username: {your_username}
- password: {your_password}

## Компиляция
- mvn clean package

## Запуск
- cd target 
- java -jar ptmk_test-1.0-SNAPSHOT.jar &lt;args&gt;