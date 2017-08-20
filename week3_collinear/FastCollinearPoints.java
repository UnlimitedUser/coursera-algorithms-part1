import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class FastCollinearPoints {
	
	private final LineSegment[] segments;

	public FastCollinearPoints(Point[] points)    // finds all line segments containing 4 points
	{
		if (points == null) illegalArgument();
		for (int a = 0; a < points.length; a++) if (points[a] == null) illegalArgument();
		Point[] tmp = Arrays.copyOf(points, points.length);
		Arrays.sort(tmp);
		
		for (int a = 0; a < tmp.length - 1; a++) if (tmp[a].compareTo(tmp[a + 1]) == 0) illegalArgument();

		List<LineSegment> list = new LinkedList<LineSegment>();

		for (int a = 0; a < tmp.length; a++) {
			Point[] current = tmp.clone();
			Arrays.sort(current, tmp[a].slopeOrder());
			int same = 0;
			for (int i = a + 1; i < current.length - 1; i++) {
				if (current[i] == current[i + 1])       same++;
				else if (same >= 3)                     list.add(new LineSegment(current[i - 3], current[i]));
				else                                    same = 0;
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