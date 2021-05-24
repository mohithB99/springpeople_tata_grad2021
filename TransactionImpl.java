package com.tata.storeapp.dao;

import com.tata.storeapp.models.Transaction;
import com.tata.storeapp.models.TransactionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransactionImpl implements TransactionDao{
    @Override
    public List<Transaction> getAllTransactions() {
        return getData();
    }

    private List<Transaction> getData(){
        List<Transaction> transactionList=new ArrayList<Transaction>();
        Transaction transaction=null;
        for(int i=0;i<1000;i++){

            transaction=new Transaction();
            transaction.setTransactionId(new Random().nextInt(10000));
            transaction.setAmount(new Random().nextInt(1000000));
            transaction.setDot(LocalDate.of(1900+new Random().nextInt(25),1+new Random().nextInt(10),1+new Random().nextInt(27) ));
            transaction.setTransactionType(TransactionType.NETBANKING);
            transactionList.add(transaction);
        }

        return transactionList;
    }

}
