// Assignment 6: ASU - CSE 205
// Name: Joseph Kharzo
// StudentID: 1218555299
//Lecture Date and Time: TTH 1:30-2:45
//Description: Creates the department class, consisting of getters, setters, and
//             an overrided toString() method. The class contains pertinent
//             information for each department, including the name of the
//             department, the number of faculty in the department, and the 
//             university the department is located in.

public class Department
{
    // Instance variables
    private String name;
    private int numberOfFaculty;
    private String university;

    // Department constructor which initializes the variables
    public Department()
    {
       name = "?";
       numberOfFaculty = 0;
       university = "?";
    }
    //accesssor method
    public String getDeptName()
    {
       return name;
    }
    public int getNumberOfMembers()
    {
       return numberOfFaculty;
    }
    public String getUniversity()
    {
       return university;
    }

    //mutator methods
    public void setDeptName(String name)
    {
       this.name = name;
    }
    public void setNumberOfMembers(int numFaculty)
    {
       this.numberOfFaculty = numFaculty;
    }
    public void setUniversity(String name)
    {
       this.university = name;
    }

    @Override
    public String toString()
    {
       return "\nDepartment Name:\t\t" + name + "\nNumber Of Faculty:\t" + numberOfFaculty +
                 "\nUniversity:\t\t" + university + "\n\n";
    }
}

