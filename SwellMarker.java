package WhatASwellMap;

import java.util.Map;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import de.fhpotsdam.unfolding.UnfoldingMap;
import processing.core.*;

public class SwellMarker extends CommonMarker {
	private String name;
	private String waveRange;
	private String conditions;
	private UnfoldingMap map;
	
	private static float PI = 3.14f;

	public SwellMarker(UnfoldingMap map, String name, Location location, Map<String,String> swellInfo) {
		super(location);
		this.name = name;
		this.waveRange = swellInfo.get("Wave Range");
		this.conditions = swellInfo.get("Conditions");
		this.map = map;
	}

	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		ScreenPosition posLondon = this.getScreenPosition(map);
		pg.pushStyle();
		pg.strokeWeight(12);
		pg.stroke(200, 0, 0, 200);
		pg.strokeCap(pg.SQUARE);
		pg.noFill();
		float s = 44;
		pg.arc(posLondon.x, posLondon.y, s, s, -PI * 0.9f, -PI * 0.1f);
		pg.arc(posLondon.x, posLondon.y, s, s, PI * 0.1f, PI * 0.9f);
		pg.fill(0);
		pg.text(name, posLondon.x - pg.textWidth(name) / 2, posLondon.y + 4);
		pg.popStyle();
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		pg.pushStyle();
		pg.fill(0);
		pg.textSize(26);
		pg.text(conditions, x, y, x+20, y+20);
		pg.popStyle();
	}


}
