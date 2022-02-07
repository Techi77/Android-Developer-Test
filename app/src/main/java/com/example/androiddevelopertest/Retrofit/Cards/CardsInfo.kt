package com.example.androiddevelopertest.Retrofit

data class Users(
    var users: MutableList<CardsInfo>,
)

data class CardsInfo(
    var card_number: String?,
    var type: String? ,
    var cardholder_name: String? ,
    var valid: String? ,
    var balance: Double? ,
    var transaction_history: MutableList<HistoryInfo> ,
    var users: String?
)

data class HistoryInfo(
    var title: String? ,
    var icon_url: String? ,
    var date: String? ,
    var amount: Double? ,
)

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