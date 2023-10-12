/**
 */
package Wkfl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workflow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Wkfl.Workflow#getJobs <em>Jobs</em>}</li>
 * </ul>
 *
 * @see Wkfl.WkflPackage#getWorkflow()
 * @model
 * @generated
 */
public interface Workflow extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Jobs</b></em>' containment reference list.
	 * The list contents are of type {@link Wkfl.Job}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jobs</em>' containment reference list.
	 * @see Wkfl.WkflPackage#getWorkflow_Jobs()
	 * @model containment="true"
	 * @generated
	 */
	EList<Job> getJobs();

} // Workflow
