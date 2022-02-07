package com.example.androiddevelopertest.Retrofit.Currency

data class Date(
    var Date: String?,
    var PreviousDate: String?,
    var PreviousURL: String?,
    var Timestamp: String?,
    var Valute: MutableList<AUD>,
)

data class AUD(
    var AUD: MutableList<SomeCurrency>,
)

data class SomeCurrency (
    var ID: String?,
    var NumCode: String?,
    var CharCode: String?,
    var Nominal: String?,
    var Name: String?,
    var Value: String?,
    var Previous: String?,
        )
/*
{
    "Date": "2020-07-28T11:30:00+03:00",
    "PreviousDate": "2020-07-25T11:30:00+03:00",
    "PreviousURL": "//www.cbr-xml-daily.ru/archive/2020/07/25/daily_json.js",
    "Timestamp": "2020-07-27T23:00:00+03:00",
    "Valute":
    {
        "AUD": {
        "ID": "R01010",
        "NumCode": "036",
        "CharCode": "AUD",
        "Nominal": 1,
        "Name": "Австралийский доллар",
        "Value": 51.0401,
        "Previous": 50.6695
    }
    }
}*/
