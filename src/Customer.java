import java.time.LocalDate;
import java.util.ArrayList;

public class Customer {

    private ArrayList<LocalDate> visitTheGym = new ArrayList<>();
    private String socialSecurityNumber;
    private String name;
    private String membershipPaid; // Detta är relevant för enskild person,

    public Customer(String name, String socialSecurityNumber, String membershipPaid) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.name = name;
        this.membershipPaid = membershipPaid;
    }

    // Här börjar getters

    public String getName() {
        return name;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getMembershipPaid() {
        return membershipPaid;
    }

    // Här slutar getters

    // Här börjar metoder för Kunderna

    public void addVisit(LocalDate date) {
        visitTheGym.add(date);
    }


    public ArrayList<LocalDate> getVisits() {
        return visitTheGym;
    }

    // Sist så kör man toString

    @Override
    public String toString() {
        return "CustomerKund{" +
                "socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", name='" + name + '\'' +
                ", membershipPaid='" + membershipPaid + '\'' +
                '}';
    }

    // Lärdom kommentarer nedanför.

    //  protected LocalDate date;
    //  Inte aktuellt för personen för att han/hon skiter i dagens datum, inte relevant
}
