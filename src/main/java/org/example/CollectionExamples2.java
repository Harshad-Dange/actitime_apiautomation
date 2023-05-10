package org.example;

import java.util.*;

public class CollectionExamples2 {

    public static void main(String[] arg) {

        //List
        List list = new ArrayList();
        list.add(89);
        list.add(true);
        list.add("abc");

        System.out.println(list);

        List<String> arrayList1 = new ArrayList(); // [Pune, Mumbai]
        arrayList1.add("Pune1");
        arrayList1.add("Mumbai1");

        System.out.println(arrayList1);

        List<String> arrayList2 = new ArrayList<>();
        arrayList2.add("Pune2");
        arrayList2.add("Mumbai2");

//        System.out.println(arrayList.get(0));
//        System.out.println(arrayList.get(1));
//        arrayList.remove("Pune");
//        arrayList.remove(1);
        arrayList1.addAll(arrayList2);
        System.out.println(arrayList2);
        System.out.println(arrayList1);

/*
    [
    {num1:1,num2:2},
    {num1:15,num2:23},
    {num1:13,num2:22}
    ]
    List<Map<String, Integer>>
*/

        List<Map<String, Integer>> arrayList3 = new ArrayList<>();

        Map<String, Integer> map1 = new HashMap<>();
        map1.put("num1", 1);
        map1.put("num2", 2);
        arrayList3.add(map1);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("num1", 3);
        map2.put("num2", 4);
        arrayList3.add(map2);

        System.out.println(map2.get("num1").hashCode());

        Map<String, Integer> map3 = new HashMap<>();
        map3.put("num1", 3);
        map3.put("num2", 6);
        arrayList3.add(map3);

        System.out.println(arrayList3);

        // [ { "ids": [1,2,3,4]}]
        //
        //
        // List<Map<String, List<Integer>>>


        List<Map<String, List<Integer>>> arrayList4 = new ArrayList<>();

        Map<String, List<Integer>> map = new HashMap<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);  // [1,2,3]
        map.put("ids", list1);  // { "ids": [1,2,3]}
        arrayList4.add(map); //[{ "ids": [1,2,3,4]}]

        System.out.println(arrayList4);

        //Map with non primetive data type
         /*
         {
             "empDetails": {
                          "name": "cyber",
                          "empId": 100,
                          "address":"pune",
                          "cities": ["pune", "mumbai"]
                          }
          }

               [2,1,3,4,2,1,4], [2,1,3,4]
          */

        //        Map<String, Map<String, Object>>

        Map<String, Map<String, Object>> map4 = new HashMap<>();
        Map<String, Object> map5 = new HashMap<>();
        map5.put("name", "cyber");
        map5.put("empId", 100);
        map5.put("address", "pune");
        map5.put("cities", Arrays.asList("Pune", "Mumbai"));
        List<String> strings = Arrays.asList("Pune", "Mumbai");
//        map5.put("cities", strings);


        map4.put("empDetails", map5);

        System.out.println(map4);

        Map<String, Object> var1 = map4.get("empDetails");
        Object v = var1.get("empId");
        System.out.println(v);
     /*    [ {data: [ {},{},{}],city,compN}]
     [ -->detailsList
         {
            "data": [
                {
                "name": "cyber",
                "empId": 100,
                "salary": 20000
                },
                {
                "name": "success",
                "empId": 1001,
                "salary": 200001
                },
                {
                "name": "api",
                "empId": 1002,
                "salary": 20001
                }
            ],
            "city": "pune",
            "companyName": "TCS"
            }
        ]
     */

        List<Map<String, Object>> list2 = new ArrayList<>();

        Map<String, Object> map6 = new HashMap<>();
        List<Map<String, Object>> list3 = new ArrayList<>();
        Map<String, Object> map7 = new HashMap<>();
        map7.put("name", "cyber");
        map7.put("empId", 100);
        map7.put("salary", 20000);
        list3.add(map7);

        Map<String, Object> map8 = new HashMap<>();
        map8.put("name", "success");
        map8.put("empId", 200);
        map8.put("salary", 30000);
        list3.add(map8);

        map6.put("data", list3);
        map6.put("city", "pune");
        map6.put("companyName", "TCS");

        list2.add(map6);

        System.out.println(list2);

        for (Map<String, Object> obj : list2) {
            List<Map<String, Object>> dataList = (List<Map<String, Object>>) obj.get("data");
            for (Map<String, Object> map9 : dataList) {
                System.out.println(map9.get("empId"));
            }
        }







       /*
       {
        "data": [
            {
               "ids": [2,1,3,4,5],
               "department": "IT"
            },
            {
               "ids": [22,12,32,42,52],
               "department": "IT"
            }
        ],
        "empDetails": {
               "empId": 200,
               "salary": 1000
        },
        "active":true
       }*/


    }


}
