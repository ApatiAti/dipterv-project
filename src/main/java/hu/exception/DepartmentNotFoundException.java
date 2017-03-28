package hu.exception;

public class DepartmentNotFoundException extends Exception {
	
	public DepartmentNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
	public DepartmentNotFoundException(long departmentId) {
		super("A megadott id-jű korházi osztály nem létezik. Id : " + Long.toString(departmentId));
	}
}
