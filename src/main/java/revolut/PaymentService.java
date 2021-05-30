package revolut;

public class PaymentService {
    private String serviceName;
    private boolean isValid;

    public PaymentService(String name){
        this.serviceName = name;
        this.isValid = true;
    }

    public String getType() {
        return serviceName;
    }

    public boolean getIsValid() {
        return this.isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}
