package com.company;

import com.company.exceptions.InvalidCatalogException;
import com.company.utils.CatalogUtil;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() {
        try {
            Catalog catalog = new Catalog("Java Resources", "catalog.ser");
            Document doc = new Document("java1", "Java Course 1", "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
            doc.addTag("type", "Slides");
            catalog.add(doc);
            CatalogUtil.save(catalog);
        }
        catch (IOException e){
            System.out.println("An error ocurred - " + e);
            return;
        }
        System.out.println("Test passed");
    }

    private void testLoadView() {
        try {
            Catalog catalog = CatalogUtil.load("catalog.ser");
            Document doc = catalog.findById("java1");
            CatalogUtil.view(doc);
        }
        catch (InvalidCatalogException c){
            System.out.println("Could not load catalog - " + c.getMessage());
            return;
        }
        catch (IOException e){
            System.out.println("Catalog not found");
            return;
        }
        System.out.println("PASS");
    }
}
