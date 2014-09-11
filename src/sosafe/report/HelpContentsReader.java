/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.report;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import sosafe.report.htmlhelper.HTMLTagComposite;

/**
 *
 * @author Z
 */
public class HelpContentsReader {
    private final HTMLFormatter formatter;

    public HelpContentsReader(HTMLFormatter formatter) {
        this.formatter = formatter;
    }
    
    public void buildHelpContents(URL inputfile) {
        
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputfile.openStream()));
            String read = in.readLine();
            if (read != null) {
                HTMLTagComposite baseContext = formatter.getCurrentContext();
                
                String heading = read;
                formatter.startNewSection(baseContext, SimpleHTMLFormatter.TAG_HEADING_LEVEL_1, heading);
                
                HTMLTagComposite paraContext = formatter.startNewSection(baseContext, SimpleHTMLFormatter.TAG_HEADING_LEVEL_2);
                formatter.writeInSection(paraContext, SimpleHTMLFormatter.TAG_HORIZONTAL_LINE, "");
                read = in.readLine();
                while (read != null) {
                    formatter.writeInSection(paraContext, SimpleHTMLFormatter.TAG_BREAK_LINE, read);
                    read = in.readLine();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HelpContentsReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HelpContentsReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
