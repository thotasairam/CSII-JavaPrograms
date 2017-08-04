/* 
  Name: Sai Ram Thota
  CWID NO.: 11573236
  Email-id: tsairam@okstate.edu
 */


import java.util.ArrayList;

/**
*Rivers Class
*It implements DivideLanes interface.
*
*Has Rivers(String ne, Double ln, String sEq) consteructor.
*
*public double area(ArrayList<BoundarySegments> i), public double boundaLen(), 	public ArrayList<Object> neighbors(BoundarySegments i),	public ArrayList<Provinces> borderOf(BoundarySegments boundSegs) as methods
*
*public String getDirectionOfFlow(), public void setDirectionOfFlow(String v, String dirOfFlow), public String getVast(),public void setVast(String v),public String getTributaries(),public void setTibutaries(String trib
*as Accessors and Mutators.
*/


public class Rivers extends BoundarySegments implements DivideLanes {

    //Instance Variables
	private String dirOfFlow;
	private String v;
	private String trib;
	
	/**
	*Rivers constructor
	*@parm String Name, Double len, String ScientificEq
	*/
	
	public Rivers(String name, Double len, String scientificEq) {
		super(name, len, scientificEq);
		
	}
	
	/**
	*area
	*@parm ArrayList<BoundarySegments>
	*@return double
	*/
	public double area(ArrayList<BoundarySegments> boundaries) {
		return 0;
	}
	
	/**
	*boundLen
	*@return double
	*/
	public double boundLen() {
		return 0;
	}
	
	/**
	*Arraylist<Object> neighbors
	*@parm BoundarySegments
	*@return null
	*/
	public ArrayList<Object> neighbors(BoundarySegments boundSegs) {
		return null;
	}
	
	/**
	*Arraylist<Provinces> bodersOf
	*@parm BoundarySegments
	*@return null
	*/
	public ArrayList<Provinces> borderOf(BoundarySegments boundSegs) {
		return null;
	}
	
	/**
	*getDirectionofFlow()
	*@return String
	*/
	public String getDirectionOfFlow() {
		return dirOfFlow;
	}
	
	/**
	*setDirectionofFlow
	*@parm String v, String dirOfFlow
	*/
	public void setDirectionOfFlow(String v, String dirOfFlow) {
		this.dirOfFlow = dirOfFlow;
	}
    
	/**
	*getVast()
	*@return String
	*/
	public String getVast() {
		return v;
	}
	
	/**
	*setVast
	*@parm String v
	*/
	public void setVast(String v) {
		this.v = v;
	}
    
	/**
	*getTributaries()
	*@return String
	*/
	public String getTributaries() {
		return trib;
	}
	
	/**
	*setTributaries
	*@parm String trib
	*/
	public void setTributaries(String trib) {
		this.trib = trib;
	}	
}//End of Class
