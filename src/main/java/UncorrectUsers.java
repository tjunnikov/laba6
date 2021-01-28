public class UncorrectUsers {
    String last_name, gender, email, phone;

    public UncorrectUsers(String  lastName, String gender, String email, String phone)
    {
        this.last_name = lastName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
