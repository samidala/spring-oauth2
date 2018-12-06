package project.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int patientId;
    private String houseNo;
    private String streetNo;
    private String name;
    private String city;
    private String state;
    private String country;
    private String zipcode;

    public Address() {

    }

    public Address(int patientId, String houseNo, String streetNo, String name, String city, String state, String country, String zipcode) {
        this.patientId = patientId;
        this.houseNo = houseNo;
        this.streetNo = streetNo;
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }

    public Address(int id, int patientId, String houseNo, String streetNo, String name, String city, String state, String country, String zipcode) {
        this.id = id;
        this.patientId = patientId;
        this.houseNo = houseNo;
        this.streetNo = streetNo;
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", houseNo='" + houseNo + '\'' +
                ", streetNo='" + streetNo + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
