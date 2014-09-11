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
public class HTMLTagComponent {
    public static final String TAG_START_LEFT = "<";
    public static final String TAG_START_RIGHT = ">";
    
    public static final String TAG_END_LEFT = "</";
    public static final String TAG_END_RIGHT = ">";
    
    private static final String ENTITY_SINGLE_SPACE_REGEX = " ";
    private static final String ENTITY_SINGLE_SPACE_REPLACEMENT = "&nbsp;";
    private static final String ENTITY_DOUBLE_SPACE_REGEX = "  ";
    private static final String ENTITY_DOUBLE_SPACE_REPLACEMENT = " &nbsp;";
    
    private static final String ENTITY_LESS_THAN_REGEX = "<";
    private static final String ENTITY_LESS_THAN_REPLACEMENT = "&lt;";
    
    private static final String ENTITY_GREATER_THAN_REGEX = ">";
    private static final String ENTITY_GREATER_THAN_REPLACEMENT = "&gt;";
    
    private static final String ENTITY_AMPERSAND_REGEX = "&amp;";
    private static final String ENTITY_AMPERSAND_REPLACEMENT = "&amp;";
    
    String tagname;
    String tagattribute;
    String tagcontent;
    
    private String HTMLEntitiesReplace(String input) {
        String output = input.replaceAll(ENTITY_AMPERSAND_REGEX, ENTITY_AMPERSAND_REPLACEMENT);
        output = output.replaceFirst(ENTITY_SINGLE_SPACE_REGEX, ENTITY_SINGLE_SPACE_REPLACEMENT);
        output = output.replaceAll(ENTITY_DOUBLE_SPACE_REGEX, ENTITY_DOUBLE_SPACE_REPLACEMENT);
        output = output.replaceAll(ENTITY_LESS_THAN_REGEX, ENTITY_LESS_THAN_REPLACEMENT);
        output = output.replaceAll(ENTITY_GREATER_THAN_REGEX, ENTITY_GREATER_THAN_REPLACEMENT);
        return output;
    }
    
    public HTMLTagComponent(String tagname) {
        this.tagname = tagname;
        this.tagattribute = null;
        this.tagcontent = "";
    }
    
    public HTMLTagComponent(String tagname, String tagcontent) {
        this.tagname = tagname;
        this.tagattribute = null;
        this.tagcontent = HTMLEntitiesReplace(tagcontent);
    }

    public HTMLTagComponent(String tagname, String tagattribute, String tagcontent) {
        this.tagname = tagname;
        this.tagattribute = tagattribute;
        this.tagcontent = HTMLEntitiesReplace(tagcontent);
    }
    
    public String toHTMLTagString() {
        if (this.tagattribute != null) {
            return (HTMLTagComponent.TAG_START_LEFT + this.tagname + " " + this.tagattribute + HTMLTagComponent.TAG_START_RIGHT
                    + this.tagcontent
                    + HTMLTagComponent.TAG_END_LEFT + this.tagname + HTMLTagComponent.TAG_END_RIGHT);
        } else {
            return (HTMLTagComponent.TAG_START_LEFT + this.tagname + HTMLTagComponent.TAG_START_RIGHT
                    + this.tagcontent
                    + HTMLTagComponent.TAG_END_LEFT + this.tagname + HTMLTagComponent.TAG_END_RIGHT);
        }
    }
}
