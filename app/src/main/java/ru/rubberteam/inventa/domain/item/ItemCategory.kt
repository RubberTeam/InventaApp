package ru.rubberteam.inventa.domain.item

/**
 * Энам, описывающий категории объектов для удобства сортировки на стороне приложения
 */
enum class ItemCategory(val categoryName: String) {
    FURNITURE("Мебель"),
    OFFICE_SUPPLIES("Канцелярные принадлежности"),
    OFFICE_EQUIPMENT("Офисная техника"),
    FIRE_SAFETY_EQUIPMENT("Средства пожарной безопасности"),
    CIPHER_LICENSE("Цифровая лицензия"),
    REAL_ESTATE("Недвижимость"),
    GUN("Оружие"),
    OTHER("Прочее");
}