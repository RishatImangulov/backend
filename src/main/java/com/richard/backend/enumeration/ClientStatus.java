package com.richard.backend.enumeration;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ClientStatus {
    FIRST_CONTACT("Первое посещение"),
    REPEATED_CONTACT("Повторное посещение"),
    REGULAR_CLIENT("Постоянный клиент"),
    VIP("VIP"),
    BLACKLIST("Черный список");

    ClientStatus(String russianName) {
    }


}