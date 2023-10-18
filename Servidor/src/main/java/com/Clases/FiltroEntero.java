package com.Clases;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FiltroEntero extends DocumentFilter {
    int length = 0;

    public FiltroEntero(int length) {
        this.length = length;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (isNumeric(string)) {
            if (this.length > 0 && fb.getDocument().getLength() + string.length() > this.length) {
                return;
            }
            super.insertString(fb, offset, string, attr);
        }
    }

    private boolean isNumeric(String text) {
        if (text == null || text.trim().equals("")) {
            return false;
        }
        for (int iCount = 0; iCount < text.length(); iCount++) {
            if (!Character.isDigit(text.charAt(iCount))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
            AttributeSet attrs) throws BadLocationException {
        if (isNumeric(text)) {
            if (this.length > 0 && fb.getDocument().getLength() + text.length() > this.length) {
                return;
            }
            super.insertString(fb, offset, text, attrs);
        }
    }
}
