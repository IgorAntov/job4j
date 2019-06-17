package ru.job4j.streamapi.bankonstream;

import ru.job4j.bank.Account;
import ru.job4j.bank.Bank;
import ru.job4j.bank.User;

import java.util.ArrayList;
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
                        l -> l.isEmpty() ? null : l.get(0)));
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

    public void setMoved(boolean result) {
        this.moved = result;
    }

    public boolean isMoved() {
        return this.moved;
    }

    @Override
    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        TreeMap<User, ArrayList<Account>> treeMap = getUserList();
        treeMap.entrySet()
                .stream()
                .filter(x -> x.getKey().getPassport().equals(srcPassport))
                .forEach(q -> q.getValue()
                        .stream()
                        .filter(a -> a.getRequisites().equals(srcRequisite))
                        .forEach(d -> {
                            if (d.getValue() >= amount) {
                                d.setValue(d.getValue() - amount);
                                treeMap.entrySet()
                                        .stream()
                                        .filter(x -> x.getKey().getPassport().equals(dstPassport))
                                        .forEach(s -> s.getValue()
                                                .stream()
                                                .filter(a -> a.getRequisites().equals(dstRequisite))
                                                .forEach(p -> p.setValue(p.getValue() + amount))
                                        );
                                setMoved(true);
                            }
                        })
                );
        return isMoved();
    }
}
