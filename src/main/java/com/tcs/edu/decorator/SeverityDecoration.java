package com.tcs.edu.decorator;

import com.tinkoff.edu.decorator.Severity;

/**
 * Message severity enrichment class.
 *
 * @author i.v.zotov
 * @version 1.0
 */


public class SeverityDecoration {

    /**
     * Method for outputting  severity.
     * Side effect: return severityString.
     */

    public String severityDecorate(Severity severity) {
        String severityString = null;
        switch (severity) {
            case MAJOR:
                severityString = "(!!!)";
                break;
            case REGULAR:
                severityString = "(!)";
                break;
            case MINOR:
                severityString = "()";
                break;

        }
        return severityString;
    }
}
