package ru.rubberteam.inventa.domain.item

/**
 * Тип ценностного объекта, видимо соответствует справочнику "Для поиска по классу"
 */
enum class ItemStatus(val statusName: String) {
    ON_BUY("Покупка/Поставка"),
    WAREHOUSE("На складе"),
    IN_USE("Выдано МОЛу"),
    ISSUE("Проблема"),
    OUT_OF_SERVICE("Списано"),
    MISSED("Утеряно");
}