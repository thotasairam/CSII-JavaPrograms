/* 
  Name: Sai Ram Thota
  CWID NO.: 11573236
  Email-id: tsairam@okstate.edu
 */

import java.util.ArrayList;

/**
 *Provinces Class
 *It implements DivideLanes interface.
 *
 *public double area(ArrayList<BoundarySegments> b), public double boundLen(), public ArrayList<Object> neighbors(BoundarySegments boundSegs), public ArrayList<Provinces> borderOf(BoundarySegments bounsegs),
 *as methos
 *
 *public Cities getCaptial() and Accesor.
 *
*/

public class Provinces implements DivideLanes {

    //Instance Variables
	private String name;
	private ArrayList<Cities> cities=new ArrayList<Cities>();
	
	/**
	*getCapital()
	*@return null
	*/

	public Cities getCapital(){
		return null;
	}
	
	/**
	*area
	*@parm ArrayList<BoundarySegments> b
	*@return double
	*/
	public double area(ArrayList<BoundarySegments> b) {
		return 0;
	}

	/**
	*boundLen()
	*@return bound
	*/
	public double boundLen() {
		return 0;
	}
	
	/**
	*neighbors
	*@parm BoundarySegments boundSegs
	*@return ArrayList<Object> 
	*/
	
	public ArrayList<Object> neighbors(BoundarySegments boundSegs) {
		return null;
	}
	
	/**
	*borderOf
	*@parm BoundarySegments boundSegs
	*@return ArrayList<Provinces> 
	*/
	public ArrayList<Provinces> borderOf(BoundarySegments boundSegs) {
		return null;
	}
}