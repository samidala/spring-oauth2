package project.model;

import com.google.gson.annotations.SerializedName;

public class PatientPayload {

    @SerializedName("patientDetails")
    private Patient patient;
    private Contact contact;
    private Address address;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PatientPayload{" +
                "patient=" + patient +
                ", contact=" + contact +
                ", address=" + address +
                '}';
    }
}
