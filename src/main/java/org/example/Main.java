package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<EmployeeDetails> detailsList = new ArrayList<>();

        EmployeeDetails employeeDetails= new EmployeeDetails();
        List<Map<String,Object>> data = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("name", "cyber");
        map1.put("empId", 100);
        map1.put("salary", 20000);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("name", "success");
        map2.put("empId", 1001);
        map2.put("salary", 20001);

        data.add(map1);
        data.add(map2);

        employeeDetails.setData(data);
        employeeDetails.setCity("Pune");
        employeeDetails.setCompanyName("TCS");

        Optional.of(employeeDetails).ifPresent(val-> System.out.println(val.getData()));

        detailsList.add(employeeDetails);
//        List<String>
        for(EmployeeDetails emp : detailsList){
            System.out.println(emp.getData());
            for(Map<String, Object> dataDetails: emp.getData()){
                Object salary= dataDetails.get("salary");
//                int sal = (int)salary;
//                Integer s = Integer.valueOf(salary.toString());
//                System.out.println(s.getClass());
                System.out.println(salary);
//                System.out.println(sal);
            }
            System.out.println(emp.getCity());
            System.out.println(emp.getCompanyName());

        }

        //create customer details class object to set values
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setId(15);
        customerDetails.setName("Cyber Success");
        customerDetails.setArchived(true);
//        Map<String,Boolean> actions = new HashMap<>();
//        actions.put("canModify", true);
//        actions.put("canDelete", false);
//        customerDetails.setAllowedActions(actions);
        Actions actions = new Actions();
        actions.setCanDelete(true);
        actions.setCanModify(true);
        customerDetails.setAllowedActions(actions);
        customerDetails.setDescription("sample desc");

        System.out.println(customerDetails);

        //print id value
        System.out.println(customerDetails.getId());
        //print created value
        System.out.println(customerDetails.getCreated());
        //print canModify value
        Actions act = customerDetails.getAllowedActions();
        System.out.println(act.getCanDelete());

        System.out.println(act.getCanModify());

    }
}