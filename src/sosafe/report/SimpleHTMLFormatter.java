/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.report;

import sosafe.report.htmlhelper.*;

/**
 *
 * @author Z
 */
public class SimpleHTMLFormatter implements HTMLFormatter {
    
    public static final String TAG_HEADING_LEVEL_1 = "h1";
    public static final String TAG_HEADING_LEVEL_2 = "h2";
    public static final String TAG_HEADING_LEVEL_3 = "h3";
    public static final String TAG_HEADING_LEVEL_4 = "h4";
    public static final String TAG_HEADING_LEVEL_5 = "h5";
    public static final String TAG_HEADING_LEVEL_6 = "h6";
    
    public static final String TAG_PARAGRAPH = "p";
    public static final String TAG_BREAK_LINE = "br";
    public static final String TAG_HORIZONTAL_LINE = "hr";
    
    public static final String TAG_TABLE = "table";
    public static final String TAG_TABLE_CAPTION = "caption";
    public static final String TAG_TABLE_ROW = "tr";
    public static final String TAG_TABLE_HEADING = "th";
    public static final String TAG_TABLE_DATA = "td";
    public static final String ATTRIBUTE_DEFAULT_TABLE = "border=\"1\"";
    
    private static final String TAG_DOCTYPE_HTML = "<!DOCTYPE html>";
    private static final String TAG_ELEMENT_HTML = "html";
    private static final String TAG_ELEMENT_BODY = "body";
    
    private final HTMLTagComposite htmlContainer;
    private final HTMLTagComposite bodyContainer;
    private HTMLTagComposite currentContext;
    
    public SimpleHTMLFormatter() {
        this.htmlContainer = new HTMLTagComposite(TAG_ELEMENT_HTML);
        this.bodyContainer = new HTMLTagComposite(TAG_ELEMENT_BODY);
        this.currentContext = this.htmlContainer.addSubTag(this.bodyContainer);
    }
    
    public HTMLTagComposite getCurrentContext() {
        return currentContext;
    }

    private void setCurrentContext(HTMLTagComposite currentContext) {
        this.currentContext = currentContext;
    }
    
    @Override
    public HTMLTagComposite startNewSection(HTMLTagComposite context, String tag) {
        return this.startNewSection(context, tag, null, "");
    }

    @Override
    public HTMLTagComposite startNewSection(HTMLTagComposite context, String tag, String content) {
        return this.startNewSection(context, tag, null, content);
    }

    @Override
    public HTMLTagComposite startNewSection(HTMLTagComposite context, String tag, String attribute, String content) {
        HTMLTagComposite newContext = new HTMLTagComposite(tag, attribute, content);
        context.addSubTag(newContext);
        this.setCurrentContext(newContext);
        return this.getCurrentContext();
    }
    
    @Override
    public void writeInSection(HTMLTagComposite context, String content) {
        this.setCurrentContext(context);
        this.getCurrentContext().addComponent(new HTMLTagLeaf(content));
    }

    @Override
    public void writeInSection(HTMLTagComposite context, String tag, String content) {
        this.writeInSection(context, tag, null, content);
    }

    @Override
    public void writeInSection(HTMLTagComposite context, String tag, String attribute, String content) {
        this.setCurrentContext(context);
        this.getCurrentContext().addComponent(new HTMLTagComponent(tag, attribute, content));
    }
    
    @Override
    public String getResult(boolean addDoctype) {
        String doctype = addDoctype? TAG_DOCTYPE_HTML: "";
        return doctype + this.htmlContainer.toHTMLTagString();
    }
}
