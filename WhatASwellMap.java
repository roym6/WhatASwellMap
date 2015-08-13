package WhatASwellMap;

import WhatASwellMap.SwellInfo;
import WhatASwellMap.JythonFactory;
import WhatASwellMap.SwellMarker;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.marker.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
public class WhatASwellMap extends PApplet {
	
	UnfoldingMap map;
	private List<Marker> beachMarkers;
	private Map<String, Location> beachLocations;
	
	public void setup() {	
		size(800, 600, P2D);

		map = new UnfoldingMap(this, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		beachMarkers = new ArrayList<Marker>();
		beachLocations = new HashMap<String, Location>();
		getLocations();
		makeMarkers();
		
		map.addMarkers(beachMarkers);
		
		map.zoomAndPanTo(10, beachLocations.get("Blacks"));
	}
	
	public void getLocations() {
		beachLocations.put("Blacks", new Location(32.878, -117.251));
		beachLocations.put("HB North", new Location(33.655, -118.004));
		beachLocations.put("Goldenwest", new Location(33.667, -118.018));
		beachLocations.put("Porto", new Location(33.902, -118423));
	}
	
	public void makeMarkers() {				
		JythonFactory jf = JythonFactory.getInstance();
		SwellInfo eType = (SwellInfo) jf.getJythonObject(
				"WhatASwellMap.SwellInfo", "/home/roy/Desktop/WhatASwellMap/Swell.py");
		String holder = eType.getSwell();
		
		Type mapOfStringObjectType = new TypeToken<Map<String, Map<String,String>>>() {}.getType();
        Gson gson = new Gson();
        Map<String, Map<String,String>> obj = gson.fromJson(holder, mapOfStringObjectType);

    	System.out.println(obj.keySet());
        for(String name : obj.keySet()) {
        	beachMarkers.add(new SwellMarker(map, name, beachLocations.get(name),obj.get(name)));
        }
	}
	
	
	public void draw() {
		map.draw();
	}
	
}
