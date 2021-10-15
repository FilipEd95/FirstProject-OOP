import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class AdministrationReception {

    ArrayList<Customer> registeredCustomers = new ArrayList<>();

    public AdministrationReception(String readFile, String visitFile) {

        // Variablerna för inläsningen av en Scanner.

        String firstRow;
        String secondRow;
        Path pathInName;

        String[] coustomerFirstLine;
        String[] customerSecondLine = new String[0];
        pathInName = Paths.get(readFile);

        // Här kör vi en try with resources med en scanner
        try (Scanner memberScanner = new Scanner(pathInName)) {

            while (memberScanner.hasNext()) {
                firstRow = memberScanner.nextLine();
                coustomerFirstLine = firstRow.split(",");
                if (memberScanner.hasNext()) {
                    secondRow = memberScanner.nextLine();
                    customerSecondLine = secondRow.split(",");
                }
                // Här så läser man av Första raden index 0 + index 1, för personNr och Namn.
                // Pathin är variabel för inläsningen i Customers.txt filen.
                Customer coustomer = new Customer(coustomerFirstLine[1].trim(), coustomerFirstLine[0].trim(),
                        customerSecondLine[0].trim());

                // registeredCustomers är en variabel för att läsa in alla registrerade kunder.

                registeredCustomers.add(coustomer);
            }

            // Här skapar vi en fil som trackar när personen kommer in på gymmet.

            File file = new File(visitFile);
            if (!file.exists()) return;

            // Scanner för besök
            Scanner visitScanner = new Scanner(Paths.get(visitFile));

            while (visitScanner.hasNext()) {

                String idNum = visitScanner.nextLine();

                // Skulle säkert kunna parse/konvertera denna int.

                int visitAmount = visitScanner.nextInt();
                visitScanner.nextLine();
                for (int i = 0; i < visitAmount; i++) {
                    String date = visitScanner.nextLine();
                    GetIfRegistered(idNum).addVisit(LocalDate.parse(date));
                }
            }

            // Exception e, som hanterar generella fel. MAN MÅSTE HA denna åtminstone,
            // annars är det JÄÄÄÄTTE osjysst mot andra programmerare eller att det
            // kan ta alldeles för lång tid att hitta felet.
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    // Denna metod kollar om kunden är aktiv

    protected boolean isCoustomerActive(Customer coustomer) {

        // Här kollar vi av om man betalat inom ett år.
        LocalDate date = LocalDate.now().minusYears(1);

        LocalDate inputDate = LocalDate.parse(coustomer.getMembershipPaid());
        Period p = Period.between(inputDate, date);

        // Ifall personen är medlem, så välkomnar vi tillbaka kunden.
        // Annars så skriver vi till kunden att hen är medlem men tiden har gått ut.
        if (p.isNegative()) {
            System.out.println("You are a member, welcome back");
            return true;
        } else {
            System.out.println("You are not welcome, your time has expired");
            return false;
        }
        // ENKLAST ATT TESTA, ALLTSÅ J_UNIT_TEST PÅ DENNA DEL
    }

    public Customer GetIfRegistered(String inputAnvändare) {

        for (Customer customer : registeredCustomers) {

            // Osäker på om jag ska indentera denna if customer eller inte, eftersom den går över sträcket i intellJ.

            if (customer.getSocialSecurityNumber().equals(inputAnvändare) || customer.getName().equalsIgnoreCase(inputAnvändare)) {
                return customer;
            }
        }
        return null;
    }

    public void writeVisitFile() {
        try {
            FileWriter file = new FileWriter(System.getProperty("user.dir") + "/visitsFile.txt");
            for (Customer customer : registeredCustomers) {
                file.write(customer.getName());
                file.write(System.getProperty("line.separator"));
                file.write(String.valueOf(customer.getVisits().size()));
                file.write(System.getProperty("line.separator"));
                for (LocalDate visit : customer.getVisits()) {
                    file.write(visit.toString());
                    file.write(System.getProperty("line.separator"));
                }
            }
            file.flush();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}