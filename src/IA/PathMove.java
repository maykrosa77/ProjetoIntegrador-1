package IA;

import java.util.ArrayList;
import Manager.FPoint;

public class PathMove {

	public int origin;
	public int destination;
	public ArrayList<FPoint> paths;
	
	public PathMove(int origin, int destination, ArrayList<FPoint> paths){
		this.origin = origin;
		this.destination = destination;
		this.paths = paths;
	}
}
