import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String memberFile = System.getProperty("user.dir") + "/Coustomers.txt";
        String visitFile = System.getProperty("user.dir") + "/visitsFile.txt";
        AdministrationReception ad = new AdministrationReception(memberFile, visitFile);

        // Här skriver du in din input.

        Scanner sc = new Scanner(System.in);
        System.out.println("Write your name  or your social security number : ");

        // Här kollar man om personen finns med i listan, om inte så säger man att personen inte är registrerad.
        Customer currentCustomer = ad.GetIfRegistered(sc.nextLine());
        if (currentCustomer == null) {
            System.out.println("Person is not registered");

        } else {
            if (ad.isCoustomerActive(currentCustomer)) {
                System.out.println("Person is a member and have paid the price within a year");
            } else {
                System.out.println("Person is a member but haven't paid the price within a year");
            }
            currentCustomer.addVisit(LocalDate.now());
        }
        ad.writeVisitFile();
    }
}