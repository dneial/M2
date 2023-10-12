/**
 */
package pipelinesA;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link pipelinesA.Stage#getJobs <em>Jobs</em>}</li>
 *   <li>{@link pipelinesA.Stage#getNextStage <em>Next Stage</em>}</li>
 * </ul>
 *
 * @see pipelinesA.PipelinesAPackage#getStage()
 * @model
 * @generated
 */
public interface Stage extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Jobs</b></em>' containment reference list.
	 * The list contents are of type {@link pipelinesA.Job}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jobs</em>' containment reference list.
	 * @see pipelinesA.PipelinesAPackage#getStage_Jobs()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Job> getJobs();

	/**
	 * Returns the value of the '<em><b>Next Stage</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Stage</em>' containment reference.
	 * @see #setNextStage(Stage)
	 * @see pipelinesA.PipelinesAPackage#getStage_NextStage()
	 * @model containment="true"
	 * @generated
	 */
	Stage getNextStage();

	/**
	 * Sets the value of the '{@link pipelinesA.Stage#getNextStage <em>Next Stage</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next Stage</em>' containment reference.
	 * @see #getNextStage()
	 * @generated
	 */
	void setNextStage(Stage value);

} // Stage
