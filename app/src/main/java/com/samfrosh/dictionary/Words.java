package com.samfrosh.dictionary;

public class Words {

    private String defination;
    private String examples;
    private String partofspeech;
    private String audio;

    public Words(String defination, String examples, String partofspeech, String audio) {
        this.defination = defination;
        this.examples = examples;
        this.partofspeech = partofspeech;
        this.audio = audio;
    }

    public String getDefination() {
        return defination;
    }

    public void setDefination(String defination) {
        this.defination = defination;
    }

    public String getExamples() {
        return examples;
    }

    public void setExamples(String examples) {
        this.examples = examples;
    }

    public String getPartofspeech() {
        return partofspeech;
    }

    public void setPartofspeech(String partofspeech) {
        this.partofspeech = partofspeech;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
