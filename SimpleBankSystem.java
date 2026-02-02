import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SimpleBankSystem{
	private static final String INT_SET="0123456789";
	private static final String CHAR_SET="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final Random random= new Random();
	private static final Scanner input=new Scanner(System.in);
	private static Map<String, List<Account>> accountsByCustomer= new HashMap<>();
	private static Map<Integer,Double> accountBalances=new HashMap<>();
	private static Map<Integer, Map<String, Double>> accountTransaction= new HashMap<>();
	public static void main(String[] args){
		while(true){
			System.out.println("Bank System Option");
			System.out.println("1. Add new customer");
			System.out.println("2. Open account for customer");
			System.out.println("3. Deposit Money");
			System.out.println("4. Withdraw Money");
			System.out.println("5. Transfer Money");
			System.out.println("Choose number to proceed to next action");
			int code=input.nextInt();
			input.nextLine();
			
			switch (code){
				case 1:
					addCustomer();
					break;
				case 2:
					addAccountforCustomer();
					break;
				case 3:
					depositMoney();
					break;
				case 4:
					withdrawMoney();
					break;
				case 5:
					transferMoney();
					break;
				default:
					System.out.println("you press incorrect option");
			}			
		}
		
	}
	public static void depositMoney(){
		System.out.println("Enter account number");
		Integer accNumber= input.nextInt();
		input.nextLine();
		System.out.println("Enter amount for deposit");
		Double deposit=input.nextDouble();
		input.nextLine();		
		accountBalances.compute(accNumber, (k, v) -> (v == null ? 0.0 : v) + deposit);
		// Handle transaction map safely
		accountTransaction.computeIfAbsent(accNumber, k -> new HashMap<>())
						  .put(generateRandomID(14), deposit);
		System.out.println("Deposit successful. New balance: " + accountBalances.get(accNumber));
	

					   
	}
	public static void withdrawMoney(){
		System.out.println("Enter account number");
		Integer accNumber= input.nextInt();
		input.nextLine();
		System.out.println("Enter amount to withdraw");
		Double withdraw=input.nextDouble();
		input.nextLine();		
		accountBalances.compute(accNumber, (k, v) -> (v == null ? 0.0 : v) - withdraw);
		// Handle transaction map safely
		accountTransaction.computeIfAbsent(accNumber, k -> new HashMap<>()).put(generateRandomID(14), withdraw);
		System.out.println("Withdraw successful. New balance: " + accountBalances.get(accNumber));
		
	}
	public static void transferMoney(){
		System.out.println("Enter account number from");
		Integer accFrom= input.nextInt();
		input.nextLine();
		
		System.out.println("Enter account number to transfer");
		Integer accTo= input.nextInt();
		input.nextLine();
		
		System.out.println("Enter amount to transfer");
		Double transfer=input.nextDouble();
		input.nextLine();		
		
		accountBalances.compute(accFrom, (k,v) -> (v==null?0.0:v) - transfer);
		accountBalances.compute(accTo,(k,v) ->(v == null? 0.0 : v)+ transfer);
		
		
		accountTransaction.computeIfAbsent(accFrom, k -> new HashMap<>()).put(generateRandomID(14), -transfer);
		accountTransaction.computeIfAbsent(accTo, k -> new HashMap<>()).put(generateRandomID(14), transfer);
		
		System.out.println("Transfer successful. Source balance: " + accountBalances.get(accFrom)+ "Destination balance"+ accountBalances.get(accTo));
		
	}
	public static void addAccountforCustomer(){
		System.out.println("Please enter user name to add account");
		String userName=input.nextLine();
		Account newAccount= new Account(Integer.parseInt(generateRandomNumber(4)),Integer.parseInt(generateRandomNumber(9)),0.0);
		accountsByCustomer.computeIfAbsent(userName, k -> new ArrayList<>()).add(newAccount);
		System.out.println("New Account created for"+userName+" with account number:"+ newAccount.getAccNo());
	}
	
	public static String generateRandomNumber(int codeLength) {
		StringBuilder code = new StringBuilder(codeLength);

		for (int i = 0; i < codeLength; i++) {
			int index = random.nextInt(INT_SET.length());
			code.append(INT_SET.charAt(index));
		}
	
		return code.toString();
		
	}
	
	public static String generateRandomID(int codeLength) {
		StringBuilder code = new StringBuilder(codeLength);

		for (int i = 0; i < codeLength; i++) {
			int index = random.nextInt(CHAR_SET.length());
			code.append(CHAR_SET.charAt(index));
		}

		return code.toString();
	}
	
	public static void addCustomer(){
		System.out.println("Please enter user name");
		String userName= input.nextLine();			
		accountsByCustomer.put(userName, new ArrayList<>());
		System.out.println("Customer has been added successfully"+ userName);
		
	}
}
