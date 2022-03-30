package lab08;

public class StudentRecord {
    String SID;
    float Midterm;
    float Assignments;
    float Exam;
    float Mark = (.2f * Assignments + .3f * Midterm + .5f * Exam);


    public StudentRecord(String SID, float Midterm, float Assignments, float Exam){
        this.SID = SID;
        this.Midterm = Midterm;
        this.Assignments = Assignments;
        this.Exam = Exam;
    }

    public String getSID(){
        return SID;
    }

    public String getMidterm(){
        return Float.toString(Midterm);
    }

    public String getAssignments(){
        return Float.toString(Assignments);
    }

    public String getExam(){
        return Float.toString(Exam);
    }

    public String getMark(){
        return Float.toString(.2f * Assignments + .3f * Midterm + .5f * Exam);
    }

    public String getGrade(){

        float markF = .2f * Assignments + .3f * Midterm + .5f * Exam;

        if(49 >= markF){
            return "F";
        }
        if(59 >= markF){
            return "D";
        }
        if(69 >= markF){
            return "C";
        }
        if(79 >= markF){
            return "B";
        }
        return "A";
    }

}