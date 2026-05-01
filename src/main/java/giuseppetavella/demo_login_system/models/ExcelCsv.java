package giuseppetavella.demo_login_system.models;

import giuseppetavella.demo_login_system.enums.internal.CsvSeparator;
import giuseppetavella.demo_login_system.exceptions.CsvGenerationException;

/**
 * In a ExcelCsv, we always add the separator hint,
 * for simplicity.
 */
public class ExcelCsv extends Csv {
    
    public ExcelCsv(String[] fields, CsvSeparator separator) throws CsvGenerationException
    {
        super(fields, separator, true);
    }
    
    public ExcelCsv(String[] fields) throws CsvGenerationException
    {
        this(fields, CsvSeparator.COMMA);
    }

}
