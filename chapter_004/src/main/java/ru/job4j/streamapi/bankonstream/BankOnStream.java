package ru.job4j.streamapi.bankonstream;


import ru.job4j.bank.Account;
import ru.job4j.bank.User;
import ru.job4j.bank.Bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class BankOnStream extends Bank {

    private boolean moved;

    @Override
    public List<Account> getUserAccounts(String passport) {
        TreeMap<User, ArrayList<Account>> treeMap = getUserList();
        return treeMap.entrySet()
                .stream()
                .filter(x -> x.getKey().getPassport().equals(passport))
                .map(x -> x.getValue())
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        l -> l.isEmpty() ? Collections.emptyList() : l.get(0)));
    }

    @Override
    public void deleteAccountFromUser(String passport, Account account) {
        List<Account> list = getUserAccounts(passport);
        list.stream()
                .filter(x -> (x.equals(account)))
                .map(list::remove);
    }

    @Override
    public void addAccountToUser(String passport, Account account) {
        getUserAccounts(passport).add(account);
    }

    private Account getAccount(String passport, String requisite) {
        return getUserAccounts(passport)
                .stream()
                .filter(x -> x.getRequisites().equals(requisite))
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        l -> l.isEmpty() ? null : l.get(0)));
    }

    @Override
    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account srcAccount = getAccount(srcPassport, srcRequisite);
        Account dstAccount = getAccount(dstPassport, dstRequisite);
        if (srcAccount != null && dstAccount != null && srcAccount.getValue() >= amount) {
            srcAccount.setValue(srcAccount.getValue() - amount);
            dstAccount.setValue(dstAccount.getValue() + amount);
            result = true;
        }
        return result;
    }
}
