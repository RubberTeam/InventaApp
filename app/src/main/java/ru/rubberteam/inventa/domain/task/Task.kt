package ru.rubberteam.inventa.domain.task

import android.annotation.SuppressLint
import lombok.AllArgsConstructor
import ru.rubberteam.inventa.domain.item.Item
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * Задача покрывающая все стадии бизнесс-процесса на стороне бэка
 */
@AllArgsConstructor
class Task {
    /**
     * Уникальный служебный идентификатор, не может быть null, заменяет системный номер инвентаризации
     */
    val taskId = UUID.randomUUID()

    /**
     * Ссылка на пользователя, который является администратором процесса, не может быть null, не может быть пустым
     */
    val businessAdmin = "admin"

    /**
     * Какие-либо контактные данные для возможного уточнения задачи
     */
    val contactsAdmin = "88005553535@sberbank.ru"

    /**
     * Ссылка на исполнителя, который в текущий момент назначен на процесс
     */
    val assignedPerformer = "user"

    /**
     * Статус таска, не может быть null
     */
    val taskStatus = TaskStatus.CREATED

    /**
     * Финансовый год, не может быть null
     */
    @SuppressLint("NewApi")
    val fiscalYear = LocalDate.now().year

    /**
     * Ссылка на документ, инициирующий процесс инвенторизации, может быть null, может быть пустым
     */
    val orderDocument = "Приказ № о проведении учета"

    /**
     * Цифровая инвенторизация, что это вообще???
     */
    val cipherInventoried = false

    /**
     * Время создания бизнес-задачи
     */
    //val creationDateTime: LocalDateTime? = null

    /**
     * Время назначения бизнес-задачи
     */
    //val assignedDateTime: LocalDateTime? = null

    /**
     * Время первого события бизнес-задачи
     */
    //val firstActionDateTime: LocalDateTime? = null

    /**
     * Время завершения бизнесс-задачи
     */
    //val endDateTime: LocalDateTime? = null

    /**
     * Список ценностных объектов, участвующих в таске. Может быть пустым, правда в каких случаях?
     */
    val items: Set<Item> = emptySet() // /**
    // * Список проблем, которые возникли в данном бизнесс-процессе
    // */
    // @Builder.Default
    // @JoinColumn(name = "TASK_ID")
    // @ElementCollection(fetch = FetchType.EAGER)
    // Set<Issue> issues = Collections.emptySet();
}