package giuseppetavella.demo_login_system.models;

import giuseppetavella.demo_login_system.helpers.FileHelper;
import giuseppetavella.demo_login_system.interfaces.EmailAttachable;
import giuseppetavella.demo_login_system.services.file_generators.CsvGeneratorService;

public class Csv implements EmailAttachable {
    
    private final byte[] csv;
    
    public Csv(byte[] csv) {
        this.csv = csv;
    }
    
    public Csv(String csv) {
        this(csv.getBytes());
    }
    
    public Csv(CsvGeneratorService csvGen) {
        this(csvGen.getCsv().toString());
    }
    
    @Override
    public String toAttachment() {
        return FileHelper.toBase64(this.getCsv());
    }

    public byte[] getCsv() {
        return csv;
    }
}
