import java.util.Date;

public class Account {
    private int accID;
    private int accNo;
    private Date dateCreated;
    private double balance;

    public Account(int accID, int accNo, double balance) {
        this.dateCreated = new Date();
        this.accID = accID;
        this.accNo = accNo;
        this.balance = balance;
    }

    public int getAccID() { return accID; }
    public void setAccID(int accID) { this.accID = accID; }

    public int getAccNo() { return accNo; }
    public void setAccNo(int accNo) { this.accNo = accNo; }

    public Date getDateCreated() { return dateCreated; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
