package com.richard.backend.enumeration;

public enum TicketStage {
    DIAGNOSTICS("Диагностика"), // Diagnosis
    LONG_BOX("Долгий ящик"), // Postponed
    WAITING_FOR_CLIENT_APPROVAL("Ожидание согласования с клиентом"), // Waiting for client approval
    WAITING_FOR_CLIENT_RESPONSE("Ожидание ответа от клиента"), // Waiting for client response
    ORDER_REQUIRED("Нужен заказ"), // Order required
    WAITING_FOR_COMPONENTS("Ожидание комплектующих"), // Waiting for components
    IN_PROGRESS("В работе"), // In progress
    COMPLETED("Готово"), // Completed
    CLIENT_NOTIFIED("Клиент оповещён"), // Client notified
    ISSUED("Выдано"), // Issued
    PROCESSED("Обработано"), // Processed
    WAITING_FOR_PAYMENT("Ждем оплату"); // Waiting for payment

    // Constructor
    TicketStage(String russianName) {
    }

}