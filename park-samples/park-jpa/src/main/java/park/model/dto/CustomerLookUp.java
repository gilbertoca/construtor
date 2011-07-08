package park.model.dto;

public class CustomerLookUp {

    private Long id;
    private String customerName;
    /** represents the Person type: NP or LE*/
    private String pTYPE;

    public CustomerLookUp(Long id, String customerName) {
        this.id = id;
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getpTYPE() {
        return pTYPE;
    }

    public void setpTYPE(String pTYPE) {
        this.pTYPE = pTYPE;
    }
}
