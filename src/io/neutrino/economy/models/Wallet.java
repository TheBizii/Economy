package io.neutrino.economy.models;

import io.neutrino.Neutrino;
import io.neutrino.api.database.ComparisonOperator;
import io.neutrino.api.database.DatabaseModel;
import io.neutrino.api.database.query.JoinType;
import io.neutrino.model.NeutrinoProfile;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Wallet extends DatabaseModel {

    private Integer userId;
    private Integer currencyId;

    public int getUserId() {
        return userId;
    }

    protected void setUser(int id) {
        this.userId = id;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    protected void setCurrency(int id) {
        this.currencyId = id;
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
        Neutrino.getInstance().getQueryBuilder()
                .insertInto(getTableName())
                .columns("user_id", "currency_id", "created", "updated", "active")
                .values(userId, currencyId, created, updated, active)
                .execute();
    }

    @Override
    public void update() {
    }

    @Override
    public boolean exists() {
        return false;
    }

    public static List<Wallet> getPlayerWallets(Player p) {
        return getPlayerWallets(p.getUniqueId());
    }

    public static List<Wallet> getPlayerWallets(UUID uuid) {
        List<Wallet> wallets = new ArrayList<>();
        ResultSet rs = Neutrino.getInstance().getQueryBuilder()
                .select("wallet.id", "user_id", "currency_id", "created", "updated", "active")
                .from(Wallet.class.getSimpleName().toLowerCase())
                .join(JoinType.INNER, NeutrinoProfile.class, "id", Wallet.class, "user_id")
                .where("uuid", ComparisonOperator.EQUALS, uuid.toString())
                .execute();
        try {
            while(rs.next()) {
                Wallet wallet = new Wallet();
                wallet.setID(rs.getInt("wallet.id"));
                wallet.setUser(rs.getInt("user_id"));
                wallet.setCurrency(rs.getInt("currency_id"));
                wallet.setCreated(rs.getTimestamp("created"));
                wallet.setUpdated(rs.getTimestamp("updated"));
                wallet.setActive(rs.getBoolean("active"));
                wallets.add(wallet);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return wallets;
    }
}
