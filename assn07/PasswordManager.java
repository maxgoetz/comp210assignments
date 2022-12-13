package assn07;

import java.util.*;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password123";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }


    // TODO: put
    @Override
    public void put(K key, V value) {
        Account<K, V> accountToAdd = new Account<>(key, value);
        int hash = getHash(key);
        if (_passwords[hash] == null) {
            _passwords[hash] = accountToAdd;
        } else {
            if (get(key) != null) {
                setAccount(_passwords[hash], accountToAdd);
            } else {
                accountToAdd.setNext(_passwords[hash]);
                _passwords[hash] = accountToAdd;
            }
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int index = getHash(key);
        if (_passwords[index] == null) {
            return null;
        } else {
            Account currentAccount = getAccount(_passwords[index], key);
            if (currentAccount == null) {
                return null;
            }
            return (V) currentAccount.getPassword();
        }
    }

    // TODO: size
    @Override
    public int size() {
        int counter = 0;
        for (int i = 0; i < 50; i++) {
            counter = getSize(_passwords[i], counter);
        }
        return counter;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> websites = new HashSet<K>();
        for (int i = 0; i < 50; i++) {
            getWebsites(websites, _passwords[i]);
        }
        return websites;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int index = getHash(key);
        if (_passwords[index] == null) {
            System.out.println("Account does not exist");
            return null;
        }
        if (_passwords[index].getWebsite().equals(key)) {
            V deletedPassword = (V) _passwords[index].getPassword();
            _passwords[index] = _passwords[index].getNext();
            System.out.println("Account deleted");
            return deletedPassword;
        }
        Account deletedAccount = removeWebsite(_passwords[index], key);
        if (deletedAccount == null) {
            System.out.println("Account does not exist");
            return null;
        }
        System.out.println("Account deleted");
        return (V) deletedAccount.getPassword();
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K>websites = new LinkedList<K>();
        for (int i = 0; i < 50; i++) {
            getDuplicates(websites, _passwords[i], value);
        }
        return websites;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        if (enteredPassword.equals(MASTER_PASSWORD)) {
            return true;
        } else {
            return false;
        }
    }

    /*
    Generates random password of input length
     */
    @Override
    public String generateRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    private int getHash(K Key) {
//        return 0;
        return Math.abs(Key.hashCode() % 50);
    }

    private void setAccount(Account currentAccount, Account accountToInsert) {
        if (currentAccount.getWebsite().equals(accountToInsert.getWebsite())) {
            currentAccount.setPassword(accountToInsert.getPassword());
            return;
        }
        setAccount(currentAccount.getNext(), accountToInsert);
    }

    private Account getAccount(Account currentAccount, K website) {
        if (currentAccount.getWebsite().equals(website)) {
            return currentAccount;
        }
        if (currentAccount.getNext() == null) {
            return null;
        }
        return getAccount(currentAccount.getNext(), website);
    }

    private int getSize(Account currentAccount, int counter) {
        if (currentAccount == null) {
            return counter;
        }
        return getSize(currentAccount.getNext(), counter + 1);
    }

    private void getWebsites(Set<K> websites, Account currentAccount) {
        if (currentAccount == null) {
            return;
        }
        websites.add((K) currentAccount.getWebsite());
        getWebsites(websites, currentAccount.getNext());
    }

    private Account removeWebsite(Account previousAccount, K websiteToDelete) {
        Account currentAccount = previousAccount.getNext();
        if (currentAccount == null) {
            return null;
        }
        if (currentAccount.getWebsite().equals(websiteToDelete)) {
            previousAccount.setNext(currentAccount.getNext());
            return currentAccount;
        }
        return removeWebsite(currentAccount, websiteToDelete);
    }

    private void getDuplicates(List<K> websites, Account currentAccount, V passwordToCheck) {
        if (currentAccount == null) {
            return;
        }
        if (currentAccount.getPassword().equals(passwordToCheck)) {
            websites.add((K) currentAccount.getWebsite());
        }
        getDuplicates(websites, currentAccount.getNext(), passwordToCheck);
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
