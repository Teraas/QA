package DesignPatterns.Creational.Builder;

/***
 * Constructor will be private only Builder class can access.
 * create static builder class. And, implement methods to set variables.
 */
public class BankAccount {

    private String name;
    private String accountNumber;
    private String email;
    private boolean newsletter;

    public BankAccount(BankAccountBuilder bankAccountBuilder) {
        this.name = bankAccountBuilder.name;
        this.accountNumber = bankAccountBuilder.accountNumber;
        this.email = bankAccountBuilder.email;
        this.newsletter = bankAccountBuilder.newsletter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }
    // constructors/getters

    public static class BankAccountBuilder {
        // builder code
        private String name; // mandatory field
        private String accountNumber; // mandatory field
        private String email;
        private boolean newsletter;

        public BankAccountBuilder(String name, String accountNumber){
            this.name = name;
            this.accountNumber = accountNumber;
        }
        public BankAccountBuilder withEmail(String email){
            this.email = email;
            return this;
        }
        public BankAccount build() {
            return new BankAccount(this);
        }
    }

}
