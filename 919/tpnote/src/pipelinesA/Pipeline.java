/**
 */
package pipelinesA;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pipeline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link pipelinesA.Pipeline#getFirstStage <em>First Stage</em>}</li>
 * </ul>
 *
 * @see pipelinesA.PipelinesAPackage#getPipeline()
 * @model
 * @generated
 */
public interface Pipeline extends EObject {
	/**
	 * Returns the value of the '<em><b>First Stage</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Stage</em>' containment reference.
	 * @see #setFirstStage(Stage)
	 * @see pipelinesA.PipelinesAPackage#getPipeline_FirstStage()
	 * @model containment="true"
	 * @generated
	 */
	Stage getFirstStage();

	/**
	 * Sets the value of the '{@link pipelinesA.Pipeline#getFirstStage <em>First Stage</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Stage</em>' containment reference.
	 * @see #getFirstStage()
	 * @generated
	 */
	void setFirstStage(Stage value);

} // Pipeline
