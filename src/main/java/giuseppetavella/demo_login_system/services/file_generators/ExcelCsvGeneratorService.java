package giuseppetavella.demo_login_system.services.file_generators;

import giuseppetavella.demo_login_system.enums.internal.CsvSeparator;
import giuseppetavella.demo_login_system.exceptions.CsvGenerationException;

/**
 * In a ExcelCsv, we always add the separator hint,
 * for simplicity.
 */
public class ExcelCsvGeneratorService extends CsvGeneratorService {
    
    public ExcelCsvGeneratorService(String[] fields, CsvSeparator separator) throws CsvGenerationException
    {
        super(fields, separator, true);
    }
    
    public ExcelCsvGeneratorService(String[] fields) throws CsvGenerationException
    {
        this(fields, CsvSeparator.COMMA);
    }

}
