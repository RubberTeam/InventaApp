package ru.rubberteam.inventa.domain.event

import lombok.AllArgsConstructor
import ru.rubberteam.inventa.domain.issue.Issue
import java.time.LocalDateTime
import java.util.*

/**
 * Описывает контракт взаимодействия с модильным клиентом
 */
@AllArgsConstructor
data class EventNew(
    val eventID: UUID = UUID.randomUUID(),
    val creationDate: LocalDateTime /* TODO= LocalDateTime.now()*/,
    val eventStatus: EventStatus = EventStatus.OK,
    val itemID: UUID,
    val taskID: UUID,
    val issue: Issue? = null
)