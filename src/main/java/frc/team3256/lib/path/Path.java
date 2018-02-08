package frc.team3256.lib.path;

import frc.team3256.lib.math.Translation;
import java.util.ArrayList;

public class Path {

    private ArrayList<Segment> segments;
    private double segmentCompletionTolerance = 0.0;

    public Path(){
        segments = new ArrayList<>();
    }

    public void addSegment(Segment segment){
        segments.add(segment);
    }

    public static class PathUpdate{
        //lookahead point location
        Translation lookaheadPoint;
        //remaining distance in the path
        double remainingDistance;
        //distance from our current pose to the closest point on the path
        double distanceToPath;
        //current segment
        Segment currSegment;
        //closest point
        Translation closestPoint;
    }

    public PathUpdate update(Translation robotCoordinates){
        PathUpdate rv = new PathUpdate();
        Segment currSegment = segments.get(0);
        //calculate closest point to robot on path
        Translation closestPoint = currSegment.getClosestPointOnSegment(robotCoordinates);
        Translation robotToClosestPoint = new Translation(robotCoordinates, closestPoint);
        rv.distanceToPath = robotToClosestPoint.norm();

        //determine lookahead point
        rv.lookaheadPoint = currSegment.getLookAheadPoint(rv.distanceToPath, closestPoint);
        for (Segment s : segments) {
            rv.remainingDistance += s.getLength();
        }
        rv.remainingDistance -= currSegment.getCurrDistanceTraveled(closestPoint);
        rv.currSegment = currSegment;
        rv.closestPoint = closestPoint;
        //remove current segment if we are done with this segment
        double distanceRemainingOnSegment = currSegment.getRemainingDistance(robotCoordinates);
        if (distanceRemainingOnSegment <= segmentCompletionTolerance) {
            System.out.println("REMOVING SEGMENT");
            System.out.println(distanceRemainingOnSegment);
            segments.remove(0);
        }
        return rv;
    }

}
