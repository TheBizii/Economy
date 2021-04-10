package models;

import io.neutrino.Neutrino;
import io.neutrino.api.database.DatabaseModel;

import java.sql.PreparedStatement;

public class Transaction extends DatabaseModel {
    private Integer receiverWalletId;
    private Integer senderWalletId;
    private Double amount;

    public int getReceiverWalletId() {
        return receiverWalletId;
    }

    protected void setReceiverWallet(Wallet wallet) {
        this.receiverWalletId = wallet.getID();
    }

    public int getSenderWalletId() {
        return senderWalletId;
    }

    protected void setSenderWalletId(Wallet wallet) {
        this.senderWalletId = wallet.getID();
    }

    public double getAmount() {
        return amount;
    }

    protected void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public void createTable() {
        Neutrino.getInstance().getQueryBuilder()
                .createTable(getTableName())
                .ifNotExists()
                .withAttribute("id", "INT")
                .withPrimaryKey("id")
                .withAttribute("receiver_wallet_id", "INT", false)
                .withAttribute("sender_wallet_id", "INT", false)
                .withAttribute("amount", "DECIMAL(13, 4)", false)
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
