import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class BruteCollinearPoints {
	
	private final LineSegment[] segments;

	public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
	{
		if (points == null) illegalArgument();
		for (int a = 0; a < points.length; a++) if (points[a] == null) illegalArgument();
		Point[] tmp = Arrays.copyOf(points, points.length);
		Arrays.sort(tmp);
		
		for (int a = 0; a < tmp.length - 1; a++) if (tmp[a].compareTo(tmp[a + 1]) == 0) illegalArgument();

		List<LineSegment> list = new LinkedList<LineSegment>();

		for (int a = 0; a < tmp.length; a++) {
			for (int i = a + 1; i < tmp.length; i++) {
				for (int e = i + 1; e < tmp.length; e++) {
					for (int w = e + 1; w < tmp.length; w++) {
						double temp = tmp[a].slopeTo(tmp[i]);
						if (temp == tmp[a].slopeTo(tmp[e]) && temp == tmp[a].slopeTo(tmp[w])) list.add(new LineSegment(tmp[a], tmp[w]));
					}
				}
			}
		}
		segments = list.toArray(new LineSegment[list.size()]);
	}

	private void illegalArgument()
	{ throw new java.lang.IllegalArgumentException("null or dublicate values"); }
	
	public           int numberOfSegments()        // the number of line segments
	{ return segments.length; }
	
	public LineSegment[] segments()                // the line segments
	{ return segments.clone(); }

}