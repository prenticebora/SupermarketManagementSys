package org.learning.j2ee.supermarket.dao.test;

import java.io.Serializable;

/**
 * Tb_basicmessage Value Object.
 * This class is value object representing database table tb_basicmessage
 * This class is intented to be used together with associated Dao object.
 */

/**
 * This sourcecode has been generated by FREE DaoGen generator version 2.4.1.
 * The usage of generated code is restricted to OpenSource software projects
 * only. DaoGen is available in http://titaniclinux.net/daogen/ It has been
 * programmed by Tuomo Lukka, Tuomo.Lukka@iki.fi
 * 
 * DaoGen license: The following DaoGen generated source code is licensed under
 * the terms of GNU GPL license. The full text for license is available in GNU
 * project's pages: http://www.gnu.org/copyleft/gpl.html
 * 
 * If you wish to use the DaoGen generator to produce code for closed-source
 * commercial applications, you must pay the lisence fee. The price is 5 USD or
 * 5 Eur for each database table, you are generating code for. (That includes
 * unlimited amount of iterations with all supported languages for each database
 * table you are paying for.) Send mail to "Tuomo.Lukka@iki.fi" for more
 * information. Thank you!
 */

public class Tb_basicmessage implements Cloneable, Serializable {

	/**
	 * Persistent Instance variables. This data is directly mapped to the
	 * columns of database table.
	 */
	private int id;
	private String name;
	private int age;
	private int dept;
	private int positionId;

	/**
	 * Constructors. DaoGen generates two constructors by default. The first one
	 * takes no arguments and provides the most simple way to create object
	 * instance. The another one takes one argument, which is the primary key of
	 * the corresponding table.
	 */

	public Tb_basicmessage() {

	}

	public Tb_basicmessage(int idIn) {

		this.id = idIn;

	}

	/**
	 * Get- and Set-methods for persistent variables. The default behaviour does
	 * not make any checks against malformed data, so these might require some
	 * manual additions.
	 */

	public int getId() {
		return this.id;
	}

	public void setId(int idIn) {
		this.id = idIn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String nameIn) {
		this.name = nameIn;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int ageIn) {
		this.age = ageIn;
	}

	public int getDept() {
		return this.dept;
	}

	public void setDept(int deptIn) {
		this.dept = deptIn;
	}

	public int getPositionId() {
		return this.positionId;
	}

	public void setPositionId(int positionIdIn) {
		this.positionId = positionIdIn;
	}

	/**
	 * setAll allows to set all persistent variables in one method call. This is
	 * useful, when all data is available and it is needed to set the initial
	 * state of this object. Note that this method will directly modify instance
	 * variales, without going trough the individual set-methods.
	 */

	public void setAll(int idIn, String nameIn, int ageIn, int deptIn,
			int positionIdIn) {
		this.id = idIn;
		this.name = nameIn;
		this.age = ageIn;
		this.dept = deptIn;
		this.positionId = positionIdIn;
	}

	/**
	 * hasEqualMapping-method will compare two Tb_basicmessage instances and
	 * return true if they contain same values in all persistent instance
	 * variables. If hasEqualMapping returns true, it does not mean the objects
	 * are the same instance. However it does mean that in that moment, they are
	 * mapped to the same row in database.
	 */
	public boolean hasEqualMapping(Tb_basicmessage valueObject) {

		if (valueObject.getId() != this.id) {
			return (false);
		}
		if (this.name == null) {
			if (valueObject.getName() != null)
				return (false);
		} else if (!this.name.equals(valueObject.getName())) {
			return (false);
		}
		if (valueObject.getAge() != this.age) {
			return (false);
		}
		if (valueObject.getDept() != this.dept) {
			return (false);
		}
		if (valueObject.getPositionId() != this.positionId) {
			return (false);
		}

		return true;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	public String toString() {
		StringBuffer out = new StringBuffer(this.getDaogenVersion());
		out.append("\nclass Tb_basicmessage, mapping to table tb_basicmessage\n");
		out.append("Persistent attributes: \n");
		out.append("id = " + this.id + "\n");
		out.append("name = " + this.name + "\n");
		out.append("age = " + this.age + "\n");
		out.append("dept = " + this.dept + "\n");
		out.append("positionId = " + this.positionId + "\n");
		return out.toString();
	}

	/**
	 * Clone will return identical deep copy of this valueObject. Note, that
	 * this method is different than the clone() which is defined in
	 * java.lang.Object. Here, the retuned cloned object will also have all its
	 * attributes cloned.
	 */
	public Object clone() {
		Tb_basicmessage cloned = new Tb_basicmessage();

		cloned.setId(this.id);
		if (this.name != null)
			cloned.setName(new String(this.name));
		cloned.setAge(this.age);
		cloned.setDept(this.dept);
		cloned.setPositionId(this.positionId);
		return cloned;
	}

	/**
	 * getDaogenVersion will return information about generator which created
	 * these sources.
	 */
	public String getDaogenVersion() {
		return "DaoGen version 2.4.1";
	}

}