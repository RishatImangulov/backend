package com.richard.backend.enumeration;

public enum TicketStage {
    DIAGNOSTICS("Диагностика","Принят на диагностику", true),
    LONG_BOX("Долгий ящик","Долгий ящик",true),
    CLIENT_APPROVAL_REQUIRED("Согласование","Нужно согласование с клиентом",true),
    WAITING_FOR_CLIENT_RESPONSE("Ожидание ответа","Ожидание ответа от клиента",true),
    COMPONENTS_ORDER_REQUIRED("Нужно заказать","Нужен заказ комплектующих",true),
    WAITING_FOR_COMPONENTS("Ожидание комплектующих","Ожидание комплектующих",true),
    IN_PROGRESS("В работе","Аппарат в работе",true),
    COMPLETED("Готово","Готов к выдаче",true),
    CLIENT_NOTIFIED("Клиент оповещён","Клиент оповещён о готовности",true),
    ISSUED("Выдано","Аппарат выдан или квитанция закрыта",true),
    PROCESSED("Обработано","Обработано",true),
    WAITING_FOR_PAYMENT("Ждем оплату","Ждем оплату от клиента",true);

    TicketStage(final String shortName,
                final String russianName,
                final boolean visible) {
    }

}