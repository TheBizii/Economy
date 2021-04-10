package io.neutrino.economy.api;

import models.Currency;

import java.util.ArrayList;
import java.util.List;

public class EconomyAPI {

    public List<Currency> getSupportedCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        return currencies;
    }

    public void addSupportedCurrency(String name, String abbreviation) {

    }

    public void addSupportedCurrency(Currency c) {

    }

}