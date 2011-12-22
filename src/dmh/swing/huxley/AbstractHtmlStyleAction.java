/**
 * Junk - AbstractHtmlStyleAction.java
 * Copyright 2011 Dave Huffman (dave dot huffman at me dot com).
 * TODO license info.
 */
package dmh.swing.huxley;

import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Observable;

import javax.swing.Icon;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;

import dmh.swing.AbstractActionObserver;
import dmh.swing.html.SwingHtmlUtil;

/**
 * TODO Document.
 *
 * @author davehuffman
 */
public abstract class AbstractHtmlStyleAction extends AbstractActionObserver {
    private static final long serialVersionUID = 1L;
    
    public static final String KEY_SELECTION = "huxley-selection";

    public AbstractHtmlStyleAction() {}

    public AbstractHtmlStyleAction(String name, Icon icon) {
        super(name, icon);
    }

    public AbstractHtmlStyleAction(String name) {
        super(name);
    }

    protected void performStyleChange(JTextComponent textComponent, HTML.Tag tag) {
        performStyleChange(textComponent, tag, null);
    }
    
    protected void performStyleChange(JTextComponent textComponent, 
                HTML.Tag tag, Map<String, String> attributes) {  
        HTMLDocument htmlDocument = (HTMLDocument) textComponent.getDocument();

        int start = textComponent.getSelectionStart();
        int end = textComponent.getSelectionEnd();

        Element element = htmlDocument.getParagraphElement(start);
        MutableAttributeSet newAttrs = new SimpleAttributeSet(element.getAttributes());
        if (tag != null) {
            newAttrs.addAttribute(StyleConstants.NameAttribute, tag);
        }
        if (attributes != null) {
            for (Map.Entry<String, String> entry: attributes.entrySet()) {
                newAttrs.addAttribute(entry.getKey(), entry.getValue());
            }
        }

        htmlDocument.setParagraphAttributes(start, end - start, newAttrs, true);
        SwingHtmlUtil.refreshJTextComponent(textComponent);
    }

}