/**
 */
package Wkfl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Wkfl.Job#getSteps <em>Steps</em>}</li>
 *   <li>{@link Wkfl.Job#getRequires <em>Requires</em>}</li>
 * </ul>
 *
 * @see Wkfl.WkflPackage#getJob()
 * @model
 * @generated
 */
public interface Job extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Steps</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Steps</em>' attribute list.
	 * @see Wkfl.WkflPackage#getJob_Steps()
	 * @model
	 * @generated
	 */
	EList<String> getSteps();

	/**
	 * Returns the value of the '<em><b>Requires</b></em>' reference list.
	 * The list contents are of type {@link Wkfl.Job}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requires</em>' reference list.
	 * @see Wkfl.WkflPackage#getJob_Requires()
	 * @model
	 * @generated
	 */
	EList<Job> getRequires();

} // Job
