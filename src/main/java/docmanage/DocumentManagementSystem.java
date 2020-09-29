package docmanage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class DocumentManagementSystem {

    private final Map<String, Importer> extensionToImporter = new HashMap<>();
    private final List<Document> documents = new ArrayList<>();
    private final List<Document> documentsView = unmodifiableList(documents);

    public DocumentManagementSystem() {
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("jpg", new ImageImporter());
    }

    public void importFile(String path) throws IOException {
        final File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        final int separatorIndex = path.lastIndexOf(".");
        if (separatorIndex != -1) {
            if (separatorIndex == path.length()) {
                throw new UnKnownFileTypeException();
            }

            final String extension = path.substring(separatorIndex + 1);
            final Importer importer = extensionToImporter.get(extension);
            if (importer == null) {
                throw new UnKnownFileTypeException("For file: " + path);
            }

            final Document doc = importer.importFile(file);
            documents.add(doc);
        } else {
            throw new UnKnownFileTypeException("No extension found For file: " + path);
        }
    }

    public void addDocumentType(String type, Importer importer) {
        if (extensionToImporter.get(type) == null) {
            extensionToImporter.put(type, importer);
        } else {
            System.out.println(type + " has already added type");
        }
    }

    public List<Document> search(final String query) {
        return documents.stream()
                .filter(Query.parse(query))
                .collect(Collectors.toList());
    }

    public List<Document> contents() {
        return documentsView;
    }
}
