package PowerSchool.src.powerschool;

/**
 * Created by GregG on 2/27/16.
 */
public class ProgressReport
{
    private Class classStudentIsIn;
    private Student studentReportIsFor;
    private String report;
    private String reportID;

    public ProgressReport(Class classStudentIsIn, Student studentReportIsFor, String report)
    {
        PowerSchool.numProgressReportsExisted++;
        this.classStudentIsIn = classStudentIsIn;
        this.studentReportIsFor = studentReportIsFor;
        this.report = report;
        reportID = PowerSchool.numProgressReportsExisted + "";
    }

    public Class getClassStudentIsIn()
    {
        return classStudentIsIn;
    }

    public void setClassStudentIsIn(Class classStudentIsIn)
    {
        this.classStudentIsIn = classStudentIsIn;
    }

    public Student getStudentReportIsFor()
    {
        return studentReportIsFor;
    }

    public void setStudentReportIsFor(Student studentReportIsFor)
    {
        this.studentReportIsFor = studentReportIsFor;
    }

    public String getReport()
    {
        return report;
    }

    public void setReport(String report)
    {
        this.report = report;
    }

    public String getReportID()
    {
        return reportID;
    }

    public void setReportID(String reportID)
    {
        this.reportID = reportID;
    }
}
