# GeoTravelJournal_Backend
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Nginx](https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

## EN

## About the Project
This project is an API for saving user routes from a mobile application. It is implemented using Spring and is intended for educational and auxiliary purposes.

## Running the Project

### 1. Creating the `.env` File
Before starting the application, create a `.env` file using `.env.template` as a reference. This file should contain all necessary environment variables.

### 2. Configuring `nginx.conf`
Replace `server_name` in the `nginx.conf` file with your domain.

### 3. Starting Containers
Use `docker-compose` to deploy all required containers:

```sh
docker-compose up -d
```

This will create containers for the database, application, and Nginx.

## License
This project is distributed under the **MIT** license.

---

## RU

## О проекте
Данный проект представляет собой API для сохранения маршрутов пользователей из мобильного приложения. Реализован с использованием Spring и предназначен для учебных и вспомогательных целей.

## Запуск проекта

### 1. Создание файла `.env`
Перед запуском приложения необходимо создать `.env` файл, используя `.env.template` в качестве примера. В этом файле должны быть определены все необходимые переменные окружения.

### 2. Настройка `nginx.conf`
Замените `server_name` в файле `nginx.conf` на ваш домен.

### 3. Запуск контейнеров
Используйте `docker-compose` для развертывания всех необходимых контейнеров:

```sh
docker-compose up -d
```

Это создаст контейнеры для базы данных, приложения и Nginx.

## Лицензия
Проект распространяется под лицензией **MIT**.