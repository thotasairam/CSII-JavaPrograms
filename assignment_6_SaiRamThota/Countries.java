/* 
  Name: Sai Ram Thota
  CWID NO.: 11573236
  Email-id: tsairam@okstate.edu
 */
 
import java.util.ArrayList;

/**
*
*Countries is a class which implements DivideLanes interface.
*
*/

public class Countries implements DivideLanes{ 

    //Instince Variables
	private String name;
	private ArrayList<Provinces> province=new ArrayList<Provinces>();
	private ArrayList<Rivers> rivers=new ArrayList<Rivers>();
	private ArrayList<BoundarySegments> boundarySegments = new ArrayList<BoundarySegments>();

	/**
	*
	*Countries counstrucor
	*/
	
	public Countries(){
		
	}
    
	/**
	*getCapital()	
	
	@return Cities
	*/
	public Cities getCapital(){
		return null;
	}

	/**
	*getCities
	
	@return Cities[]
	*/
	public Cities[] getCities(){
		
		return null;
	}
	
	/**
	*area
	
	@parm ArrayList<BoundarSegments> boundSegs
	@return double
	*/
	public double area(ArrayList<BoundarySegments> boundSegs) {
		return 0;
	}
	
    /**
	*boundLen()
	
	@return double
	*/	
	public double boundLen() {
		return 0;
	}
	
	/**
	*neighbors
	
	@parm BoundarSegments boundSegs
	@return ArrayList<Object>
	*/
	public ArrayList<Object> neighbours(BoundarySegments boundSegs) {
		return null;
	}
	
	/**
	*borderOf
	
	@parm BoundarSegments boundSegs
	@return ArrayList<Provinces>
	*/
	public ArrayList<Provinces> borderOf(BoundarySegments boundSegs) {
		return null;
	}
	
	/**
	*getName()
	*It's a accessor method.
    
	@return String name
	*/
	public String getName() {
		return name;
	}
	
	/**
	*setName
	*It's a mutator method.
	
	@parm String
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	*getProvinces()
	*It's a accessor method
	
	@return ArrayList<Provinces>
	*/
	public ArrayList<Provinces> getProvinces() {
		return province;
	}

	/**
	*addProvinces
	*It's a void method
	
	@parm Provinces
	*/
	public void addProvinces(Provinces provinces) {
		this.province.add(provinces);
	}
    
	/**
	*getRivers()
	*It's a accessor method.
	
	@return ArrayList<Rivers>
	*/
	public ArrayList<Rivers> getRivers() {
		return rivers;
	}
    
    /**
	*addRivers
	*It's a void method which add Rivers variable passed in parameter.
	
	@parm Rivers river
    */	
	public void addRivers(Rivers rivers) {
		this.rivers.add(rivers);
	}

	/**
	*getBoundaries()
	*It's a accessor method.
	
	@return ArrayList<BoundarySegments>
	*/
	public ArrayList<BoundarySegments> getBoundaries() {
		return boundarySegments;
	}

	/**
	*addBoundaries
	*It;s a void methos which add BoundarySegments pass in parameters.
	
	@parm BoundarySegments bound
	*/
	public void addBoundaries(BoundarySegments bound) {
		this.boundarySegments.add(bound);
	}

	@Override
	public ArrayList<Object> neighbors(BoundarySegments boundSegs) {
		return null;
	}

}
