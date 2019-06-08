package com.shinycatcher.api.enums;

public enum CaptureBall {
	
	POKE_BALL("Poke Ball"),
	GREAT_BALL("Great Ball"),
	MASTERBALL("");

    private final String display;

    private CaptureBall(final String display) {
        this.display = display;
    }

    public String getDisplay() { 
    	return display; 
    }

}
