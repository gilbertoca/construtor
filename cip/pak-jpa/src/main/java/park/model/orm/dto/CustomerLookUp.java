package park.model.orm.dto;

    public class CustomerLookUp {

        private Long id;
        private String customerName;

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
    }
