/**
 */
package pipelinesA;

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
 *   <li>{@link pipelinesA.Job#getScript <em>Script</em>}</li>
 * </ul>
 *
 * @see pipelinesA.PipelinesAPackage#getJob()
 * @model
 * @generated
 */
public interface Job extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Script</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' attribute list.
	 * @see pipelinesA.PipelinesAPackage#getJob_Script()
	 * @model
	 * @generated
	 */
	EList<String> getScript();

} // Job
