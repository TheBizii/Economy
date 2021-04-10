package models;

import io.neutrino.Neutrino;
import io.neutrino.api.database.DatabaseModel;

import java.sql.PreparedStatement;

public class Currency extends DatabaseModel {

    private String name;
    private String abbreviation;

    public String getName() {
        return name;
    }

    protected void setName(String name) {

    }

    public String getAbbreviation() {
        return abbreviation;
    }

    protected void setAbbreviation() {

    }

    @Override
    public void createTable() {
        Neutrino.getInstance().getQueryBuilder()
                .createTable(getTableName())
                .ifNotExists()
                .withAttribute("id", "INT")
                .withPrimaryKey("id")
                .withAttribute("name", "VARCHAR(45)", false)
                .withAttribute("abbreviation", "VARCHAR(4)", false)
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
