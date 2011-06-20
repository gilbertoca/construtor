package park.model.dto;

    public class EmployeeLookUp {

        private Long id;
        private String employeeName;

        public EmployeeLookUp(Long id, String employeeName) {
            this.id = id;
            this.employeeName = employeeName;
        }

        public Long getId() {
            return id;
        }

        public String getEmployeeName() {
            return employeeName;
        }
    }
