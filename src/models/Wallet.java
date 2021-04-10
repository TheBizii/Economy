package models;

import io.neutrino.Neutrino;
import io.neutrino.api.database.DatabaseModel;
import io.neutrino.model.NeutrinoProfile;

import java.sql.PreparedStatement;

public class Wallet extends DatabaseModel {

    private Integer userId;
    private Integer currencyId;

    public int getUserId() {
        return userId;
    }

    protected void setUser(NeutrinoProfile user) {
        this.userId = user.getID();
    }

    public int getCurrencyId() {
        return currencyId;
    }

    protected void setCurrency(Currency currency) {
        this.currencyId = currency.getID();
    }

    @Override
    public void createTable() {
        Neutrino.getInstance().getQueryBuilder()
                .createTable(getTableName())
                .ifNotExists()
                .withAttribute("id", "INT")
                .withPrimaryKey("id")
                .withAttribute("user_id", "INT", false)
                .withAttribute("currency_id", "INT", false)
                .withAttribute("created", "DATETIME", false)
                .withAttribute("updated", "DATETIME")
                .withAttribute("active", "BOOLEAN", 1)
                .execute();
    }

    @Override
    public void save() {

    }

    @Override
    public void insert() {
    }

    @Override
    public void update() {
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public void load() {

    }
}
