package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalClassExamples {

    public static void main(String [] agr){

        List<Integer> list= null ;// List.of(1,2,4,6,8,3,10,12,13);
//        List<Integer> list= new ArrayList<>();
//        list.add(null);

//        for(Integer val : list){
//            if(val%2==0){
//                System.out.println(val);
//            }
//        }

        String s = new String();

        String s1 = null;

        System.out.println(s.isEmpty());

        System.out.println( s == null);
        System.out.println(s1 == null);
        /*
            if object in ofNullable is null then it return empty Optional else it returns object with
         Optional class reference
         */

        Optional<List<Integer>>  optional = Optional.ofNullable(list);

        System.out.println("Print the result for isPresent: "+optional.isPresent());
        System.out.println("Print the result for isEmpty :"+optional.isEmpty());

//        optional.ifPresent(obj-> obj.forEach(i->{
//            if(i%2==0){
//                System.out.println(i);
//            }
//        }));

        optional.ifPresentOrElse(obj-> obj.forEach(i->{
            if(i%2==0){
                System.out.println(i);
            }
        }),()-> System.out.println("The optional class object is null"));



    }





}
