package com.manning.junitbook.ch04.assumptions;

public class SUT {
    private Job currentJob;

    public void run(Job currentJob) {
        this.currentJob = currentJob;
    }

    public boolean hasJobToRun(){
        return currentJob != null;
    }
}
