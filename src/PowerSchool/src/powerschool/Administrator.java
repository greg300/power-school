package PowerSchool.src.powerschool;

/**
 * Created by gregorygiovannini on 2/10/16.
 */
public class Administrator extends Person
{
    private String adminID;
    private int numAdmins;

    public Administrator(String username, String password, String firstName, String lastName)
    {
        generateAdminID();
        password = adminID;
        username = lastName;
    }

    public void generateAdminID()
    {
        adminID = "200"+numAdmins;
        numAdmins++;
    }

    @Override
    public String getString()
    {
        return null;
    }

    @Override
    public double getNumerical()
    {
        return 0;
    }
}
