/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.report.htmlhelper;

/**
 *
 * @author Z
 */
public class HTMLTagLeaf extends HTMLTagComponent {

    public HTMLTagLeaf(String tagcontent) {
        super("", tagcontent);
    }
    
    @Override
    public String toHTMLTagString() {
        return super.tagcontent;
    }
}
