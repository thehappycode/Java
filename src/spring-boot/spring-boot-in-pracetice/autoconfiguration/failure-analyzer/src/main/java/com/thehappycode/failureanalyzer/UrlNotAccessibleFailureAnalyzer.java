package com.thehappycode.failureanalyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalyzer;

public class UrlNotAccessibleFailureAnalyzer extends AbstractFailureAnalyzer<UrlNotAccessibleException>{

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, UrlNotAccessibleException cause) {
    
        // TODO Auto-generated method stub
        return new FailureAnalysis("Unable to access the URL" + cause.getUrl(), "Validate the URL and ensure it is accessible", cause);

    }
}
