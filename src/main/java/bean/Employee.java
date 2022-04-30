package bean;

/**
 * @Author聆雨
 * @Date11:13
 * @Version 1.0
 */
public class Employee {
    private int id;
    private String empname;
    private String empAccount;
    private String empPassword;
    private String job;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmpAccount() {
        return empAccount;
    }

    public void setEmpAccount(String empAccount) {
        this.empAccount = empAccount;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empname='" + empname + '\'' +
                ", empAccount='" + empAccount + '\'' +
                ", empPassword='" + empPassword + '\'' +
                ", job='" + job + '\'' +
                ", age=" + age +
                '}';
    }


}
