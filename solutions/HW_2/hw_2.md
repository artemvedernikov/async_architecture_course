# Homework 2

## (Задание 1) Выбрать логику аутентификации в системе. Можно использовать любой из способов аутентификации, Антон советует попробовать oAuth, а Федя — JWT HTTP API интерфейс.

Использован внешний сервис Keycloack + плагин для работы с кафка https://github.com/SnuK87/keycloak-kafka

При настройке использовал статью https://www.baeldung.com/spring-boot-keycloak

Dockerfile и конфиг realms лежат в ./keycloack, в docker-compose.yml описан как auth

Большое спасибо @drsdgdbye за идею!

## (Задание 2) Выбрать message broker и развернуть его локально

Использованы Kafka + Zookeeper в docker-compose.yml описана как broker

## (Задание 3) Написать сервис авторизации. Можно написать его с нуля самому, а можно взять готовый из гитхаб-репозитория с примером inventory-системы.

Использован Keycloack

## (Задание 4) Реализовать логику таск-трекера

Сервис task-tracker

## (Задание 5) Связать сервис авторизации и логику таск трекера, а так же реализовать все события которые должны быть отправлены и прочитаны в каждом из двух сервисов

Keycloak отправляет в kafka топик account events события, которые читает task-tracker

## (Задание 6) Отправить первое событие в систему и прочитать его в другом сервисе