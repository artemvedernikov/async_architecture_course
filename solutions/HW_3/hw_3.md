# Homework 3

## Расписать процесс миграции title -> (title, jira_id) на новую логику в текстовом виде

Этап 1 (кодинг)

1.1) Пишем миграцию на добавление колонки jira-id (Делаем дефолтное значение null или например регэкспом достаем из текущего значения title)

1.2) Создать новый вид события TaskEventV2 в schema-registry (модуль event-model)

1.3) Поддержать новый формат данных на стороне CRUD REST API для Task в TaskService 

1.4) Поддержать новый вид событий в Producer на стороне TaskService и в Consumer



Этап 2 (Развертывание)

2.1) Мигрируется БД

2.2) Раскатывается Consumer (Accounting-Billing Service) c поддержкой TaskEventV2

2.3) Раскатывается Producer (Task Tracker Service) c поддержкой TaskEventV2

Note: тк новое поле nullable, нам не страшна откатка кода

Этап 3 (Подчистка кода)

3.1) Удаляем из кода логику работы с TaskEventV1

Этап 4 (Развертывание после подчистки кода)

4.1) Раскатывается Producer (Task Tracker Service) c удаленным TaskEventV1

4.2) Раскатывается Consumer (Accounting-Billing Service) c удаленным TaskEventV1
