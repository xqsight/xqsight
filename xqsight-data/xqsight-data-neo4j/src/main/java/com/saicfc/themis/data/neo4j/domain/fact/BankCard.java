package com.saicfc.themis.data.neo4j.domain.fact;

import com.saicfc.themis.data.neo4j.domain.Entity;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by GTaurus on 2016/4/5.
 */
@NodeEntity(label = "BankCard")
public class BankCard extends Entity {

    private String number;
    private String bankCode;
    /**
     *  Debit/Credit
     */
    private String cardType;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
