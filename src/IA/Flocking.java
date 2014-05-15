package IA;

import java.util.ArrayList;
import Math.PVector;
import Objects.Units.BasicUnit;

public class Flocking {

	public PVector align(BasicUnit unit, ArrayList<BasicUnit> otherUnit) {
		float neighbordist = 50;
		PVector sum = new PVector(0, 0);
		int count = 0;
		for (BasicUnit other : otherUnit) {
			float d = PVector.dist(unit.location, other.location);
			if ((d > 0) && (d < neighbordist)) {
				sum.add(other.velocity);
				count++;
			}
		}
		if (count > 0) {
			sum.div((float) count);
			sum.normalize();
			sum.mult(unit.maxspeed);
			PVector steer = PVector.sub(sum, unit.velocity);
			steer.limit(unit.maxforce);
			return steer;
		} else {
			return new PVector(0, 0);
		}
	}
	
	public PVector cohesion(BasicUnit unit, ArrayList<BasicUnit> otherUnit) {
		float neighbordist = 50;
		PVector sum = new PVector(0, 0);
		int count = 0;
		for (BasicUnit other : otherUnit) {
			float d = PVector.dist(unit.location, other.location);
			if ((d > 0) && (d < neighbordist)) {
				sum.add(other.location);
				count++;
			}
		}
		if (count > 0) {
			sum.div(count);

			return seek(unit, sum);
		} else {
			return new PVector(0, 0);
		}
	}

	public PVector separate(BasicUnit unit, ArrayList<BasicUnit> otherUnit) {
		float desiredseparation = 25.0f;
		PVector steer = new PVector(0, 0, 0);
		int count = 0;

		for (BasicUnit other : otherUnit) {
			float d = PVector.dist(unit.location, other.location);
			if ((d > 0) && (d < desiredseparation)) {
				// Calculate vector pointing away from neighbor
				PVector diff = PVector.sub(unit.location, other.location);
				diff.normalize();
				diff.div(d); // Weight by distance
				steer.add(diff);
				count++; // Keep track of how many
			}
		}
		if (count > 0) {
			steer.div((float) count);
		}

		// As long as the vector is greater than 0
		if (steer.mag() > 0) {
			steer.normalize();
			steer.mult(unit.maxspeed);
			steer.sub(unit.velocity);
			steer.limit(unit.maxforce);
		}
		return steer;
	}

	public PVector seek(BasicUnit unit, PVector target) {
		PVector desired = PVector.sub(target, unit.location);
		desired.normalize();
		desired.mult(unit.maxspeed);
		PVector steer = PVector.sub(desired, unit.velocity);
		steer.limit(unit.maxforce);

		return steer;
	}
}
