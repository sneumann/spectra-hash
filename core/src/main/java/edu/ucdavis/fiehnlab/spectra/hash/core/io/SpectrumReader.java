package edu.ucdavis.fiehnlab.spectra.hash.core.io;

import edu.ucdavis.fiehnlab.spectra.hash.core.Ion;
import edu.ucdavis.fiehnlab.spectra.hash.core.Spectrum;
import edu.ucdavis.fiehnlab.spectra.hash.core.impl.SpectrumImpl;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * reads a spectrum from the file input stream and notifies our spectrum handler
 */
public class SpectrumReader {

    private Logger logger = Logger.getLogger(getClass().getName());
    /**
     * read a spectrum and notify our handler
     * @param reader
     * @param handler
     */
    public void readSpectrum(Reader reader,SpectraHandler handler) throws IOException {
        Scanner scanner = new Scanner(reader);

        handler.begin();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine().trim();

            String origin = "unknown";

            line = line.replaceAll(" {2,}","\t");

            if(line.contains("\t")){
                String t[] = line.split("\t");

                origin = t[0];
                line = t[1];

                if(t.length == 3){
                    origin = origin + "_" + t[2];
                }
            }

            if(line.contains(":") && line.contains(" ")) {
                ArrayList<Ion> ions = new ArrayList<Ion>();

                for (String s : line.split(" ")) {
                    String[] content = s.split(":");

                    Ion ion = new Ion();
                    ion.setMass(Double.parseDouble(content[0]));
                    ion.setIntensity(Double.parseDouble(content[1]));
                    ions.add(ion);
                }

                handler.handle(new SpectrumImpl(ions,origin));
            }
        }

        handler.done();
    }

}
