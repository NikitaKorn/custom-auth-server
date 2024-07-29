# Кастомный сервер авторизации
Что делает:
* Генерирует, подписывает и выдает токен
* Проверяет валидность токена
* Сохраняет в БД пользователей с ролевой моделью для получения токена

## Получение токена
### Запрос на получение

    curl --location 'https://localhost:9000/authenticate' \
    --header 'Content-Type: application/json' \
    --data '{
    "username": "admin",
    "password" : "123"
    }'

### Ответ от сервера

    {
    "jwt": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyMjI4NTMzMiwiZXhwIjoxNzIyMzIxMzMyfQ.Etq3S54G7EYVTwii2kbXc0aS7o847HBGx8hZ8v3gmUr30m1ccSBj_kz_urFZEmvJVulhxj-0rd5A2qJxSoN-QQ"
    }

## Добавление нового пользователя
### Запрос на добавление

    curl --location 'https://localhost:9000/save_user' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwNjg3MzIxNiwiZXhwIjoxNzA2OTA5MjE2fQ.QIcHPfJVN0dORcRVsi4VM0BjwNazQ5yaZ8l5GYZEZZm3FzAVPDCEGyZwLwTX0GKI9RS_mZNk3rLUEVWYrL05bg' \
    --data-raw '{
    "login": "qwe1213",
    "password" : "1234",
    "email" : "test@mail.ru",
    "roles" : ["test1", "test2"]
    }'

## Технологии:
- Spring boot 3
- Spring security
- Jwt
- Spring aspect
- Controller
- Jpa
- Certificates
- H2 database
- Cache