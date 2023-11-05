import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class AppInitializer {
    public static int printQuestions(String[] questions, Scanner input){


        System.out.println("Select an option...");
        for (String tempQuestion: questions
        ) {
            System.out.println(tempQuestion);
        }


        int answerNumber = input.nextInt();
        if (answerNumber>6 || answerNumber<=0){
            System.out.println("wrong Input & try again");
            System.exit(0);
        }
        return answerNumber-1;
    }

    public static boolean isExist(String[][] customers, String nic){
//        if (customers[0][0]==null){
//           return false;
//        }

        for (int i = 0; i < customers.length; i++) {
            for (int j = 0; j < customers[i].length; j++) {
                if (Objects.equals(customers[i][0], nic)){
                    System.out.println("Customer already exist in the database. Please try again with a different NIC");
                    return true;
                }
            }
        }
        return false;
    }

    public static void deleteCustomer(String[][] customers, Scanner input){
        System.out.println("Enter Customer NIC");
        String selectedNic = input.next();

        if (null!=selectedNic){
            for (int i =0; i < customers.length; i++) {
                for (int j = 0; j < customers[i].length; j++) {
                    if (Objects.equals(customers[i][0], selectedNic)){
                        customers[i][0] = null;
                        customers[i][1] = null;
                        customers[i][2] = null;
                        System.out.println("Customer deleted successfully");

                        // shift the array to left
                        for (int k = i; k < customers.length-1; k++) {
                            customers[k][0] = customers[k+1][0];
                            customers[k][1] = customers[k+1][1];
                            customers[k][2] = customers[k+1][2];
                        }
                        return;
                    }
                }
            }
            System.out.println("Customer not found");
        }else {
            System.out.println("Invalid NIC");
        }
    }

    public static void updateCustomer(String[][] customers, Scanner input){
        System.out.println("Enter Customer NIC");
        String selectedNic = input.next();

        if (null!=selectedNic){
            for (int i =0; i < customers.length; i++) {
                for (int j = 0; j < customers[i].length; j++) {
                    if (Objects.equals(customers[i][0], selectedNic)){

                        System.out.println("Enter Customer Name");
                        customers[i][1] = input.next();
                        System.out.println("Enter Customer Address");
                        customers[i][2] = input.next();
                        System.out.println("Customer updated successfully");
                        return;
                    }
                }
            }
            System.out.println("Customer not found");
        }else {
            System.out.println("Invalid NIC");
        }
    }

    public static void addCustomer(String[][] customers, Scanner input){

        boolean addStatus= true;
        while (addStatus){
            if (customers[customers.length-1][0]!=null){
                System.out.println("Database is full");
                return;
            }

           outer : for (int i = 0; i < customers.length; i++) {
              inner :  for (int j = 0; j < customers[i].length; j++) {
                    if (customers[i][j]==null){

                        String selectedNic= "";
                        do {
                            System.out.println("Enter Customer NIC");
                            selectedNic = input.next();
                        } while (isExist(customers, selectedNic));

                        customers[i][0] = selectedNic;

                        System.out.println("Enter Customer Name");
                        customers[i][1] = input.next();
                        System.out.println("Enter Customer Address");
                        customers[i][2] = input.next();
                        break outer;
                    }else {
                        break;
                    }
                }
            }

            System.out.println("Do you want to add another customer?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            int answer = input.nextInt();
            if (answer!=1){
                addStatus = false;
            }
        }

    }

    public static void findAndPrintCustomer(String[][] customers, Scanner input){
//        input.next();
        // find customer
        System.out.println("Enter Customer NIC");
        String selectedNic = input.next();

        if (null!=selectedNic){
            for (int i =0; i < customers.length; i++) {
                for (int j = 0; j < customers[i].length; j++) {
                    if (Objects.equals(customers[i][0], selectedNic)){
                        System.out.println(Arrays.toString(customers[i]));
                        return;
                    }
                }
            }
            System.out.println("Customer not found");
        }else {
            System.out.println("Invalid NIC");
        }
    }

    public static void printAllCustomers(String[][] customers){
        for (int i = 0; i < customers.length; i++) {
               if (customers[i][0]!=null){
                      System.out.println(Arrays.toString(customers[i]));
               }


        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String msg = "Welcome to Customer Management System";
        System.out.println(msg);

        String [][] customers = new String[10][3];

        String [] questions = new String[6];
        questions[0]= "(1) Add Customer";
        questions[1]= "(2) Find Customer";
        questions[2]= "(3) Delete Customer";
        questions[3]= "(4) Update Customer";
        questions[4]= "(5) Print All Customers";
        questions[5]= "(6) Logout";
        while (true){
            int answer = printQuestions(questions, input);
            switch (answer){
                case 0:
                    addCustomer(customers, input);
                    System.out.println(Arrays.deepToString(customers));
                    break;
                case 1:
                    findAndPrintCustomer(customers, input);
                    break;
                case 2:
                    deleteCustomer(customers, input);
                    System.out.println(Arrays.deepToString(customers));
                    break;
                case 3:
                    updateCustomer(customers, input);
                    break;
                case 4:
                    printAllCustomers(customers);
                    break;
                case 5:
                    System.out.println("Thank you for using our system");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }
}
