package ru.rubberteam.inventa.domain.task

/**
 * Статус бизнес-задачи
 */
enum class TaskStatus(val statusName: String) {
    CREATED("Создано"),
    ASSIGNED("Назначено"),
    SCHEDULED("Запланированно"),
    IN_PROGRESS("В работе"),
    ISSUE("Проблема"),
    DONE("Завершено");
}