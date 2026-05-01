package giuseppetavella.demo_login_system.models;

import giuseppetavella.demo_login_system.exceptions.CsvGenerationException;
import giuseppetavella.demo_login_system.helpers.FileHelper;

import java.util.Arrays;

public class Csv {
    
    private final StringBuilder csv;
    private final String[] fields;
    private final String separator;

    /**
     * Initialize a CSV with a custom separator.
     */
    public Csv(String[] fields, String separator) throws CsvGenerationException
    {
        if(fields.length == 0) {
            throw new CsvGenerationException("Csv must have at least 1 header field.");
        }
        
        this.csv = new StringBuilder();
        this.fields = fields;
        this.separator = separator;
        this.generateHeader();
    }

    /**
     * Initialize a CSV with a comma as default separator
     */
    public Csv(String[] fields) throws CsvGenerationException
    {
        this(fields, ",");
    }

    /**
     * Add a row to the CSV.
     * The number of values in each row 
     * must match the number of header fields.
     */
    public void addRow(String... values) throws CsvGenerationException
    {
        // check if the number of values is different 
        // from the number of fields
        if(values.length != getFields().length) {
            throw new CsvGenerationException("While adding a row to a csv, the number "
                                            +"of values in this row was different than "
                                            +"the number of header fields. "
                                            +"Number of values in this row was '" + values.length + "'. " 
                                            +"Number of header fields was '" + getFields().length + "'. "
                                            +"The source of truth is the number of header fields or row values?");
        }
        
        StringBuilder csv = this.getCsv();
        String separator = this.getSeparator();
        
        for(String value : values) {
            csv.append(value).append(separator);
        }
        
        // add a newline to signal the end of the row
        csv.append("\n");
        
    }

    /**
     * Initialize the CSV with a header row.
     * Must only be generated once at initialization.
     */
    private void generateHeader() {

        StringBuilder csv = this.getCsv();
        String separator = this.getSeparator();
        
        int lastFieldIdx = fields.length-1;
        String lastField = fields[lastFieldIdx];

        // build the header
        // all fields except last
        for(int i = 0; i < fields.length-1; i++) {
            String field = fields[i];
            csv.append(field);
            // separate each field with a comma at the end
            csv.append(separator);
        }

        // last field does not have a comma at the end
        csv.append(lastField);
        // add a newline to header
        csv.append("\n");

    }

    
    /**
     * This csv -> bytes 
     */
    public byte[] toBytes() {
        return this.getCsv().toString().getBytes();
    }

    /**
     * This csv -> base64 
     */
    public String toBase64() {
        return FileHelper.toBase64(this.toBytes());
    }

    public StringBuilder getCsv() {
        return csv;
    }

    public String[] getFields() {
        return fields;
    }

    public String getSeparator() {
        return separator;
    }

    /**
     * When a {@code .toString()} is called on a Csv object,
     * the csv that was built so far is returned.
     */
    @Override
    public String toString() {
        return this.getCsv().toString();
    }
    
}
