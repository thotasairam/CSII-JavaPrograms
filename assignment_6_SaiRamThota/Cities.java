/* 
  Name: Sai Ram Thota
  CWID NO.: 11573236
  Email-id: tsairam@okstate.edu
 */
 
import java.util.ArrayList;

/**
*Cities class implements DivideLanes interface.
*/

public class Cities implements DivideLanes {
    //Instance Variables
	private String name;
	private Double rad;
	private Double dia;
	private double theta;
	private double psi;
	private boolean isCountryCapital;
	private boolean isProvinceCapital;
	private boolean isStateCapital;
	
	/**
	*Cities constructor.
	
	@parm String name, Double rad, Double theta, Double psi
	*/
	public Cities(String name,Double rad,Double dia,Double theta,Double psi){
		setName(name);
		setRadius(rad);
		setDiameter(dia);
		setTheta(theta);
		setPsi(psi);
		
		
	}
	
	/**
	*distance
	
	@parm Cities city
	@return double
	*/	
	public double distance(Cities city){
		
		return 0;
	}
	
	/**
	*area
	
	@parm ArrayList<BoundarSegments> boundaries
	@return double
	*/
	public double area(ArrayList<BoundarySegments> boundries) {
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
	public ArrayList<Object> neighbors(BoundarySegments boundSegs) {
		return null;
	}
	
	@Override
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
	*getRadius()
	*It's a accessor method
	
	@return double radius
	*/
	public double getRadius() {
		return rad;
	}

	/**
	*setRadius
	*It's a mutator method.
	
	@parm double rad
	*/
	public void setRadius(Double rad) {
		this.rad = rad;
	}
	
	/**
	*getDiameter()
	*It's a accessor method
	
	@return double dia
	*/
	public double getDiameter() {
		return dia;
	}

	/**
	*setDiameter
	*It's a mutator method.
	
	@parm double dia
	*/
	public void setDiameter(Double dia) {
		this.dia = dia;
	}

	/**
	*getTheta()
	*It's a accessor method
	
	@return double theta
	*/
	public double getTeta() {
		return theta;
	}

	/**
	*setTheta
	*It's a mutator method.
	
	@parm double theta
	*/
	public void setTheta(double theta) {
		this.theta = theta;
	}
	
    /**
	*getPsi()
	*It's a accessor method
	
	@return double psi
	*/
	public double getPsi() {
		return psi;
	}

	/**
	*setPsi
	*It's a mutator method.
	
	@parm double psi
	*/
	public void setPsi(double psi) {
		this.psi = psi;
	}

	/**
	*isCountryCapital()
	*
	
	@return boolean isCountryCapital i.e.,true or false depending on the condition
	*/
	public boolean isCountryCapital() {
		return isCountryCapital;
	}

	/**
	*setCountryCapital
	*It's a mutator method.
	
	@parm boolean isCountryCapital
	*/
	public void setCountryCapital(boolean isCountryCapital) {
		this.isCountryCapital = isCountryCapital;
	}

	/**
	*isProvinceCapital()
	*
	
	@return boolean isProvinceCapital i.e.,true or false depending on the condition
	*/
	public boolean isProvinceCapital() {
		return isProvinceCapital;
	}

	/**
	*setProvinceCapital
	*It's a mutator method.
	
	@parm boolean isProvinceCapital
	*/
	public void setProvinceCapital(boolean isProvinceCapital) {
		this.isProvinceCapital = isProvinceCapital;
	}
	
	/**
	*isStateCapital()
	*
	
	@return boolean isStateCapital i.e.,true or false depending on the condition
	*/
	public boolean isStateCapital() {
		return isStateCapital;
	}
 
    /**
	*setStateCapital
	*It's a mutator method.
	
	@parm boolean isStateCapital
	*/
	public void setStateCapital(boolean isStateCapital) {
		this.isStateCapital = isStateCapital;
	}
}