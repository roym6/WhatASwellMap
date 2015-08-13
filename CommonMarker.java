package WhatASwellMap;

import java.util.HashMap;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PGraphics;

public abstract class CommonMarker extends SimplePointMarker {

	public CommonMarker(Location location) {
		super(location);
	}

	public CommonMarker(Location location, HashMap<java.lang.String,java.lang.Object> properties) {
		super(location, properties);
	}

	public void draw(PGraphics pg, float x, float y) {
		this.drawMarker(pg, x, y);
		this.showTitle(pg, x, y);
	}

	public abstract void drawMarker(PGraphics pg, float x, float y);
	public abstract void showTitle(PGraphics pg, float x, float y);
}
