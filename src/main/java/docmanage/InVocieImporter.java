package docmanage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static docmanage.Attributes.*;

public class InVocieImporter implements Importer {
    private static final String NAME_PREFIX = "Dear ";
    private static final String AMOUNT_PREFIX = "Amount: ";

    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);
        textFile.addLines(2, line -> false, BODY);
        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "INVOCIE");
        return new Document(attributes);
    }
}
