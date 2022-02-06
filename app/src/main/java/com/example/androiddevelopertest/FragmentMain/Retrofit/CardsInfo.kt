package com.example.androiddevelopertest.FragmentMain.Retrofit

data class CardsInfo(
    var card_number: String? = null,
    var type: String? = null,
    var cardholder_name: String? = null,
    var valid: String? = null,
    var balance: String? = null,
    var transaction_history: String? = null,
    var users: String? = null
)

data class HistoryInfo(
    var title: String? = null,
    var icon_url: String? = null,
    var date: String? = null,
    var amount: String? = null,
)

class Person(val users: String)
/*
users{
    card_number - номер карты клиента
    type - тип карты (от этого зависти иконка в фрагменте выбора карты)
    cardholder_name - имя держателя карты
    valid - срок действия карты
    balance - баланс в долларах
    transaction_history - история транзакций
            title - название транзакции
            icon_url -url иконка транзакции
            date - дата транзакции
            amount - сумма транзакции
}*/