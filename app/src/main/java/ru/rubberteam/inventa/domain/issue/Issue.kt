package ru.rubberteam.inventa.domain.issue

import lombok.AllArgsConstructor
import java.util.*

/**
 * Сущность объекта, который описывает какие-либо проблемы бизнес-процесса
 */
@AllArgsConstructor
data class Issue(
    /**
     * Уникальный служебный идентификатор
     */
    val issueID: UUID? = UUID.randomUUID(),
    /**
     * Ссылки на task и item, к которым может быть привязано issue
     */
    val taskID: UUID,
    val itemID: UUID,
    /**
     * Описание проблемы
     */
    var description: String? = "Есть какая-то проблема",
    /**
     * Идентификатор проблемы в других системах, например, сбердруг
     */
    var identifierThirdPartySystems: String? = null,
    /**
     * Подтверждение
     */
    var pic: ByteArray?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Issue

        if (issueID != other.issueID) return false
        if (taskID != other.taskID) return false
        if (itemID != other.itemID) return false
        if (description != other.description) return false
        if (identifierThirdPartySystems != other.identifierThirdPartySystems) return false
        if (pic != null) {
            if (other.pic == null) return false
            if (!pic.contentEquals(other.pic)) return false
        } else if (other.pic != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = issueID?.hashCode() ?: 0
        result = 31 * result + taskID.hashCode()
        result = 31 * result + itemID.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (identifierThirdPartySystems?.hashCode() ?: 0)
        result = 31 * result + (pic?.contentHashCode() ?: 0)
        return result
    }
}
