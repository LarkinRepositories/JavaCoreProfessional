package Lessons.Lesson_7.WebinarCodeSamples;

@Table(name = "bank_account")
public class BankAccount extends Account {

    public String cardId;

    public BankAccount(String id) {
        super(id);
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

}
