package com.tron.wallet.business.message;
public class TransactionMessageStatusBean {
    private TransactionMessage message;
    private int position;

    public TransactionMessage getMessage() {
        return this.message;
    }

    public int getPosition() {
        return this.position;
    }

    public void setMessage(TransactionMessage transactionMessage) {
        this.message = transactionMessage;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public TransactionMessageStatusBean(TransactionMessage transactionMessage, int i) {
        this.message = transactionMessage;
        this.position = i;
    }
}
