package roadguard.model;

public class Complaint {

    private int complaintId;
    private String citizenEmail;
    private String description;
    private String location;
    private String status;

    public Complaint() {
    }

    public Complaint(String citizenEmail, String description, String location) {
        this.citizenEmail = citizenEmail;
        this.description = description;
        this.location = location;
        this.status = "PENDING";
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getCitizenEmail() {
        return citizenEmail;
    }

    public void setCitizenEmail(String citizenEmail) {
        this.citizenEmail = citizenEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}