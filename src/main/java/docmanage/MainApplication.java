package docmanage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainApplication {
    public static void main(String[] args) throws IOException {
        DocumentManagementSystem system = new DocumentManagementSystem();

        system.addDocumentType("invoice", new InVocieImporter());
        String resourcesDir = "src/main/resources/";

        system.importFile(resourcesDir + "patient.invoice");
        system.importFile(resourcesDir + "patient.letter");
        system.importFile(resourcesDir + "patient.report");
//        system.importFile(resourcesDir + "xray.jpg");

        System.out.println("---");

        List<Document> result = system.search("patient:Joe Bloggs,body:Diet Coke");

        System.out.println(result);
    }
}
