/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.report;

import sosafe.report.htmlhelper.HTMLTagComposite;

/**
 *
 * @author Z
 */
public interface HTMLFormatter {
    public HTMLTagComposite getCurrentContext();
    
    HTMLTagComposite startNewSection(HTMLTagComposite context, String tag);
    HTMLTagComposite startNewSection(HTMLTagComposite context, String tag, String content);
    HTMLTagComposite startNewSection(HTMLTagComposite context, String tag, String attribute, String content);
    
    void writeInSection(HTMLTagComposite context, String content);
    void writeInSection(HTMLTagComposite context, String tag, String content);
    void writeInSection(HTMLTagComposite context, String tag, String attribute, String content);
    
    String getResult(boolean addDoctype);
}