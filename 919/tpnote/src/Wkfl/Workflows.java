/**
 */
package Wkfl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workflows</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Wkfl.Workflows#getWorkflows <em>Workflows</em>}</li>
 * </ul>
 *
 * @see Wkfl.WkflPackage#getWorkflows()
 * @model
 * @generated
 */
public interface Workflows extends EObject {
	/**
	 * Returns the value of the '<em><b>Workflows</b></em>' containment reference list.
	 * The list contents are of type {@link Wkfl.Workflow}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workflows</em>' containment reference list.
	 * @see Wkfl.WkflPackage#getWorkflows_Workflows()
	 * @model containment="true"
	 * @generated
	 */
	EList<Workflow> getWorkflows();

} // Workflows
