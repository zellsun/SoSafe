/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.report.htmlhelper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Z
 */
public class HTMLTagComposite extends HTMLTagComponent {
    private final List<HTMLTagComponent> subComps;
    
    public HTMLTagComposite(String tagname) {
        super(tagname);
        this.subComps = new ArrayList<>();
    }
    
    public HTMLTagComposite(String tagname, String tagcontent) {
        super(tagname, tagcontent);
        this.subComps = new ArrayList<>();
    }
    
    public HTMLTagComposite(String tagname, String tagattribute, String tagcontent) {
        super(tagname, tagattribute, tagcontent);
        this.subComps = new ArrayList<>();
    }
    
    public void addComponent(HTMLTagComponent comp) {
        this.subComps.add(comp);
    }
    
    public HTMLTagComposite addSubTag(HTMLTagComposite tag) {
        this.subComps.add(tag);
        return tag;
    }
    
    @Override
    public String toHTMLTagString() {
        String subCompString = "";
        for (HTMLTagComponent comp : this.subComps) {
            subCompString += comp.toHTMLTagString();
        }

        if (super.tagattribute != null) {
            return (HTMLTagComponent.TAG_START_LEFT + super.tagname + " " + super.tagattribute + HTMLTagComponent.TAG_START_RIGHT
                    + super.tagcontent
                    + subCompString
                    + HTMLTagComponent.TAG_END_LEFT + super.tagname + HTMLTagComponent.TAG_END_RIGHT);
        } else {
            return (HTMLTagComponent.TAG_START_LEFT + super.tagname + HTMLTagComponent.TAG_START_RIGHT
                    + super.tagcontent
                    + subCompString
                    + HTMLTagComponent.TAG_END_LEFT + super.tagname + HTMLTagComponent.TAG_END_RIGHT);
        }
    }
}
