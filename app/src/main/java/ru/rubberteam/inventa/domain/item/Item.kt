package ru.rubberteam.inventa.domain.item

import lombok.AllArgsConstructor
import java.time.LocalDate
import java.util.*

/**
 * Сущность объекта, который когда-либо подлежал или будет подлежать инвентаризации
 */
@AllArgsConstructor
class Item {
    /**
     * Соответствует item_ID, уникальный служебный идентификатор, не может быть null
     */
    val itemId = UUID.randomUUID()

    /**
     * Соответствует Category_Name, категория ценностного объекта, не может быть null, не может быть пустым
     *
     * @see ItemCategory
     */
    //    @NonNull
    val itemCategory = ItemCategory.OTHER

    /**
     * Соответствует Status - состояние ценностного объекта, не может быть null, не может быть пустым
     *
     * @see ItemStatus
     */
    //    @NonNull
    val itemStatus = ItemStatus.IN_USE

    /**
     * Признак, что ценностный объект когда-либо уже использовался
     */
    val itemIsUsed = true

    /**
     * Фактическое подразделение, в котором находится объект, или к которому приписан. Соответствует DIVISION, может
     * быть пустым или null
     */
    val itemDivisionName: String? = null

    /**
     * Название завода, где произведен или произведен ремонт ценностного объекта, может быть null, может быть пустым.
     * Соответствует WERKS
     */
    val itemFactoryName: String? = null

    /**
     * Название склада, где размещается ценностный объект, соответствует LGORT
     */
    val itemWareHouseName: String? = null

    /**
     * Соответствует ZZPERNR - табельный номер, который содержится в баркоде или qr-коде, может быть null, может быть
     * пустым
     */
    val itemCode: String? = null

    /**
     * МОЛ, ответственный за объект, может быть null, может быть пустым. Отмечается фактический человек на месте.
     * Соответствует LINKK
     */
    val itemOwner: String? = null

    /**
     * Соответствует INVNR - инвентарный номер, может быть null, может быть пустым
     */
    val itemInventoryNumber: String? = null

    /**
     * Соответствует SERIAL - серийный номер, может быть null, может быть пустым
     */
    val itemSerialNumber: String? = null

    /**
     * Соответствует CHARG - номер партии, может быть null, может быть пустым
     */
    val itemBatchNumber: String? = null

    /**
     * Соответствует TMC_NAME - имя и описание сущности (товарно-материальные-ценности)
     */
    val itemDescription: String? = null

    /**
     * Соответствует Task_ID - последний активный таск, в котором велась работы над данным объектом
     */
    val taskID: UUID? = null

    /**
     * Индикатор того, что уже находится в каком-то таске И должен быть проинвенторизирован
     */
    val taskCurrentlyInventoried = false

    /**
     * Количество предметов подразумеваемых под данной сущностью, соответствует BU_VALUE (Количество по БУ), по дефолту
     * 1, не может быть меньше 1
     */
    val itemCount = 1

    /**
     * Соответствует BU_AMOUNT - стоимость по БУ, 0 в случае отсутствия цены по какой-либо причине
     */
    val itemPrice = 0

    /**
     * Соответствует WAERS - код валюты для цены, может быть null, может быть empty в случае отсутствия itemPrice
     */
    val itemCurrencyType = "RUB"

    /**
     * Соответствует 'Adress' - адресу + уточнение локаци, например кабинет и рабочее место
     */
    val itemLocation: String? = null

    /**
     * Дата последнего обновления
     */
    val itemLastUpdate: LocalDate? = null //    /**
    //     * Проблема
    //     */
    //     Issue itemIssue;
}