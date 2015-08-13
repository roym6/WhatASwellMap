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
	
	private static float PI = (float) 3.14;

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
		pg.strokeWeight(12);
		pg.stroke(200, 0, 0, 200);
		pg.noFill();
		float s = 44;
		pg.arc(posLondon.x, posLondon.y, s, s, -PI * 0.9f, -PI * 0.1f);
		pg.arc(posLondon.x, posLondon.y, s, s, PI * 0.1f, PI * 0.9f);
		pg.fill(0);
		pg.text(name, posLondon.x, posLondon.y + 4);
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		// TODO Auto-generated method stub
		
	}


}
