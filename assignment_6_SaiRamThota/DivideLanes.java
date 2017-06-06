/* 
  Name: Sai Ram Thota
  CWID NO.: 11573236
  Email-id: tsairam@okstate.edu
 */

import java.util.ArrayList;

/**
*DivideLanes
*
*It's an interface. 
*
*/

public interface DivideLanes {
	double area(ArrayList<BoundarySegments> bound);
	double boundLen();
	ArrayList<Object> neighbors(BoundarySegments boundarySegments);
	ArrayList<Provinces> borderOf(BoundarySegments boundarySegments);
}
