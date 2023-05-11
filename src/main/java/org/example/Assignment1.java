package org.example;

import java.util.ArrayList;
import java.util.List;

public class Assignment1 {

    public static void main(String [] agr){

        GlossDef glossDef= new GlossDef();
        glossDef.setPara("This is para");

        List<String> glossSeeValues= new ArrayList<>();
        glossSeeValues.add("GML");
        glossSeeValues.add("XML");
        glossDef.setGlossSeeAlso(glossSeeValues);

        GlossEntry glossEntry= new GlossEntry();
        glossEntry.setId(10);
        glossEntry.setSortAs("ABC");
        glossEntry.setGlossTerm("Standard Generalized Markup Language");
        glossEntry.setAcronym("SGML");
        glossEntry.setAbbrev("ISO 8879:1986");
        glossEntry.setGlossDef(glossDef);
        glossEntry.setGlossSee("markup");

        GlossList glossList= new GlossList();
        glossList.setGlossEntry(glossEntry);

        GlossDiv glossDiv= new GlossDiv();
        glossDiv.setTitle("Java");
        glossDiv.setGlossList(glossList);

        Glossary glossary = new Glossary();
        glossary.setTitle("Glossary Title");
        glossary.setGlossDiv(glossDiv);

        GlossaryDetails glossaryDetails= new GlossaryDetails();
        glossaryDetails.setGlossary(glossary);

//        new GlossaryDetails().getGlossary();

       List<String> values= glossaryDetails.getGlossary().getGlossDiv().getGlossList().getGlossEntry().getGlossDef().getGlossSeeAlso();

       values.forEach(v -> System.out.println(v));

       values.forEach(System.out::println);

       for(String var : values){
           System.out.println(var);
       }

       for(int i =0; i<values.size();i++) {
           System.out.println(values.get(i));
       }

    }



}
